package com.example.jenkins.components

def test() {
    // Abstracting simple commands like this enables us to 
    // wrap them in other functionality, like for example
    // specifying custom maven config.
    sh 'mvn verify'
}

def package() {
    sh 'mvn package -Dmaven.test.skip=true'
}

return this