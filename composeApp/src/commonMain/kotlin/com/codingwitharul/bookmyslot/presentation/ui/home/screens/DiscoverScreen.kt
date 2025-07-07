package com.codingwitharul.bookmyslot.presentation.ui.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.bell
import bookmyslot.composeapp.generated.resources.logo_bml
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DiscoverScreen() {
    Surface {
        Column(modifier = Modifier) {
            HomeToolbar()
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                            .height(180.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.logo_bml), // Replace with your banner image
                            contentDescription = "Banner Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Text(
                        "Services",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        items(5) { // Replace with your actual service list size
                            ServiceItem()
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Text(
                            "Popular Products",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxWidth()
                                .heightIn(max = 500.dp),
                            userScrollEnabled = false,
                            contentPadding = PaddingValues(bottom = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(4) { ProductItem() } // Replace with your actual product list size
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun HomeToolbar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(60.dp)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            "BookMySlot", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {

            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(Res.drawable.bell),
                    tint = Color.Black,
                    contentDescription = "Notification"
                )
            }
            Box(
                modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {

                }
                .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                // Replace with your service icon or image
                Image(
                    painter = painterResource(Res.drawable.logo_bml),
                    contentDescription = "Service Icon",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun ServiceItem() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            // Replace with your service icon or image
            Image(
                painter = painterResource(Res.drawable.logo_bml),
                contentDescription = "Service Icon",
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Service Name", fontSize = 14.sp)
    }
}

@Composable
fun ProductItem() {
    Card(
        modifier = Modifier
            .width(160.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Image(
                painter = painterResource(Res.drawable.logo_bml), // Replace with your product image
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Product Name", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Rp 100.000", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}