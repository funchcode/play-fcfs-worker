package io.github.funch.fcfs.common

interface Config {

    fun getAwsSqsUrl(): String
    fun getAwsCredentialAccessKey(): String
    fun getAwsCredentialSecretKey(): String

}