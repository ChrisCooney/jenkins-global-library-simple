package com.example.jenkins.pipelines

def javaPipeline() {
    node {
        Maven maven = new Maven()

        pipeline {
            stage('Run a test') {
                maven.test()
            }
            stage('Build JAR') {
                maven.package()
            }
        }
    }
}

return this