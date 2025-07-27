# Ecommerce Web Application - Test Automation Framework

This is a **production-grade Selenium Test Automation Framework** designed for testing an E-commerce website ([Magento Practice Site](https://magento.softwaretestingboard.com/)) using **Java, Selenium WebDriver, TestNG, Maven**, and **Page Object Model**. It supports **Allure reports**, **parallel execution**, **cross-browser testing**, and **CI integration via Jenkins**.

---

## Project Structure
```
<details> <summary><strong>Project Structure</strong></summary>
bash
Copy
Edit
EcommerceApplication
├── src
│   ├── main
│   │   └── java
│   │       ├── configuration     # Environment configs
│   │       ├── driver            # WebDriver setup
│   │       ├── exceptions        # Custom exceptions
│   │       ├── factory           # PageFactory manager
│   │       ├── logsUtil          # Logger utils
│   │       ├── models            # Test data models
│   │       ├── pageClass         # Page Object classes (POM)
│   │       ├── services          # UI helpers & actions
│   │       ├── util              # Common utilities
│   │       └── wait              # Custom WaitUtils
│   └── resources                 # Optional main resources
│
├── src
│   ├── test
│   │   └── java
│   │       ├── assertions        # Custom assertion wrappers
│   │       ├── base              # Base test setup
│   │       ├── listeners         # TestNG/Allure listeners
│   │       ├── testCases         # Test classes (Create, Login, Welcome)
│   │       └── utils             # Test-specific helpers
│   └── resources
│       ├── config                # env.properties, browser config
│       ├── testData             # JSON/Excel test data
│       └── log4j2.xml           # Log4j2 config
│
├── ci                           # CI scripts like ci-script.bat, generate-allure.bat
├── logs                         # Per-test logs
├── screenshots                  # Screenshots on failure
├── testSuite_XML                # testng.xml, Jenkinsfile
├── target                       # Maven output
├── pom.xml                      # Maven project file
└── README.md                    # Project documentation
</details>
```
## Features

- 🔹 Selenium WebDriver with Java 17
- 🔹 TestNG with `@DataProvider` support
- 🔹 Page Object Model (POM) with `@FindBy`
- 🔹 Custom Assertions and WaitUtils
- 🔹 Multiple validation error support (Create/Login)
- 🔹 JSON/Excel Data-Driven Testing
- 🔹 Dynamic user creation for Login tests
- 🔹 Allure Reporting with step logging
- 🔹 Screenshots on failure
- 🔹 Log4j2 test-level logs
- 🔹 Cross-browser execution
- 🔹 Parallel test execution
- 🔹 Jenkins integration with `ci-script.bat`

---

## How to Run Tests

### Run All Tests

```bash
mvn clean test

Run Specific Suite:
mvn clean test -DsuiteXmlFile=testSuite_XML/testng.xml

Allure Report
Generate & View Allure Report:
allure serve target/allure-results
Or use provided script:
ci\generate-allure.bat

Jenkins Integration
 Add JDK 11, Maven, and Allure Commandline in Jenkins global tools.

 Use ci/ci-script.bat inside your Jenkins pipeline job.

 GitHub Credentials ID: github-creds

 Set Jenkinsfile location: testSuite_XML/Jenkinsfile
