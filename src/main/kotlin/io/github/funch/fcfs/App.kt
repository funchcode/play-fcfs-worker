import io.github.funch.fcfs.SqsConsumer
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        SqsConsumer().start()
    }
}