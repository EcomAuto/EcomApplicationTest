pipeline {
    agent any

    tools {
        maven 'Maven4'
        jdk 'JDK17'
    }

    environment {
        PATH = "${tool 'JDK17'}/bin:${env.PATH}"
    }

    options {
        timestamps()
        skipDefaultCheckout(false)
    }

    triggers {
        pollSCM('* * * * *')
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

        stage('Archive Results') {
            steps {
                archiveArtifacts artifacts: '**/screenshots/*.png', allowEmptyArchive: true
                junit '**/target/surefire-reports/*.xml'
                allure([
                    includeProperties: false,
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
