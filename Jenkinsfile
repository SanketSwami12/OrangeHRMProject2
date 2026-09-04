pipeline {

    agent any

    stages {

        stage('Checkout') {

            steps {

                checkout scm

            }

        }

        stage('Build') {

            steps {

                bat 'mvn clean compile'

            }

        }

        stage('Run Tests') {

            steps {

                bat 'mvn test'

            }

        }

    }

    post {

        always {

            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'src/test/resources/ExtentReport',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])

            echo 'Pipeline execution completed.'

        }

        success {

            echo 'Build and tests passed successfully.'

        }

        failure {

            echo 'Build or tests failed.'

        }

    }

}