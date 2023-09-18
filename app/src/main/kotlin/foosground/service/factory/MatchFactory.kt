package foosground.service.factory

import foosground.commons.Constants
import foosground.models.Match
import foosground.models.Table
import foosground.models.Team
import java.time.Duration
import java.time.LocalDateTime

interface MatchFactory {
    fun createMatch(
        table: Table,
        team1: Team,
        team2: Team,
        startDateTime: LocalDateTime,
        duration: Duration? = Constants.DEFAULT_DURATION
    ): Match
}