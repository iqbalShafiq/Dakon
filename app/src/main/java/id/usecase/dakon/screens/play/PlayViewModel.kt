package id.usecase.dakon.screens.play

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayViewModel : ViewModel() {
    private val _state = MutableStateFlow(PlayState())
    val state = _state.asStateFlow()

    private val gameScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun moveMarbles(position: Int, player: Int) {
        gameScope.launch {
            if (state.value.currentPlayer != player) return@launch

            val containers = if (player == 1) {
                state.value.player1Containers.toMutableList()
            } else {
                state.value.player2Containers.toMutableList()
            }

            var marbles = containers[position]
            containers[position] = 0

            var currentPos = position

            while (marbles > 0) {
                delay(200)
                currentPos = (currentPos + 1) % containers.size
                containers[currentPos]++
                marbles--

                _state.update { current ->
                    if (player == 1) {
                        current.copy(player1Containers = containers)
                    } else {
                        current.copy(player2Containers = containers)
                    }
                }
            }

            // Check for game end and update player turn
            checkGameEnd()
            updatePlayerTurn()
        }
    }

    private fun checkGameEnd() {
        // Implement game end logic
    }

    private fun updatePlayerTurn() {
        _state.update { current ->
            current.copy(
                currentPlayer = if (current.currentPlayer == 1) 2 else 1
            )
        }
    }
}