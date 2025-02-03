package id.usecase.dakon.screens.play

data class PlayState(
    val player1Containers: List<Int> = List(7) { 7 },
    val player2Containers: List<Int> = List(7) { 7 },
    val player1Goal: Int = 0,
    val player2Goal: Int = 0,
    val currentPlayer: Int = 1
)
