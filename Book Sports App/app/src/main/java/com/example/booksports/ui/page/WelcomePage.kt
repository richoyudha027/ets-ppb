package com.example.booksports.ui.page


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksports.R
import kotlinx.coroutines.delay

@Composable
fun WelcomePage(onTimeout: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        onTimeout()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.book_sports),
            contentDescription = "Book Sport Logo",
            modifier = Modifier.size(200.dp),
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePagePreview() {
    WelcomePage(onTimeout = {})
}