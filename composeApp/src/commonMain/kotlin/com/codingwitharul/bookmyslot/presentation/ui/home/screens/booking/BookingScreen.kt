package com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.logo_bml
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BookingScreen() {
    val viewModel: BookingViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {

    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Booking") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ),
            actions = {
                Icon(
                    painter = painterResource(Res.drawable.logo_bml),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
            ) {
                Text("Book a Service", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                // Horizontal date picker
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    state.availableDates.map { date ->
                        val isSelected = date == state.selectedDate
                        val hasSlots = true // Stub: assume all days have slots
                        Column(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.onEvent(BookingUiEvent.SelectDate(date)) }
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                    else if (hasSlots) Color.LightGray.copy(alpha = 0.2f)
                                    else Color.Transparent
                                )
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                // Use ISO format or custom formatting if needed
                                date.toString(),
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified
                            )
                            if (hasSlots) {
                                Box(
                                    Modifier
                                        .size(8.dp)
                                        .background(Color.Green, shape = MaterialTheme.shapes.small)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Time slots for selected date
                if (state.selectedDate != null) {
                    Text("Available Time Slots", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        state.availableTimeSlots.forEach { slot ->
                            val isSelected = slot == state.selectedTimeSlot
                            Button(
                                onClick = { viewModel.onEvent(BookingUiEvent.SelectTimeSlot(slot)) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
                                ),
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text(slot, color = if (isSelected) Color.White else Color.Black)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.onEvent(BookingUiEvent.BookSelectedSlot) },
                        enabled = state.selectedTimeSlot != null
                    ) {
                        Text("Book Now")
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Your Bookings", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                if (state.loading) {
                    CircularProgressIndicator()
                } else if (state.error.isNotEmpty()) {
                    Text(state.error, color = MaterialTheme.colorScheme.error)
                } else {
                    LazyColumn {
                        items(state.bookings) { booking ->
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                onClick = { viewModel.onEvent(BookingUiEvent.SelectBooking(booking)) }
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        booking.service.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text("${booking.date} - ${booking.timeSlot}")
                                    Text(booking.status.name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
} 