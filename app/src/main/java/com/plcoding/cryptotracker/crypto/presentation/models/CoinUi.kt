package com.plcoding.cryptotracker.crypto.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.util.getDrawableIdForCoin
import java.util.Locale

data class CoinUi(
    val id: String? = null,
    val rank: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val marketCapUsd: DisplayValue? = null,
    val priceUsd: DisplayValue? = null,
    val changePercent24Hr: DisplayValue? = null,
    @DrawableRes val iconRes: Int,
)

data class DisplayValue(
    val value: Double? = null,
    val formatted: String? = null,
)

fun Coin.toCoinUi() = CoinUi(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd?.toDisplayableValue(),
    priceUsd = priceUsd?.toDisplayableValue(),
    changePercent24Hr = changePercent24Hr?.toDisplayableValue(),
    iconRes = getDrawableIdForCoin(symbol ?: "")
)

fun Double.toDisplayableValue() : DisplayValue {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayValue(
        value = this,
        formatted = formatter.format(this)
    )
}