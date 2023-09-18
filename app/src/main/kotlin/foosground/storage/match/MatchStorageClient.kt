package foosground.storage.match

import foosground.models.Match
import foosground.models.Player
import java.util.Date
import java.util.UUID

interface MatchStorageClient {
    fun saveMatch(match: Match)
    fun getMatch(id: UUID): Match?
    fun listMatches(building: String): List<Match>
    fun listMatches(owner: Player): List<Match>
    fun listMatches(startDate: Date): List<Match>
}