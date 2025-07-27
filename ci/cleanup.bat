@echo off
echo Cleaning previous test outputs...

REM Clean Surefire reports
if exist target\surefire-reports (
    rmdir /s /q target\surefire-reports
)

REM Clean Allure results
if exist target\allure-results (
    rmdir /s /q target\allure-results
)

REM Clean Allure report
if exist target\allure-report (
    rmdir /s /q target\allure-report
)

REM Clean screenshots (if in test-output/screenshots or similar)
if exist test-output\screenshots (
    rmdir /s /q test-output\screenshots
)

echo Cleanup complete.
