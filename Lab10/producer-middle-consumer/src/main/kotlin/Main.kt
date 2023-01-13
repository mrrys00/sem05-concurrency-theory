import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

val channels = mutableListOf<Channel<String>>()
val customerChannel = Channel<String>()
const val intermediariesNumber = 7;

fun myCopy(num: Int): Int {
    return num
}

fun main() = runBlocking<Unit> {

    for (id in 0 until intermediariesNumber) {
        val channel = Channel<String>()
        channels.add(channel);
    }

    launch {
        producer(channels)
    }

    for (chanIdx in 0 until channels.size) {
        launch {
            intermediar(channels[chanIdx], customerChannel, chanIdx)
        }
        println("launched $chanIdx")
    }

    launch {
        consumer(customerChannel)
    }
}

fun CoroutineScope.producer(outChannels: MutableList<Channel<String>>) = produce<String> {
    var productNumber = 0;
    while (true) {
        delay(100)
        select<String> {
            outChannels[(0 until intermediariesNumber).random()].onSend("product: $productNumber") {
                it.toString()
            }
        }
        println("send product: $productNumber")
        productNumber++
    }
}

fun CoroutineScope.intermediar(inside: ReceiveChannel<String>, outside: SendChannel<String>, name: Int) =
    produce<String> {
        val batch = mutableListOf<String>()
        while (true) {
            select<Unit> {
                inside.onReceive { value ->
                    batch.add(value)
                    println("process $value by $name")
                    delay(500)
                }
                if (batch.isNotEmpty())  {
                    val prod = batch[0]
                    outside.onSend("processed $prod") { }
                    batch.clear()
                }
            }
        }
    }

fun CoroutineScope.consumer(inside: ReceiveChannel<String>) = produce<String> {
    while (true) {
        delay(100)
        select<Unit> {
            inside.onReceive { value -> println("consume $value") }
        }
    }
}
