package io.github.funch.fcfs.common

class PublicConfig {

    companion object : Config {

        override fun getAwsSqsUrl(): String {
            return "{AWS_SQS_URL}"
        }

        override fun getAwsCredentialAccessKey(): String {
            return "{AWS_CREDENTIAL_ACCESSKEY}"
        }

        override fun getAwsCredentialSecretKey(): String {
            return "{AWS_CREDENTIAL_SECRETKEY}"
        }

    }

}