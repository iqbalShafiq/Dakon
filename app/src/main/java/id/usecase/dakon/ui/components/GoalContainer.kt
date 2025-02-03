@file:OptIn(ExperimentalAnimationApi::class)

package id.usecase.dakon.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.usecase.dakon.ui.theme.DakonTheme

@Composable
fun GoalContainer(
    score: Int,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false
) {
    // Animation for the container when score changes
    val transition = updateTransition(score, label = "scoreTransition")

    // Scale animation when score changes
    val scale by transition.animateFloat(
        label = "scaleAnimation",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        }
    ) { targetScore ->
        if (targetScore > score) 1.2f else 1f
    }

    // Continuous shine animation for highlighting
    val infiniteTransition = rememberInfiniteTransition()
    val shine by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = if (isHighlighted) {
                        listOf(
                            Color(0xFF8BC34A).copy(alpha = 0.8f + (0.2f * shine)),
                            Color(0xFF4CAF50).copy(alpha = 0.8f + (0.2f * shine))
                        )
                    } else {
                        listOf(
                            Color(0xFF8BC34A),
                            Color(0xFF4CAF50)
                        )
                    }
                )
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated score display
            AnimatedContent(
                targetState = score,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }
            ) { targetScore ->
                Text(
                    text = targetScore.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 36.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            // Optional: Add "Lumbung" label
            Text(
                text = "Lumbung",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun DakonGoalContainerPreview() {
    DakonTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GoalContainer(
                score = 12,
                modifier = Modifier.width(102.dp),
                isHighlighted = true
            )

            GoalContainer(
                score = 8,
                modifier = Modifier.width(102.dp)
            )
        }
    }
}