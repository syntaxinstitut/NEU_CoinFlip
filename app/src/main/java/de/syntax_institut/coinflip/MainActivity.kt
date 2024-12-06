package de.syntax_institut.coinflip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import de.syntax_institut.coinflip.ui.theme.SyntaxBlue
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var image by remember { mutableIntStateOf(R.drawable.kopf) }
            var text by remember { mutableStateOf("") }
            var targetAngle by remember { mutableFloatStateOf(0f) }
            val rotationAngle: Float by animateFloatAsState(
                targetValue = targetAngle,
                label = "rotation",
                finishedListener = {
                    if (Random.nextBoolean()) {
                        image = R.drawable.kopf
                        text = "Kopf!"
                    } else {
                        image = R.drawable.zahl
                        text = "Zahl!"
                    }
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .background(SyntaxBlue)
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 48.sp
                )
                Image(
                    painter = painterResource(image),
                    contentDescription = "Bild einer Münze",
                    modifier = Modifier.graphicsLayer(
                        rotationX = rotationAngle,
                        cameraDistance = 100f
                    )
                )
                Button(
                    onClick = {
                        text = ""
                        targetAngle += 36000f
                    }
                ) { Text("Wirf eine Münze") }
            }
        }
    }
}
