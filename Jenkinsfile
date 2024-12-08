pipeline {
    agent any

    tools {
        maven "Maven 3.9.9"
    }
    environment{
        DOCKER_HUB_CREDENTIALS_ID = ''
        DOCKER_HUB_REPO = 'yorften/shopapi'
    }

    stages {
        stage('clone repo'){
            steps{
                git branch : 'main', url: 'https://github.com/JavaAura/Abderrahman_Badi_Sprint_4_B1_ShopAPI'
            }
        }
        stage('Build Artifact'){
            steps{
                sh '''
                    cd ./shopapi
                    mvn -B -DskipTests clean package 
                '''
                archiveArtifacts artifacts: 'shopapi/target/*.jar', fingerprint: true
            }
        }
    }
}