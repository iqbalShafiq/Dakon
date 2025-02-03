package id.usecase.dakon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.usecase.dakon.R
import id.usecase.dakon.ui.theme.DakonTheme

@Composable
fun MainMenuScreen(
    onPlayClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.play_dakon),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ) {
                IconButton(
                    onClick = onPlayClick,
                    modifier = Modifier.size(77.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_play),
                        contentDescription = "Play"
                    )
                }

                IconButton(
                    onClick = onHelpClick,
                    modifier = Modifier.size(77.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_help),
                        contentDescription = "Help"
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun MainMenuScreenPreview() {
    DakonTheme {
        MainMenuScreen(
            onPlayClick = {},
            onHelpClick = {}
        )
    }
}