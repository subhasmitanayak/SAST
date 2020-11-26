#!groovy

pipeline {

  agent any

  environment {
    git_commit_message = ''
    git_commit_diff = ''
    git_commit_author = ''
    git_commit_author_name = ''
    git_commit_author_email = ''
  }

  stages {


	// SCM Checkout
    stage('SCM Checkout') {
       
      steps {
        deleteDir()
        //checkout scm
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '0febc8c8-f0a2-4ae7-a206-a6098136f074', url: 'https://github.com/subhasmitanayak/SAST-Demo-Project.git']]])
      }
    }
    
    // Build
    stage('Build') {
      
      steps {
	      sh "echo 'Build....'"
	   
        sh 'gradle --version'
      }
    }

stage('Frontend Static Code Analysis') {
    
            steps {
		     sh "echo 'Run Static Code Analysis'"
                script {
                    try {
                        sh './gradlew clean pmdMain --no-daemon'
                    } finally { //Make checkstyle results available
                        pmd canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/pmd.xml ', unHealthy: ''
                    }
                }
            }
        }
	// Build
    stage('Publish Report') {
      
      steps {
	      sh "echo 'Publish Report....'"
	   
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '**/reports/pmd/', reportFiles: 'main.html', reportName: 'HTML Report', reportTitles: ''])
      }
    }
  
	  
  }
}
