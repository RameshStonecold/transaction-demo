pipeline {
    agent any 
  
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
                    bat 'docker build -t RameshStonecold/transaction-demo:0.0.1 .'
                }
            }
        }
        
        stage('Docker Registry'){
            steps{
                script{
                 withDockerRegistry(credentialsId: 'dockercId', url: 'https://registry-1.docker.io/v2/') {
                   bat 'docker push RameshStonecold/transaction-demo:0.0.1'
                }
                }
            }
        }
        
        stage('Cleanup workspace'){
            steps{
                deleteDir()
            }
        }
        
   }
  }

