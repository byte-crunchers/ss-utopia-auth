pipeline {
    options
    {
      buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    agent any

    environment {
      AWS_ACCOUNT_ID="422288715120"
      AWS_DEFAULT_REGION="us-east-1" 
      IMAGE_REPO_NAME="ss-utopia-auth"
      IMAGE_REPO_NAME2="ss-utopia-sample-microservice"
      IMAGE_TAG="latest"
      REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
      REPOSITORY_URI2 = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME2}"
    }

    stages {
      stage('checkout') {
        steps {
          git branch: 'develop', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-auth.git'
        }
      }
      stage("Clean install auth") {  
        steps {
          dir('Auth') {
           sh 'mvn clean package -Dmaven.test.skip' 
           //-Dmaven.test.skip
          }
        }
      }
      stage("Clean install sample micro-service") {  
        steps {
          dir('SampleMicroservice'){
           sh 'mvn clean package -Dmaven.test.skip' 
          }
        }
      }
        
        stage("SonarQube analysis Auth") {
            steps {
              withSonarQubeEnv('SonarQube') {
                dir('Auth') {
                  sh 'mvn sonar:sonar'
                }
              }
            }
          }
          stage("Quality Gate Auth") {
            steps {
              echo message: "can not do on local machine "
             /* timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }*/
            }
          }
          
          stage("SonarQube analysis SampleMicroservice") {
            steps {
              withSonarQubeEnv('SonarQube') {
                dir('SampleMicroservice') {
                  sh 'mvn sonar:sonar'
                }
              }
            }
          }
           stage("Quality Gate SampleMicroservice") {
            steps {
              echo message: "can not do on local machine "
             /* timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }*/
            }
          }
          
          stage('Build Auth') {
            steps {
                  dir('Auth') {
                    sh 'docker build . -t ss-utopia-auth:latest'

                  }

                 
            }
        }
        stage('Deploy Auth') {
            steps {
                script{
                  docker.withRegistry("https://${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com", 'ecr:us-east-1:ss-AWS') 
                  {
                    docker.image('ss-utopia-auth').push('latest')
                  }
                }
            }
        }
        stage('Build SampleMicroservice') {
            steps {
                  dir('SampleMicroservice') {
                    sh 'docker build . -t ss-utopia-sample-microservice:latest'

                  }

                 
            }
        }
        stage('Deploy SampleMicroservice') {
            steps {
                script{
                  docker.withRegistry("https://${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com", 'ecr:us-east-1:ss-AWS') 
                  {
                    docker.image('ss-utopia-sample-microservice').push('latest')
                  }
                }
            }
        }
    }

}