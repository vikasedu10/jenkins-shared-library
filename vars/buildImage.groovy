#!/usr/bin/env groovy

def call() {
    echo "Building Docker image for shared lib Node application"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t vikas1412/node-website:3.0 ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push vikas1412/node-website:3.0"
    }
}