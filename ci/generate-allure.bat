@echo off
echo Generating Allure Report...

REM Make sure Allure is in PATH
where allure >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Allure not found in PATH. Please install Allure CLI and set PATH.
    exit /b 1
)

allure generate target\allure-results --clean -o target\allure-report

echo Allure Report generated at target\allure-report
