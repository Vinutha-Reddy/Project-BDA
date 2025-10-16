@echo off
echo.
echo ========================================
echo   COVID-19 Analytics - Deployment Kit
echo   By: Vinutha - Enterprise Edition  
echo ========================================
echo.

echo 📋 Checking website files...
if not exist "website\index.html" (
    echo ❌ Error: index.html not found in website folder
    pause
    exit /b 1
)

if not exist "website\css\style.css" (
    echo ❌ Error: style.css not found in website\css folder  
    pause
    exit /b 1
)

if not exist "website\processed_data.json" (
    echo ❌ Error: processed_data.json not found in website folder
    pause
    exit /b 1
)

echo ✅ All website files found and ready for deployment!
echo.

echo 🌐 Your hosting options:
echo.
echo 1. GitHub Pages:     vinutha-covid-analytics.github.io
echo 2. Netlify:          vinutha-covid-analytics.netlify.app  
echo 3. Vercel:           vinutha-covid-analytics.vercel.app
echo 4. Firebase:         vinutha-covid-analytics.web.app
echo.

echo 📁 Files ready for upload:
echo    ├── index.html (771 lines - Full dashboard)
echo    ├── processed_data.json (Top 10 countries data)
echo    └── css/style.css (Complete styling system)
echo.

echo 🎯 Next Steps:
echo 1. Follow the HOSTING_GUIDE.md instructions
echo 2. Upload the 'website' folder contents to your chosen platform
echo 3. Your enterprise-grade COVID-19 dashboard will be live!
echo.

echo 🚀 Opening hosting guide...
start "" "HOSTING_GUIDE.md"

echo.
echo ✨ Your project is ready for the world to see!
echo 📧 Perfect for: Academic submission, Portfolio showcase, Professor demo
echo.
pause