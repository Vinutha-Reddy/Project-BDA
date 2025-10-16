@echo off
echo ========================================
echo   COVID-19 MapReduce Analysis
echo ========================================
echo.

cd /d "%~dp0"

echo 📂 Current directory: %cd%
echo.

echo 🔨 Compiling Java MapReduce program...
javac SimpleCovidAnalysis.java

if %errorlevel% equ 0 (
    echo ✅ Compilation successful!
    echo.
    
    echo 🚀 Running COVID-19 analysis...
    echo.
    java SimpleCovidAnalysis
    
    if %errorlevel% equ 0 (
        echo.
        echo ✅ Analysis completed successfully!
        echo 📂 Results saved to ../website/processed_data.json
        echo.
        echo 🌐 You can now open the website:
        echo    File: %~dp0..\website\index.html
        echo.
        
        REM Ask if user wants to open the website
        set /p choice="Do you want to open the website now? (y/n): "
        if /i "%choice%"=="y" (
            start "" "%~dp0..\website\index.html"
        )
    ) else (
        echo ❌ Analysis failed!
    )
) else (
    echo ❌ Compilation failed!
    echo Make sure Java JDK is installed and in your PATH.
)

echo.
pause