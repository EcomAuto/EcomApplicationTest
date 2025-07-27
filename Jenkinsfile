pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    tools {
        maven 'Maven4'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-creds', url: 'https://github.com/EcomAuto/EcomApplicationTest.git', branch: 'main'
            }
        }
        stage('Cleanup') {
            steps {
                bat 'ci\\cleanup.bat'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'ci\\ci-script.bat'
            }
        }
        stage('Allure Report') {
            steps {
                bat 'ci\\generate-allure.bat'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/*.xlsx', allowEmptyArchive: true
            archiveArtifacts artifacts: '**/screenshots/*.png', allowEmptyArchive: true

            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
