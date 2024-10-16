import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

const val HOST = "https://api.telegram.org"

fun main(args: Array<String>){

    val botToken = args[0]
    val urlGetMe = "$HOST/bot$botToken/getMe"
    val urlGetUpdates = "$HOST/bot$botToken/getUpdates"


    val client: HttpClient = HttpClient.newBuilder().build()

    val requestGetMe: HttpRequest = HttpRequest.newBuilder().uri(URI.create(urlGetMe)).build()
    val responseGetMe: HttpResponse<String> = client.send(requestGetMe, HttpResponse.BodyHandlers.ofString())

    println(responseGetMe.body())

    val requestGetUpdates: HttpRequest = HttpRequest.newBuilder().uri(URI.create(urlGetUpdates)).build()
    val responseGetUpdates: HttpResponse<String> = client.send(requestGetUpdates, HttpResponse.BodyHandlers.ofString())

    println(responseGetUpdates.body())

}