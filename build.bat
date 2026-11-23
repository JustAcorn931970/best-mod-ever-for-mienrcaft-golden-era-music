@echo off
setlocal
cd /d "%~dp0"

echo ===============================================
echo   Realistic Water Era Music - build
echo ===============================================

where java >nul 2>nul
if errorlevel 1 (
  echo ERROR: Java 25 is required but java was not found on PATH.
  echo Install a JDK 25 and add its bin folder to PATH, then try again.
  pause
  exit /b 1
)

java -version 2>&1 | findstr /C:"25." >nul
if errorlevel 1 (
  echo ERROR: This build requires Java 25. The current java on PATH is not Java 25.
  echo Set JAVA_HOME and PATH to your JDK 25 installation, then try again.
  java -version
  pause
  exit /b 1
)

set "TOOLS_DIR=%LOCALAPPDATA%\RWE-Music-Build-Tools"
set "GRADLE_HOME=%TOOLS_DIR%\gradle-9.9.2"
set "GRADLE_EXE=%GRADLE_HOME%\bin\gradle.bat"

if not exist "%GRADLE_EXE%" (
  echo Downloading Gradle 9.9.2...
  if not exist "%TOOLS_DIR%" mkdir "%TOOLS_DIR%"
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference='Stop'; $zip=Join-Path $env:LOCALAPPDATA 'RWE-Music-Build-Tools\gradle-9.9.2-bin.zip'; Invoke-WebRequest 'https://services.gradle.org/distributions/gradle-9.9.2-bin.zip' -OutFile $zip; Expand-Archive -LiteralPath $zip -DestinationPath (Join-Path $env:LOCALAPPDATA 'RWE-Music-Build-Tools') -Force; Remove-Item $zip -Force"
  if errorlevel 1 (
    echo ERROR: Could not download Gradle. Check your internet connection.
    pause
    exit /b 1
  )
)

call "%GRADLE_EXE%" --no-daemon clean build
if errorlevel 1 (
  echo.
  echo BUILD FAILED. Check the error above.
  pause
  exit /b 1
)

echo.
echo BUILD COMPLETE: build\libs\realistic-water-era-music-1.0.0.jar
echo Copy that JAR into your Minecraft mods folder.
pause
