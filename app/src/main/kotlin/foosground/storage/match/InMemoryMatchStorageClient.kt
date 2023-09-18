package foosground.storage.match

import foosground.models.Match
import foosground.models.Player
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.UUID

class InMemoryMatchStorageClient(): MatchStorageClient {
    companion object {
        private val memory = mutableMapOf<UUID, Match>()
        private val ownerIndex = mutableMapOf<Player, MutableSet<UUID>>()
        private val buildingIndex = mutableMapOf<String, MutableSet<UUID>>()
        private val startDateIndex = mutableMapOf<Date, MutableSet<UUID>>()
        val formatter = SimpleDateFormat("yyyy-mm-dd")

        private fun<K, T: MutableMap<K, MutableSet<UUID>>> updateMap(key: K, index: T, value: UUID) {
            if (index.containsKey(key)) {
                index[key]!!.add(value)
            } else {
                index[key] = mutableSetOf(value)
            }
        }

    }
    override fun saveMatch(match: Match) {
        if (memory.containsKey(match.matchId)) return
        // update memory
        memory[match.matchId] = match
        // update owner indexes
        updateMap(match.owner, ownerIndex, match.matchId)
        updateMap(match.table.building, buildingIndex, match.matchId)
        // get iso date
        val dateString = match.startDateTime.format(DateTimeFormatter.ISO_DATE)
        updateMap(formatter.parse(dateString), startDateIndex, match.matchId)
    }
    override fun getMatch(id: UUID): Match? {
        return memory[id]
    }

    override fun listMatches(building: String): List<Match> {
        return buildingIndex[building]?.map { memory[it]!! } ?: listOf()
    }

    override fun listMatches(owner: Player): List<Match> {
        return ownerIndex[owner]?.map { memory[it]!! } ?: listOf()
    }

    override fun listMatches(startDate: Date): List<Match> {
        return startDateIndex[startDate]?.map { memory[it]!! } ?: listOf()
    }
}