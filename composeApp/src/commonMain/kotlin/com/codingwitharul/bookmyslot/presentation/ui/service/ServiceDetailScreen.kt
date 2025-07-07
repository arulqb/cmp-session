package com.codingwitharul.bookmyslot.presentation.ui.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.cart
import bookmyslot.composeapp.generated.resources.pc
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ServiceDetailScreen() {
    // Dummy data for demonstration purposes
    val serviceName = "Premium Car Wash"
    val serviceDescription =
        "Experience the ultimate clean with our premium car wash service. We use high-quality products to ensure your car shines like new. This service includes exterior wash, interior vacuuming, tire shining, and window cleaning."
    val serviceRate = 49.99
    val serviceImages = listOf(
        Res.drawable.cart, // Replace with actual image resources
        Res.drawable.cart,
        Res.drawable.cart
    )
    val reviews = listOf(
        Review("John Doe", 4.5f, "Great service! My car looks amazing."),
        Review("Jane Smith", 5.0f, "Absolutely fantastic. Will come again!"),
        Review("Peter Jones", 4.0f, "Good value for money. Satisfied with the results.")
    )

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Service Images
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(serviceImages) { imageRes ->
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = "Service Image",
                            modifier = Modifier.size(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Service Name
            item {
                Text(
                    text = serviceName,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Service Description
            item {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = serviceDescription, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Service Rate
            item {
                Text(
                    text = "Rate",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$serviceRate",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Reviews Section
            item {
                Text(
                    text = "Reviews",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(reviews) { review ->
                ReviewItem(review = review)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Book Now Button
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { /* Handle booking action */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Book Now")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class Review(
    val userName: String,
    val rating: Float,
    val comment: String
)

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = review.userName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Rating: ${review.rating}/5",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(
                    painterResource(Res.drawable.pc),
                    contentDescription = "Star Rating",
                    tint = Color.Yellow, // Or your theme's star color
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = review.comment, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview()
@Composable
fun ServiceDetailScreenPreview() {
    MaterialTheme {
        ServiceDetailScreen()
    }
}