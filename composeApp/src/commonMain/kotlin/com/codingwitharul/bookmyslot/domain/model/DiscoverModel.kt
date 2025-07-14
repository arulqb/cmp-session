package com.codingwitharul.bookmyslot.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DiscoverModel(
    val id: String,
    val createdAt: String,
    val services: List<ServiceModel>,
    val products: List<ProductModel>,
    val offers: List<OfferModel>
)

@Serializable
data class ServiceModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String
)

@Serializable
data class ProductModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val price: Double
)

@Serializable
data class OfferModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String
)