package id.usecase.dakon.screens.play


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.usecase.dakon.ui.components.GoalContainer
import id.usecase.dakon.ui.components.PlayerRow
import id.usecase.dakon.ui.theme.DakonTheme
import kotlinx.coroutines.launch

@Composable
fun PlayScreen(
    viewModel: PlayViewModel
) {
    val gameState by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Player 2 Row
            PlayerRow(
                containers = gameState.player2Containers,
                isCurrentPlayer = gameState.currentPlayer == 2,
                onContainerClick = { position ->
                    scope.launch {
                        viewModel.moveMarbles(position, 2)
                    }
                },
                modifier = Modifier.padding(top = 33.dp)
            )

            // Middle Section with Goals
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Player 1 Goal
                GoalContainer(
                    score = gameState.player1Goal,
                    modifier = Modifier.width(102.dp)
                )

                // Player Turn Indicator
                Text(
                    text = "Player ${gameState.currentPlayer}'s Turn",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 28.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                )

                // Player 2 Goal
                GoalContainer(
                    score = gameState.player2Goal,
                    modifier = Modifier.width(102.dp)
                )
            }

            // Player 1 Row
            PlayerRow(
                containers = gameState.player1Containers,
                isCurrentPlayer = gameState.currentPlayer == 1,
                onContainerClick = { position ->
                    scope.launch {
                        viewModel.moveMarbles(position, 1)
                    }
                },
                modifier = Modifier.padding(bottom = 33.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun PlayScreenPreview() {
    DakonTheme {
        PlayScreen(
            viewModel = PlayViewModel()
        )
    }
}