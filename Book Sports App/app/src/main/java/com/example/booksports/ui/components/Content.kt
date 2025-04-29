package com.example.booksports.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksports.R

@Composable
fun Content(onBookingClick: () -> Unit){
    var searchText by remember { mutableStateOf("") }
    var selectedSportFilter by remember { mutableStateOf<String?>(null) }

    val gradientColors = listOf(
        Color(0xFF5E8DF2),
        Color(0xFF61D7BC)
    )

    val sportList = listOf(
        Sport(R.drawable.basket, "Basket"),
        Sport(R.drawable.bola, "Futsal"),
        Sport(R.drawable.tenis, "Tenis"),
        Sport(R.drawable.badmin, "Badminton")
    )

    val imageList = listOf(
        FieldPict(R.drawable.fiva, "Fiva Sport Futsal", "Jl. Bumi Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.basket_indoor_its, "Lap. Basket Indoor ITS", "Jl. ITS Raya, Keputih", "10.00 WIB", "22.00 WIB", 80000),
        FieldPict(R.drawable.cls_sby, "Gor CLS Kertajaya", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.zuper, "Zuper Badminton", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.gor_pertamina, "Gor Pertamina ITS", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.lap_tenis, "Lap. Tenis ITS", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.sier, "Lap. Tenis SIER", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000),
        FieldPict(R.drawable.delano, "De Lano Sport Badminton", "JL BUMI Jl. Marina Emas Barat I No.15", "10.00 WIB", "22.00 WIB", 40000)
    )

    // Fungsi untuk filter data berdasarkan search dan jenis olahraga
    val filteredImageList = imageList.filter { field ->
        val matchesSearch = if (searchText.isBlank()) true
        else field.name.contains(searchText, ignoreCase = true) ||
                field.addres.contains(searchText, ignoreCase = true)

        val matchesSport = if (selectedSportFilter == null) true
        else when (selectedSportFilter) {
            "Basket" -> field.name.contains("Basket", ignoreCase = true)
            "Futsal" -> field.name.contains("Futsal", ignoreCase = true) || field.name.contains("Gor", ignoreCase = true)
            "Tenis" -> field.name.contains("Tenis", ignoreCase = true)
            "Badminton" -> field.name.contains("Badminton", ignoreCase = true)
            else -> true
        }

        matchesSearch && matchesSport
    }

    Spacer(Modifier.height(24.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Search Field
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Cari tempat olahraga favoritmu!", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear Search",
                            tint = Color.Gray
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 4.dp,
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        // Sport Filter Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            sportList.forEach { sport ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Ubah background icon saat dipilih
                    val isSelected = selectedSportFilter == sport.name
                    val iconBackground = if (isSelected) {
                        Modifier
                            .size(64.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF61D7BC).copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                ),
                                shape = RoundedCornerShape(32.dp)
                            )
                    } else {
                        Modifier.size(64.dp)
                    }

                    IconButton(
                        onClick = {
                            selectedSportFilter = if (selectedSportFilter == sport.name) null else sport.name
                        },
                        modifier = iconBackground
                    ) {
                        Icon(
                            painter = painterResource(id = sport.iconResId),
                            contentDescription = sport.name,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = sport.name,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                            color = if (isSelected) Color(0xFF5E8DF2) else Color.Black
                        )
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Judul dan indikator filter aktif
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mau olahraga apa hari ini?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.weight(1f)
            )

            if (searchText.isNotEmpty() || selectedSportFilter != null) {
                Text(
                    text = "Filter aktif",
                    fontSize = 12.sp,
                    color = Color(0xFF5E8DF2),
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Tampilan hasil pencarian
        if (filteredImageList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada hasil yang ditemukan",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            // Dynamic field display dengan grid layout konsisten
            repeat((filteredImageList.size + 1) / 2) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    val startIndex = rowIndex * 2
                    val endIndex = minOf(startIndex + 2, filteredImageList.size)
                    // Untuk setiap item dalam row
                    filteredImageList.slice(startIndex until endIndex).forEach { field ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Image(
                                painter = painterResource(id = field.fieldPictResId),
                                contentDescription = field.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = field.name,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = field.addres,
                                fontSize = 8.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Column(
                                    horizontalAlignment = Alignment.Start
                                ){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(0.7f),
                                        text = buildAnnotatedString {
                                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                                append("Jam Buka: ")
                                            }
                                            append(field.open)
                                            append("  ")
                                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                                append("Jam Tutup: ")
                                            }
                                            append(field.close)
                                        },
                                        fontSize = 5.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.7f)
                                            .height(25.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color(0xFF61D7BC))
                                            .padding(vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                                        ){
                                            Icon(
                                                painter = painterResource(R.drawable.mask_group__1_),
                                                contentDescription = "Harga",
                                                modifier = Modifier.size(20.dp),
                                                tint = Color.Unspecified
                                            )
                                            Text(
                                                text = "Rp.${field.price}/jam",
                                                style = TextStyle(
                                                    fontSize = 11.sp,
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.CenterVertically),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(
                                        onClick = { onBookingClick() },
                                        modifier = Modifier.size(40.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.kerajnajng_icon__1_),
                                            contentDescription = "Beli",
                                            tint = Color.Unspecified
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Jika hanya ada 1 item di row, tambahkan spacer kosong dengan weight yang sama
                    if (filteredImageList.slice(startIndex until endIndex).size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                if (rowIndex < (filteredImageList.size + 1) / 2 - 1) {
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

data class Sport(
    val iconResId: Int,
    val name: String
)

data class FieldPict(
    val fieldPictResId: Int,
    val name: String,
    val addres: String,
    val open: String,
    val close: String,
    val price: Int
)

@Preview(showBackground = true)
@Composable
fun ContentPrev(){
    Content(
        onBookingClick = {}
    )
}