package com.example.booksports.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksports.ui.components.Footer

@Composable
fun BookingListPage(
    onBackClick: () -> Unit
) {
    val bookings = listOf(
        Booking("Gor CLS Kertajaya", "Basket", "01/05/2025", "10.00", "12.00", "Ayu Rahma", "0812 3456 7890", "ayu.rahma@gmail.com"),
        Booking("Lap. Tenis ITS", "Tenis", "02/05/2025", "15.00", "17.00", "Budi Santoso", "0821 6543 9876", "budi.san@gmail.com"),
        Booking("Gor Pertamina ITS", "Futsal", "30/4/2025", "18.00", "22.00", "Richa", "0816 4273 1238", "richa.kumia@gmail.com"),
        Booking("Fiva Sport Futsal", "Futsal", "05/05/2025", "13.00", "15.00", "Dina Putri", "0899 1122 3344", "dina.putri@mail.com"),
        Booking("De Lano Sport Badminton", "Badminton", "06/05/2025", "10.00", "12.00", "Eko Saputra", "0877 5566 7788", "eko.saputra@gmail.com")
    )

    Scaffold(
        bottomBar = {
            Footer(
                onHomeClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp),
            ) {
                item {
                    Header(onBackClick)
                }
                items(bookings) { booking ->
                    BookingCard(booking)
                }
            }
        }
    }
}

@Composable
fun Header(onBackClick: () -> Unit) {
    val gradientColors = listOf(
        Color(0xFF5E8DF2),
        Color(0xFF61D7BC)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                ),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(60.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "List Pemesanan",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BookingCard(booking: Booking) {
    Spacer(Modifier.height(24.dp))
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFF61D7BC)
                        )
                    )
                )
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text(text = booking.place, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = booking.sport, fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Tanggal: ${booking.date}       Pukul: ${booking.startTime} - ${booking.endTime}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = booking.name, color = Color(0xFF5E8DF2), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = booking.email, color = Color(0xFF5E8DF2), fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = booking.phone, color = Color(0xFF5E8DF2), fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

data class Booking(
    val place: String,
    val sport: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val name: String,
    val phone: String,
    val email: String
)

@Preview(showSystemUi = true)
@Composable
fun BookingListScreenPreview() {
    BookingListPage(
        onBackClick = {}
    )
}