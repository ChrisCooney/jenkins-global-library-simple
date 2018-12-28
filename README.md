# Jenkins Global Shared Library Example

Jenkins Global Shared libraries over a powerful way of centralising the functionality that will typically be duplicated across many `Jenkinsfile` pipelines across your application repositories.

## General Structure

* There are component files that represent collections of functionality that are similar, for example maven commands

* There are pipeline files that represent a specific type of pipeline, for example the `JavaPipeline` file.

* There is an overall Pipelines file. This is the interface that downstream users will interact with. This prevents any internal changes to the files themselves from breaking everyones builds.

## Example

There is a Jenkinsfile at the root of this repository that pulls in the libraries and shows how they might be used.

## Shared Libraries have some QUIRKS!

Yep, it's Groovy and it's Jenkins so you've got to bare a few things in mind.

### No classes

You can't use classes if you want to access Jenkins pipeline methods like `sh`. Instead, you need to do a little groovy trick.

```groovy
package com.example.jenkins

def doSomethingCool() {
    sh "./run-my-script.sh"
}

return this
```
This will give you access to the delegate and owner fields, which contains `sh`, `echo` and all the other DSL functions that Jenkins will populate a `Jenkinsfile` with.

### No Fields!

As a consequence of the first quirk, you can't do something like...

```groovy
package com.example.jenkins

def MY_CONSTANT_VALUE = 'hello'

def doSomething() {
    // Nope, it won't compile.
    echo "${MY_CONSTANT_VALUE}"
}

return this
```

So instead, you need to wrap the values as functions...

```groovy
def myConstantValue() { 'hello' }
```

### NO STATIC!

If it's static, it doesn't have the same `owner`, `delegate` and `this` value as the code that has been invoked by the Jenkins server directly. That means if you want to run `sh`, it needs to be an instance method. This is a quirk of Groovy closures, which you can read more about [here](https://dzone.com/articles/groovy-closures-owner-delegate).