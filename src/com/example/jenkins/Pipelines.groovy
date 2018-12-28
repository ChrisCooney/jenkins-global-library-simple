package com.example.jenkins

import com.example.jenkins.pipelines.JavaPipeline

/**
    The developer invokes the pipeline via this method 
    and have no visibility of how that pipeline is constructed.
*/
def javaPipeline() {
    JavaPipeline pipeline = new JavaPipeline()
    pipeline.javaPipeline()
}

return this