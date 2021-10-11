pipeline {
    options
    {
      buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    agent any

    environment {
      AWS_DEFAULT_REGION="us-east-1" 
      IMAGE_REPO_NAME="ss-utopia-auth"
      IMAGE_TAG="latest"
      REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }

    stages {
      stage('checkout') {
        steps {
          git branch: 'feature_kubernetes', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-auth.git'
        }
      }
       stage('get_commit_msg') {
        steps {
            script {
                env.GIT_COMMIT_MSG = sh (script: 'git log -1 --pretty=format:"%H"', returnStdout: true).trim()
            }
        }
      }
      stage("Clean install auth") {  
        steps {
          dir('Auth') {
            withMaven(maven: 'maven') {
           sh 'mvn clean package -Dmaven.test.skip' 
            }
           //-Dmaven.test.skip
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
          
          
          
          stage('Build Auth') {
            steps {
                  dir('Auth') {
                    sh 'docker build . -t ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/ss-utopia-auth:${GIT_COMMIT_MSG}'

                  }

                 
            }
        }
        stage('log into ecr') {
        steps {
            script{
                sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com'
            }
        }
      }
        stage('Deploy Auth') {
            steps {
                sh 'docker push ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/ss-utopia-auth:${GIT_COMMIT_MSG}'
                }
            }
            stage('Cleaning up') {
        steps{
            sh "docker rmi ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/ss-utopia-auth:${GIT_COMMIT_MSG}"
        }
        }
        }
        
    

}