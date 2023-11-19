package io.github.funch.fcfs.common

interface Config {

    fun getAwsCredentialAccessKey(): String
    fun getAwsCredentialSecretKey(): String
    fun getAwsRegion(): String

}