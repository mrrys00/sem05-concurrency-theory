import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

val channels = mutableListOf<Channel<String>>()
val customerChannel = Channel<String>()
val intermediariesNumber = 7;

fun myCopy(num: Int): Int {
    return num
}

fun main() = runBlocking<Unit> {

    for (id in 0..intermediariesNumber - 1) {
        val channel = Channel<String>()
        channels.add(channel);
    }

    launch {
        producer(channels)
    }

    for (chanIdx in 0..channels.size - 1) {
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
            outChannels.get((0..intermediariesNumber - 1).random()).onSend("product: $productNumber") {
                it.toString()
            }
        }
        println("send product: $productNumber")
        productNumber++
    }
}

fun CoroutineScope.intermediar(inside: ReceiveChannel<String>, outside: SendChannel<String>, name: Int) =
    produce<String> {
        var product: String = ""
        while (true) {
            delay(500)
            select<Unit> {
                inside.onReceive { value -> product = value }
                outside.onSend("processed $product") { }
            }
            println("process $product by $name")
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
