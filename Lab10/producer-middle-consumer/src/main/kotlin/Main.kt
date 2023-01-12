import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

val channels = mutableListOf<Channel<String>>()
val customerChannel = Channel<String>()
val intermediariesNumber = 7;

fun myCopy(num: Int): Int {
    return num
}

fun main() = runBlocking<Unit> {

    for (id in 0..intermediariesNumber-1) {
        val channel = Channel<String>()
        channels.add(channel);
    }

    launch {
        producer(channels)
    }

    for(chanIdx in 0..channels.size-1) {
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
        outChannels.get((0..intermediariesNumber-1).random()).send("product: $productNumber")
        println("send product: $productNumber")
        productNumber++
    }
}

fun CoroutineScope.intermediar(inside: ReceiveChannel<String>, outside: SendChannel<String>, name: Int) = produce<String> {
    while (true) {
        delay(500)
        var product = inside.receive()
        println("process $product by $name")
        outside.send("processed $product")
    }
}

fun CoroutineScope.consumer(inside: ReceiveChannel<String>) = produce<String> {
    while (true) {
        delay(100)
        var product = inside.receive()
        println("consume $product")
    }
}

