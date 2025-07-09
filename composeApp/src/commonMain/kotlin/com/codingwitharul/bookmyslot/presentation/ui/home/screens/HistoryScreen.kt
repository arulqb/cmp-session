package com.codingwitharul.bookmyslot.presentation.ui.home.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HistoryService(
    val customerName: String,
    val serviceDate: String,
    val serviceTime: String,
    val serviceType: String,
    val description: String,
    val totalPrice: String,
    val status: String
)

@Immutable
data class UpcomingService(
    val date: String,
    val serviceName: String,
    val location: String,
    val isPickup: Boolean,
    val time: String
)

val services = listOf(
    HistoryService(
        customerName = "John Doe",
        serviceDate = "2023-10-01",
        serviceTime = "10:30 AM",
        serviceType = "Grooming",
        description = "Pet Grooming & Bath",
        totalPrice = "Rp 300,000",
        status = "Completed"
    ),
    HistoryService(
        customerName = "Jane Smith",
        serviceDate = "2023-10-02",
        serviceTime = "02:15 PM",
        serviceType = "Haircut",
        description = "Men's Haircut",
        totalPrice = "Rp 150,000",
        status = "Pending"
    ),
    HistoryService(
        customerName = "Alice Johnson",
        serviceDate = "2023-10-03",
        serviceTime = "09:00 AM",
        serviceType = "Manicure",
        description = "French Manicure",
        totalPrice = "Rp 200,000",
        status = "Cancelled"
    )
)

val upcomingServices = listOf(
    UpcomingService(
        date = "2023-12-25",
        serviceName = "Hair Styling",
        location = "Chic Salon, Downtown",
        isPickup = false,
        time = "10:00 AM"
    ),
    UpcomingService(
        date = "2023-12-28",
        serviceName = "Spa Day Package",
        location = "Relax & Rejuvenate Spa",
        isPickup = true,
        time = "02:30 PM"
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Upcoming", "Pending", "History")

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> UpcomingScreen()
                1 -> PendingScreen()
                2 -> HistoryListScreen()
            }
        }
    }
}

@Composable
fun UpcomingScreen() {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                "Upcoming Services",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(upcomingServices) { service ->
            UpcomingServiceItem(service = service)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun UpcomingServiceItem(service: UpcomingService) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = service.serviceName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Date: ${service.date} at ${service.time}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Location: ${service.location}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (service.isPickup) "Pickup Service" else "At Venue",
                style = MaterialTheme.typography.bodyMedium,
                color = if (service.isPickup) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PendingScreen() {
    // Content for Pending tab
    Text("Pending Bookings", modifier = Modifier.padding(16.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelSheeet() {
    val (_, setSelectedService) = remember { mutableStateOf<HistoryService?>(null) }
    val sheetState = rememberModalBottomSheetState()
    rememberCoroutineScope()
    val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }
    val (sortBy, setSortBy) = remember { mutableStateOf("Date") }
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {}
    ) {
        Surface {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Service History", style = MaterialTheme.typography.headlineMedium)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.weight(0.6f)) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { setSearchQuery(it) },
                            modifier = Modifier.weight(0.9f),
                            placeholder = { Text("Search by name or service") },
                            label = { Text("Search") }
                        )
                    }
                    Row {
                        Text("Sort by: ", fontSize = 14.sp)
                        TextButton(onClick = { setSortBy("Date") }) {
                            Text(
                                "Date",
                                color = if (sortBy == "Date") MaterialTheme.colorScheme.primary else LocalContentColor.current
                            )
                        }
                        TextButton(onClick = { setSortBy("Status") }) {
                            Text(
                                "Status",
                                color = if (sortBy == "Status") MaterialTheme.colorScheme.primary else LocalContentColor.current
                            )
                        }
                    }
                }
                val filteredServices = services.filter {
                    it.customerName.contains(searchQuery, ignoreCase = true)
                }
                val sortedServices =
                    if (sortBy == "Date") filteredServices.sortedByDescending { it.serviceDate } else filteredServices.sortedBy { it.status }

                LazyColumn {
                    items(sortedServices) { service ->
                        ServiceHistoryItemCard(service) {
                            setSelectedService(service)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryListScreen() {
    // Display the ModelSheeet which contains the filterable list of history services
    ModelSheeet()
}

@Composable
fun ServiceDetailsBottomSheet(service: HistoryService, onDismiss: () -> Unit) {
    Column {
        Text(
            "Service Details",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Customer: ${service.customerName}", fontSize = 16.sp)
            Text("Date: ${service.serviceDate}")
            Text("Time: ${service.serviceTime}")
            Text("Service Type: ${service.serviceType}")
            Text("Description: ${service.description}")
            Text("Price: ${service.totalPrice}")
            Text(
                "Status: ${service.status}",
                style = MaterialTheme.typography.bodyMedium,
                color = when (service.status) {
                    "Completed" -> MaterialTheme.colorScheme.primary
                    "Pending" -> androidx.compose.ui.graphics.Color.Gray
                    "Cancelled" -> androidx.compose.ui.graphics.Color.Red
                    else -> androidx.compose.ui.graphics.Color.Unspecified
                }
            )
        }
        Button(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        ) {
            Text("Dismiss")
        }
    }
}

@Composable
fun ServiceHistoryItemCard(service: HistoryService, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick.invoke() }
            .padding(8.dp, 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(service.serviceDate, style = MaterialTheme.typography.labelMedium)
            Row {
                Column {
                    Text(service.serviceType, style = MaterialTheme.typography.titleMedium)
                    Text(service.description)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    service.status,
                    style = MaterialTheme.typography.titleMedium,
                    color = when (service.status) {
                        "Completed" -> MaterialTheme.colorScheme.primary
                        "Pending" -> androidx.compose.ui.graphics.Color.Gray
                        "Cancelled" -> androidx.compose.ui.graphics.Color.Red
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )
            }
            Row {
                Text("Customer: ${service.customerName}")
                Spacer(modifier = Modifier.width(12.dp))
                Text("Price: ${service.totalPrice}", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}