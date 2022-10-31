import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.logging.LogLevel
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*

fun startPolling() {

    val bot = bot {

        token = getLineFromFile("token.txt")
        logLevel = LogLevel.Error

        dispatch {

            command("start") {
                bot.sendMessage(ChatId.fromId(update.message!!.chat.id), genRandomInt(0, 100))
            }

            command("gen") {
                var min = 0
                var max = 100

                if (args.size == 1 && isNum(args[0])) {
                    max = args[0].toInt()
                } else {
                    if (args.size == 2 && isNum(args[0]) && isNum(args[1])) {
                        min = args[0].toInt()
                        max = args[1].toInt()
                    }
                }

                val result =
                    if (min < max) {
                        genRandomInt(min, max)
                    } else {
                        genRandomInt(max, min)
                    }

                bot.sendMessage(ChatId.fromId(message.chat.id), result, replyToMessageId = update.message!!.messageId)
            }
        }
    }

    bot.startPolling()
}

fun genRandomInt(min: Int, max: Int) = (Random().nextInt(max - min + 1) + min).toString()

fun isNum(str: String) = str.toIntOrNull() != null

fun getLineFromFile(filename: String): String =
    try {
        BufferedReader(FileReader(filename)).readLine()
    } catch (e: IOException) {
        println(e)
        ""
    }