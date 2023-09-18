/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package foosground

import foosground.models.Player
import foosground.models.Table
import foosground.models.Team
import foosground.service.factory.FoosBallMatchFactory
import foosground.storage.match.InMemoryMatchStorageClient
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class App {
    fun test() {
        // create matches
        val match1 = FoosBallMatchFactory(Player("bob")).createMatch(
            team1 = Team(Player("bob"), Player("alice")),
            team2 = Team(Player("john"), Player("sue")),
            table = Table("table1", "LIN11", "LIN11.04"),
            startDateTime = LocalDateTime.parse("2023-01-01T11:00:00")
        )
        val match2 = FoosBallMatchFactory(Player("mggallet")).createMatch(
            team1 = Team(Player("mmgallet"), Player("bob")),
            team2 = Team(Player("blofa"), Player("mpalaz")),
            table = Table("table1", "LIN11", "LIN11.04"),
            startDateTime = LocalDateTime.parse("2023-01-02T11:00:00")
        )
        val match3 = FoosBallMatchFactory(Player("alan")).createMatch(
            team1 = Team(Player("alan"), Player("kelly")),
            team2 = Team(Player("paul"), Player("sarah")),
            table = Table("table1", "DUB12", "DUB12.LG01"),
            startDateTime = LocalDateTime.parse("2023-01-01T16:00:00")
        )
        // create in-memory BD
        val client = InMemoryMatchStorageClient()
        // save
        client.saveMatch(match1)
        client.saveMatch(match2)
        client.saveMatch(match3)
        // filter by owner
        val matchByOwner = client.listMatches(Player("bob"))
        println("By owner: bob")
        matchByOwner.forEach {
            println(it.toString())
        }
        // filter by building
        println("By building: LIN11")
        val matchByBuilding = client.listMatches("LIN11")
        matchByBuilding.forEach {
            println(it.toString())
        }
        // filter by date
        println("By date: 2023-01-01")
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        val matchByDateTime = client.listMatches(formatter.parse("2023-01-01"))
        matchByDateTime.forEach {
            println(it.toString())
        }
    }
}

fun main() {
    val app = App()
    app.test()
}