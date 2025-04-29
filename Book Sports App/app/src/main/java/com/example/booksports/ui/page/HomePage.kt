package com.example.booksports.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksports.ui.components.Footer
import com.example.booksports.ui.components.Header
import com.example.booksports.ui.components.Content

@Composable
fun HomePage(
    onBookingClick: () -> Unit,
    onBookingListClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Footer(
                onHomeClick = {},
                onBookingListClick = onBookingListClick
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Header()
                Content(onBookingClick = onBookingClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(onBookingClick = {}, onBookingListClick = {})
}