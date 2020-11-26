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

stage('PMD Static Code Analysis') {
    
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
        // Publish PMD Report
    stage('Publish PMD Report') {
      
      steps {
	      sh "echo 'Publish Report....'"
	   
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/Users/apdfinanceangul/.jenkins/workspace/demo-project_master/build/reports/pmd', reportFiles: 'main.html', reportName: 'HTML Report', reportTitles: ''])
	      //publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'reports', reportFiles: 'main.html', reportName: 'HTML Report', reportTitles: ''])
      }
    }
    
    
    stage('Findbugs Static Code Analysis') {
    
            steps {
		     sh "echo 'Run Findbugs Static Code Analysis'"
                script {
			try {
                        sh './gradlew clean pmdMain --no-daemon'
                    } finally { //Make checkstyle results available
                        findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: '', unHealthy: ''
                    }
                    
                }
            }
        }
        // Publish Findbugs Report
    stage('Publish Findbugs Report') {
      
      steps {
	      sh "echo 'Publish  Find bugs Report....'"
	   	publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/Users/apdfinanceangul/.jenkins/workspace/demo-project_master/build/reports/pmd', reportFiles: 'main.html', reportName: 'HTML Report', reportTitles: ''])
      }
    }
    
    /*//CHECKMARX SAST
        stage('Checkmarx Static Code Analysis') {
    
            steps {
		     sh "echo 'Checkmarx Static Code Analysis'"
                script {
                    step([$class: 'CxScanBuilder', comment: '', credentialsId: 'admin', excludeFolders: '', exclusionsSetting: 'global', failBuildOnNewResults: false, failBuildOnNewSeverity: 'HIGH', filterPattern: '', fullScanCycle: 10, generatePdfReport: true, incremental: true, password: '{AQAAABAAAAAQ51vdCkusIgc5pxYvGP+tBt1lt+KvOH7YvuBXM+AqZNs=}', projectName: 'PMD-demo-project', sastEnabled: true, serverUrl: '', sourceEncoding: 'Provide Checkmarx server credentials to see source encodings list', username: '', vulnerabilityThresholdResult: 'FAILURE', waitForResultsEnabled: true])
                }
            }
        }*/
	
  
	  
  }
}
