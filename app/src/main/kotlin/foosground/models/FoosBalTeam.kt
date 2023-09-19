package foosground.models


interface Team
data class FoosballTeam(
    val player1: Player,
    val player2: Player
) : Team

data class SingleTableTennisTeam(
    val player: Player
) : Team

data class DoublesTableTennisTeam(
    val player1: Player,
    val player2: Player
) : Team
