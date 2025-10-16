# 🦠 COVID-19 Data Analysis using Hadoop MapReduce

A Big Data Analytics project that processes COVID-19 datasets using MapReduce paradigm and visualizes results through an interactive web dashboard.

## 📁 Project Structure
```
COVID-19/
├── data/
│   └── covid_data.csv          # Input dataset
├── hadoop/
│   ├── SimpleCovidAnalysis.java # ✅ Java MapReduce simulation (no dependencies)
│   └── run_analysis.bat        # ✅ Script to compile and run analysis
└── website/
    ├── index.html              # Main dashboard page (self-contained)
    ├── css/
    │   └── style.css           # Dashboard styling
    └── processed_data.json     # Output from MapReduce (generated)
```

## 🎯 Project Features

### MapReduce Analysis
- **Mapper Phase**: Reads CSV data and emits country-wise statistics
- **Shuffle & Sort**: Groups data by country
- **Reducer Phase**: Aggregates total confirmed, deaths, and recovered cases per country
- **Output**: JSON format for web visualization

### Web Dashboard
- 📊 **Summary Cards**: Global statistics overview
- 📈 **Charts**: Top 10 countries bar chart and cases distribution pie chart
- 📋 **Data Table**: Detailed country-wise breakdown with death rates
- 📱 **Responsive Design**: Works on desktop and mobile devices

## 🚀 How to Run

### Step 1: Run MapReduce Analysis
1. Navigate to the `hadoop` directory
2. Double-click `run_analysis.bat` or run in Command Prompt:
   ```cmd
   cd hadoop
   run_analysis.bat
   ```

### Step 2: View Results
1. The analysis will generate `processed_data.json` in the website folder
2. Open `website/index.html` in your web browser
3. The dashboard will automatically load and display the processed data

## 💻 Technical Implementation

### MapReduce Logic
```java
// Mapper: Read CSV and emit key-value pairs
country -> (date, confirmed, deaths, recovered)

// Reducer: Aggregate by country
country -> (max_confirmed, max_deaths, max_recovered, active_cases)
```

### Web Technologies
- **Frontend**: HTML5, CSS3, JavaScript
- **Visualization**: Chart.js library
- **Design**: Responsive grid layout with modern styling

## 📊 Data Format

### Input (CSV)
```csv
Country,Date,Confirmed,Deaths,Recovered
USA,2023-01-01,1000000,50000,900000
India,2023-01-01,800000,40000,750000
```

### Output (JSON)
```json
[
  {
    "country": "USA",
    "confirmed": 1001500,
    "deaths": 50100,
    "recovered": 901000,
    "active": 50400
  }
]
```

## 🛠 Requirements

### For Java Analysis
- Java JDK 8 or higher
- Command Prompt or Terminal access

### No External Dependencies Required
- Pure Java implementation (no Hadoop installation needed)
- Uses MapReduce concepts without external libraries

### For Website
- Modern web browser (Chrome, Firefox, Safari, Edge)
- Local web server (optional, for file:// protocol restrictions)

## 📈 Analytics Insights

The dashboard provides:
1. **Global Overview**: Total cases across all countries
2. **Country Rankings**: Top 10 most affected countries
3. **Cases Distribution**: Visual breakdown of recovered vs active vs deaths
4. **Death Rates**: Mortality percentage by country
5. **Detailed Statistics**: Comprehensive country-wise data table

## 🎓 Educational Value

This project demonstrates:
- **MapReduce Programming**: Core big data processing paradigm
- **Data Processing**: Large-scale dataset handling
- **Web Development**: Modern dashboard creation
- **Data Visualization**: Interactive charts and graphs
- **System Integration**: Connecting backend processing with frontend display

## 🔧 Troubleshooting

### Java Compilation Issues
- Ensure Java JDK is installed: `java -version`
- Check PATH environment variable includes Java bin directory

### Website Display Issues
- Check browser developer console for errors
- Ensure `processed_data.json` exists in website folder
- Try opening from a local web server instead of file://

### Data Loading Problems
- Verify CSV format matches expected structure
- Check file paths in Java code match your directory structure

## 🚀 Future Enhancements

- [ ] Time-series analysis with date filtering
- [ ] Interactive world map visualization
- [ ] Real-time data updates from APIs
- [ ] Machine learning predictions
- [ ] Mobile app version
- [ ] Database integration
- [ ] Advanced Hadoop cluster deployment

## 📝 License

This project is for educational purposes. COVID-19 data should be sourced from reliable organizations like WHO, Johns Hopkins University, or government health departments.

---

**Made with ❤️ for Big Data Analytics Learning**