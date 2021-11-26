package it.granz

import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import java.math.BigDecimal

class Zettai(val lists: Map<User, List<ToDoList>>) : HttpHandler {
    val routes = routes(
        "/todo/{user}/{list}" bind Method.GET to ::showList
    )

    override fun invoke(request: Request): Response = routes(request)

    private fun showList(request: Request): Response =
        request
            .let(::extractListData)
            .let(::fetchListContent)
            .let(::renderHtml)
            .let(::createResponse)

    fun extractListData(request: Request): Pair<User, ListName> {
        val user = request.path("user").orEmpty()
        val list = request.path("list").orEmpty()
        return User(user) to ListName(list)
    }

    fun fetchListContent(listId: Pair<User, ListName>): ToDoList {
        return lists[listId.first]
            ?.firstOrNull { it.listName == listId.second }
            ?: error("List unknown")
    }

    fun renderHtml(list: ToDoList): HtmlPage =
        HtmlPage(
            """
        <html>
            <body>
                <h1>Zettai</h1>
                <h2>${list.listName.name}</h2>
                <table>
                    <tbody>${renderItems(list.items)}</tbody>
                </table>
            </body>
        </html>
        """.trimIndent()
        )

    fun renderItems(items: List<ToDoItem>) =
        items.joinToString("") {
            """<tr><td>${it.description}</td></tr>""".trimIndent()
        }

    fun createResponse(html: HtmlPage): Response = Response(OK).body(html.raw)
}

data class ToDoList(val listName: ListName, val items: List<ToDoItem>)
data class ListName(val name: String)
data class User(val name: String)
data class ToDoItem(val description: String)
data class HtmlPage(val raw: String)
enum class ToDoStatus { Todo, InProgress, Done, Blocked }
