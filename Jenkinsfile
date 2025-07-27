pipeline {
    agent any

    tools {
        maven 'Maven4'     // Configure this name in Jenkins Global Tool Config
        jdk 'JDK17'      // Or JDK-11 or JDK-8 depending on your setup
        allure 'Allure'   // Add this in Jenkins > Global Tool Config
    }


    environment {
        PATH = "${tool 'JDK17'}/bin:${env.PATH}"
    }

    options {
        timestamps()
        skipDefaultCheckout(false)
    }

    triggers {
        pollSCM('* * * * *') // Poll every minute (can be changed)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Cleanup') {
            steps {
                bat 'ci/cleanup.bat'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'ci/ci-script.bat'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'ci/generate-allure.bat'
            }
        }

        stage('Archive Results') {
            steps {
                archiveArtifacts artifacts: '**/screenshots/*.png', allowEmptyArchive: true
                junit '**/target/surefire-reports/*.xml'
                allure([
                    includeProperties: false,
                    jdk: 'JDK17',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'Build succeeded!'
        }
    }
}
