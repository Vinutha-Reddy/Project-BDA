import java.io.*;
import java.util.*;

public class SimpleCovidAnalysis {

    public static class CovidData {
        String country;
        String date;
        int confirmed;
        int deaths;
        int recovered;

        public CovidData(String country, String date, int confirmed, int deaths, int recovered) {
            this.country = country;
            this.date = date;
            this.confirmed = confirmed;
            this.deaths = deaths;
            this.recovered = recovered;
        }
    }

    public static void main(String[] args) {
        System.out.println("🚀 Starting COVID-19 Data Analysis (MapReduce Simulation)...");

        // Step 1: Mapper Phase - Read and parse data
        List<CovidData> mappedData = mapperPhase("../data/covid_data.csv");

        // Step 2: Shuffle and Sort Phase
        Map<String, List<CovidData>> shuffledData = shuffleAndSort(mappedData);

        // Step 3: Reducer Phase - Aggregate by country
        List<CountrySummary> results = reducerPhase(shuffledData);

        // Step 4: Output results
        outputResults(results);

        System.out.println("✅ Analysis Complete!");
    }

    // MAPPER PHASE: Read CSV and emit key-value pairs
    public static List<CovidData> mapperPhase(String filename) {
        List<CovidData> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    try {
                        String country = fields[0].trim();
                        String date = fields[1].trim();
                        int confirmed = Integer.parseInt(fields[2].trim());
                        int deaths = Integer.parseInt(fields[3].trim());
                        int recovered = Integer.parseInt(fields[4].trim());

                        data.add(new CovidData(country, date, confirmed, deaths, recovered));

                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("📊 Mapper processed " + data.size() + " records");
        return data;
    }

    // SHUFFLE AND SORT PHASE: Group by country
    public static Map<String, List<CovidData>> shuffleAndSort(List<CovidData> data) {
        Map<String, List<CovidData>> grouped = new HashMap<>();

        for (CovidData record : data) {
            grouped.computeIfAbsent(record.country, k -> new ArrayList<>()).add(record);
        }

        System.out.println("🔄 Shuffled data for " + grouped.size() + " countries");
        return grouped;
    }

    // REDUCER PHASE: Aggregate statistics per country
    public static List<CountrySummary> reducerPhase(Map<String, List<CovidData>> groupedData) {
        List<CountrySummary> results = new ArrayList<>();

        for (Map.Entry<String, List<CovidData>> entry : groupedData.entrySet()) {
            String country = entry.getKey();
            List<CovidData> countryData = entry.getValue();

            // Find maximum values (latest cumulative counts)
            int maxConfirmed = 0;
            int maxDeaths = 0;
            int maxRecovered = 0;

            for (CovidData data : countryData) {
                maxConfirmed = Math.max(maxConfirmed, data.confirmed);
                maxDeaths = Math.max(maxDeaths, data.deaths);
                maxRecovered = Math.max(maxRecovered, data.recovered);
            }

            int active = maxConfirmed - maxDeaths - maxRecovered;
            results.add(new CountrySummary(country, maxConfirmed, maxDeaths, maxRecovered, active));
        }

        // Sort by confirmed cases (descending)
        results.sort((a, b) -> Integer.compare(b.confirmed, a.confirmed));

        System.out.println("📈 Reducer processed " + results.size() + " countries");
        return results;
    }

    // OUTPUT PHASE: Save results and display summary
    public static void outputResults(List<CountrySummary> results) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("../website/processed_data.json"))) {
            writer.println("[");
            for (int i = 0; i < results.size(); i++) {
                CountrySummary summary = results.get(i);
                writer.printf(
                        "  {\"country\": \"%s\", \"confirmed\": %d, \"deaths\": %d, \"recovered\": %d, \"active\": %d}",
                        summary.country, summary.confirmed, summary.deaths, summary.recovered, summary.active);
                if (i < results.size() - 1)
                    writer.println(",");
                else
                    writer.println();
            }
            writer.println("]");

            System.out.println("💾 Results saved to ../website/processed_data.json");

        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }

        // Display top 5 countries
        System.out.println("\n🏆 Top 5 Countries by Confirmed Cases:");
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            CountrySummary summary = results.get(i);
            System.out.printf("%d. %s: %,d confirmed, %,d deaths, %,d recovered\n",
                    i + 1, summary.country, summary.confirmed, summary.deaths, summary.recovered);
        }
    }

    public static class CountrySummary {
        String country;
        int confirmed;
        int deaths;
        int recovered;
        int active;

        public CountrySummary(String country, int confirmed, int deaths, int recovered, int active) {
            this.country = country;
            this.confirmed = confirmed;
            this.deaths = deaths;
            this.recovered = recovered;
            this.active = active;
        }
    }
}