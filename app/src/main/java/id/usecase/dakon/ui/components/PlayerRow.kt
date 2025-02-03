package id.usecase.dakon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.usecase.dakon.R
import id.usecase.dakon.ui.theme.DakonTheme

@Composable
fun PlayerRow(
    containers: List<Int>,
    isCurrentPlayer: Boolean,
    onContainerClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 48.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(containers.size) { position ->
            DakonContainer(
                marbleCount = containers[position],
                enabled = isCurrentPlayer && containers[position] > 0,
                onClick = { onContainerClick(position) }
            )
        }
    }
}

@Composable
fun DakonContainer(
    marbleCount: Int,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundRes = remember(marbleCount) {
        when (marbleCount) {
            0 -> R.drawable.bg_0
            in 1..9 -> R.drawable.bg_1 + (marbleCount - 1)
            else -> R.drawable.bg_0
        }
    }

    Box(
        modifier = modifier
            .clickable(enabled = enabled, onClick = onClick)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8BC34A),
                        Color(0xFF4CAF50)
                    )
                ),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (marbleCount > 9) {
            Text(
                text = marbleCount.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        } else {
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun PlayerRowPreview() {
    DakonTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PlayerRow(
                containers = List(7) { 7 },
                isCurrentPlayer = true,
                onContainerClick = {}
            )
        }
    }
}