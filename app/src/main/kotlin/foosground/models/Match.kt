package foosground.models

import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

enum class MatchStatus {
    CREATED,
    PLAYED,
    ABORTED
}

data class Match(
    val matchId: UUID,
    val startDateTime: LocalDateTime,
    val duration: Duration,
    val table: Table,
    val owner: Player,
    val team1: Team,
    val team2: Team,
    val status: MatchStatus,
    val createDate: LocalDateTime,
    val lastUpdateDate: LocalDateTime
)
