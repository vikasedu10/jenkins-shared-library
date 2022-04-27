#!/usr/bin/env groovy

def call(String ImageName) {
    echo "Building Docker image for '$BRANCH_NAME' branch Node application"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t $ImageName ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push $ImageName"
    }
}