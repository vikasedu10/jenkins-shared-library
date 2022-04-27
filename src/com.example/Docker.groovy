#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String ImageName) {
        script.echo "Building Docker image for '$script.BRANCH_NAME' Node application"
        script.sh "docker build -t $ImageName ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            script.sh "echo $script.PASSWORD | docker login -u $script.USERNAME --password-stdin"
        }
    } 

    def dockerPush(String ImageName) {
        script.sh "docker push $ImageName"
    }
}