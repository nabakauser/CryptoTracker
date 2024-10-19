package com.plcoding.cryptotracker.crypto.domain

data class Coin(
    val id: String? = null,
    val rank: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val marketCapUsd: Double? = null,
    val priceUsd: Double? = null,
    val changePercent24Hr: Double? = null,
)