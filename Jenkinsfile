pipeline {
    agent any 

  environment {
      DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
      VERSION = "${env.BUILD_ID}"

    }

    tools {
        maven 'maven-3.9.9' 
    }
    stages {
        stage ('Build maven') {
            steps {
                // Checkout the code from Git
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/RameshStonecold/transaction-demo']])

                // Run the Maven build command
                bat 'mvn clean package  -DskipTests'
            }
        }
        stage('Build docker image'){
            steps {
                script{
                    bat 'docker build -t RameshStonecold/transaction-demo:${VERSION} . .'
                }
            }
        }

        stage('Docker login and Push') {
              steps {
                  bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'

                  bat 'docker push RameshStonecold/transaction-demo::${VERSION}'
              }
        
        stage('Cleanup workspace'){
            steps{
                deleteDir()
            }
        }
        
   }
  }

