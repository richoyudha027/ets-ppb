package com.example.booksports.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksports.R

@Composable
fun ConfirmationPage(onHomeClick: () -> Unit) {
    val gradientColors = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF5E8DF2),
            Color(0xFF61D7BC)
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.pngwing_com__1_),
                contentDescription = "Check Mark",
                modifier = Modifier.size(200.dp),
                tint = Color.Unspecified
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Pemesanan telah Dikonfirmasi",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .padding(24.dp)
                .background(gradientColors, shape = RoundedCornerShape(32.dp))
        ) {
            Button(
                onClick = { onHomeClick() },
                modifier = Modifier
                    .height(50.dp)
                    .align(Alignment.Center),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Back to Dashboard",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ConfirmPagePrev() {
    ConfirmationPage(onHomeClick = {})
}