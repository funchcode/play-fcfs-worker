package io.github.funch.fcfs.common

class Configs {

    companion object Constants : Config {

        private var targetConfig: Config = PublicConfig

        override fun getAwsSqsUrl(): String {
            return targetConfig.getAwsSqsUrl()
        }

        override fun getAwsCredentialAccessKey(): String {
            return targetConfig.getAwsCredentialAccessKey()
        }

        override fun getAwsCredentialSecretKey(): String {
            return targetConfig.getAwsCredentialSecretKey()
        }

        fun setConfig(config: Config) {
            targetConfig = config
        }

    }

}