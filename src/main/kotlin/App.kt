import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

fun main() {

//    general()
//    coroutine()
    coroutineAsyncAwait()

}

fun general() {
    val startTime = System.nanoTime()
    for (i in 1..20) {
        // 1초 걸리는 작업
        println("for-loop work: $i")
//        delay(1000L)
    }
    val endTime = System.nanoTime()
    val t = (endTime - startTime) / 1000000000
    println("for-loop: $t")
}

suspend fun doSomethingOne(): Int {
    println("start doSomethingOne")
    delay(10000L)
    return 1
}

suspend fun doSomethingTwo(): Int {
    println("start doSomethingTwo")
    delay(200L)
    return 2
}

fun coroutineAsyncAwait() {

    runBlocking {
        measureNanoTime {
            val one = async(start = CoroutineStart.LAZY) { doSomethingOne() }
            val two = async { doSomethingTwo() }
//            one.start()
            println("sum: ${one.await() + two.await()}")
        }
        println("Completed")
    }

}

fun coroutine() {

    runBlocking {
        println("runBlocking")
        val job = launch(Dispatchers.Default) {
            repeat(1000) { i ->
                println("$i")
                delay(500L)
            }
            println("coroutineScope01")
        }

        suspend fun a() {
            println("suspend")
            delay(3000L)
        }

        a() // 3seconds
        job.cancelAndJoin()

        coroutineScope {
            launch {
                println("coroutineScope02")
            }
        }

        runBlocking {
            println("inner runBlocking")
        }

    }

}