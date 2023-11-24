import io.github.funch.fcfs.SqsConsumer
import io.github.funch.fcfs.common.Configs
import io.github.funch.fcfs.common.PrivateConfig
import kotlinx.coroutines.runBlocking
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient

fun main() {
    Configs.setConfig(PrivateConfig)

    runBlocking {
        val sqs: SqsAsyncClient = SqsAsyncClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(Configs.getAwsCredentialAccessKey(), Configs.getAwsCredentialSecretKey())))
                .region(Region.AP_NORTHEAST_2)
                .build()
        SqsConsumer(sqs).start()
    }
}