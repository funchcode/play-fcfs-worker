package io.github.funch.fcfs.common

class Configs {

    companion object Constants : Config {

        val targetConfig = PrivateConfig

        override fun getAwsSqsUrl(): String {
            return targetConfig.getAwsSqsUrl()
        }

        override fun getAwsCredentialAccessKey(): String {
            return targetConfig.getAwsCredentialAccessKey()
        }

        override fun getAwsCredentialSecretKey(): String {
            return targetConfig.getAwsCredentialSecretKey()
        }

    }

}