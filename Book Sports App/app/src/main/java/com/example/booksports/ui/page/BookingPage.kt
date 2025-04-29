@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.booksports.ui.page

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksports.ui.components.Footer
import java.util.*

@Composable
fun BookingPage(
    onHomeClick: () -> Unit,
    onConfirmationClick: () -> Unit
) {
    Scaffold(
        topBar = { BookingHeader(onHomeClick = onHomeClick) },
        bottomBar = { Footer() }
    ) { innerPadding ->
        BookingForm(
            Modifier.padding(innerPadding),
            onConfirmationClick = onConfirmationClick
        )
    }
}

@Composable
fun BookingHeader(onHomeClick: () -> Unit) {
    val gradientColors = listOf(
        Color(0xFF5E8DF2),
        Color(0xFF61D7BC)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                ),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            IconButton(onClick = onHomeClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Booking Lapangan",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BookingForm(
    modifier: Modifier = Modifier,
    onConfirmationClick: () -> Unit,
) {
    val context = LocalContext.current
    var tempatOlahraga by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var waktuMasuk by remember { mutableStateOf("") }
    var waktuKeluar by remember { mutableStateOf("") }
    var jenisOlahraga by remember { mutableStateOf("") }
    var namaPemesan by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }

    val tempat = listOf(
        "Fiva Sport Futsal",
        "Lap. Basket Indoor ITS",
        "Gor CLS Kertajaya",
        "Zuper Badminton",
        "Gor Pertamina ITS",
        "Lap. Tenis ITS",
        "Lap. Tenis SIER",
        "De Lano Sport Badminton"
    )

    val waktuOptions = List(13) { (10 + it).toString().padStart(2, '0') + ":00" }
    val gradientColors = listOf(
        Color(0xFF5E8DF2),
        Color(0xFF61D7BC)
    )

    LaunchedEffect(tempatOlahraga) {
        jenisOlahraga = when (tempatOlahraga) {
            "Fiva Sport Futsal", "Gor Pertamina ITS" -> "Futsal"
            "Lap. Basket Indoor ITS", "Gor CLS Kertajaya" -> "Basket"
            "De Lano Sport Badminton", "Zuper Badminton" -> "Badminton"
            "Lap. Tenis ITS", "Lap. Tenis SIER" -> "Tenis"
            else -> "Jenis Olahraga"
        }
    }

    LaunchedEffect(tempatOlahraga, tanggal, waktuMasuk, waktuKeluar, namaPemesan, email, phoneNumber, emailError) {
        isFormValid = tempatOlahraga.isNotEmpty() &&
                tanggal.isNotEmpty() &&
                waktuMasuk.isNotEmpty() &&
                waktuKeluar.isNotEmpty() &&
                namaPemesan.text.isNotEmpty() &&
                email.isNotEmpty() &&
                phoneNumber.isNotEmpty() &&
                emailError.isEmpty()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExposedDropdownMenuBoxSample(label = "Tempat Olahraga", options = tempat, selectedOption = tempatOlahraga) {
            tempatOlahraga = it
        }

        OutlinedTextField(
            value = jenisOlahraga ?: "Jenis Olahraga",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            label = { Text("Jenis Olahraga") }
        )

        OutlinedTextField(
            value = namaPemesan,
            onValueChange = { namaPemesan = it },
            label = { Text("Nama Pemesan") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
                emailError = when {
                    "@" !in newEmail -> "Email harus mengandung karakter '@'"
                    "." !in newEmail.substringAfter("@") -> "Email harus mengandung '.' setelah '@'"
                    else -> ""
                }
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty(),
            placeholder = { Text("Masukkan email anda") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (emailError.isEmpty()) Color.Green else Color.Red,
                unfocusedBorderColor = if (emailError.isEmpty()) Color.Gray else Color.Red
            )
        )

        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newPhoneNumber ->
                if (newPhoneNumber.length <= 12 && newPhoneNumber.all { it.isDigit() }) {
                    phoneNumber = newPhoneNumber
                }
            },
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = VisualTransformation.None
        )

        OutlinedTextField(
            value = tanggal,
            onValueChange = { tanggal = it },
            label = { Text("Tanggal Booking") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            tanggal = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
                }
            }
        )

        ExposedDropdownMenuBoxSample(label = "Waktu Masuk", options = waktuOptions, selectedOption = waktuMasuk) {
            waktuMasuk = it
        }

        ExposedDropdownMenuBoxSample(label = "Waktu Keluar", options = waktuOptions, selectedOption = waktuKeluar) {
            waktuKeluar = it
        }

        Button(
            onClick = {
                if (isFormValid) {
                    onConfirmationClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(),
            enabled = isFormValid
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = gradientColors
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("KONFIRMASI BOOKING", color = Color.White)
            }
        }
    }
}

@Composable
fun ExposedDropdownMenuBoxSample(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember(selectedOption) { mutableStateOf(selectedOption) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedText = selectionOption
                        onOptionSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    BookingPage(
        onHomeClick = {},
        onConfirmationClick = {}
    )
}