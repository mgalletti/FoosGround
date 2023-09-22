/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package foosground

import foosground.models.DoublesTableTennisTeam
import foosground.models.Player
import foosground.models.Table
import foosground.models.FoosballTeam
import foosground.models.Match
import foosground.models.SingleTableTennisTeam
import foosground.models.serialization.Serializer
import foosground.service.factory.FoosBallMatchFactory
import foosground.service.factory.TennisTableMatchFactory
import foosground.storage.match.InMemoryMatchStorageClient
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class App {
    private fun setUpFoosballMatches() {
        // create matches
        val match1 = FoosBallMatchFactory(Player("bob")).createMatch(
            team1 = FoosballTeam(Player("bob"), Player("alice")),
            team2 = FoosballTeam(Player("john"), Player("sue")),
            table = Table("table1", "MILANO_1", "ROOM.01"),
            startDateTime = LocalDateTime.parse("2023-01-01T11:00:00"),
        )
        val match2 = FoosBallMatchFactory(Player("matteo g,")).createMatch(
            team1 = FoosballTeam(Player("matteo g."), Player("fabio")),
            team2 = FoosballTeam(Player("francesco"), Player("matteo p.")),
            table = Table("table1", "MILANO_1", "ROOM.02"),
            startDateTime = LocalDateTime.parse("2023-01-02T11:00:00")
        )
        val match3 = FoosBallMatchFactory(Player("alan")).createMatch(
            team1 = FoosballTeam(Player("alan"), Player("kelly")),
            team2 = FoosballTeam(Player("paul"), Player("sarah")),
            table = Table("table1", "DUBLIN_1", "ROOM.LG01"),
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
        val building = "MILANO_1"
        println("By building: $building")
        val matchByBuilding = client.listMatches(building)
        matchByBuilding.forEach {
            println(it.toString())
        }
        // filter by date
        val matchDate = "2023-01-01"
        println("By date: $matchDate")
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        val matchByDateTime = client.listMatches(formatter.parse(matchDate))
        matchByDateTime.forEach {
            println(it.toString())
        }
    }

    // example of extending class models to tennis table matches
    fun setUpTennisTableMatches() {
        // create tennis table singles match
        val matchOfSingles: Match = TennisTableMatchFactory(Player("bob")).createMatch(
            team1 = SingleTableTennisTeam(Player("bob")),
            team2 = SingleTableTennisTeam(Player("john")),
            table = Table("table1", "MILANO_1", "ROOM.01"),
            startDateTime = LocalDateTime.parse("2023-01-01T11:00:00"),
        )

        // create tennis table doubles match
        val matchOfDoubles: Match = TennisTableMatchFactory(Player("bob")).createMatch(
            team1 = DoublesTableTennisTeam(Player("bob"), Player("alice")),
            team2 = DoublesTableTennisTeam(Player("john"), Player("sue")),
            table = Table("table1", "MILANO_1", "ROOM.01"),
            startDateTime = LocalDateTime.parse("2023-01-01T11:00:00"),
        )
        val serializer = Serializer()
        println("model to string")
        println(serializer.serialize(matchOfDoubles))
    }
    fun test() {
        // run test on foosball
        setUpFoosballMatches()
        // run test on tennis table
        setUpTennisTableMatches()
    }
}

fun main() {
    val app = App()
    app.test()
}