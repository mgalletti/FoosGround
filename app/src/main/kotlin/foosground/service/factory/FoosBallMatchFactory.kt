package foosground.service.factory


import foosground.commons.Constants
import foosground.models.Match
import foosground.models.MatchStatus
import foosground.models.Player
import foosground.models.Table
import foosground.models.Team
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID


class FoosBallMatchFactory(
    val owner: Player
): MatchFactory {

    override fun createMatch(
        table: Table,
        team1: Team,
        team2: Team,
        startDateTime: LocalDateTime,
        duration: Duration?
    ): Match {
        return Match(
            matchId = UUID.randomUUID(),
            startDateTime = startDateTime,
            duration = duration ?: Constants.DEFAULT_DURATION,
            table = table,
            owner = owner,
            team1 = team1,
            team2 = team2,
            status = MatchStatus.CREATED,
            createDate = LocalDateTime.now(),
            lastUpdateDate = LocalDateTime.now()
        )
    }

}