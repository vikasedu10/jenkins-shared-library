#!/usr/bin/env groovy

package com.example

class Docker implements Seriali {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String ImageName) {
        script.echo "Building Docker image for '$script.BRANCH_NAME' Node application"
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            script.sh "docker build -t $ImageName ."
            script.sh "echo $script.PASSWORD | docker login -u $script.USERNAME --password-stdin"
            script.sh "docker push $ImageName"
        }
    } 
}