package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayValue
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.primaryLightHighContrast

internal val previewCoinUi = Coin(
    id = "1",
    rank = 1,
    name = "Ethereum",
    symbol = "Eth",
    marketCapUsd = 14393.77,
    priceUsd = 56.88,
    changePercent24Hr = -4.7,
).toCoinUi()

@PreviewLightDark
@Composable
fun CoinItemPreview() {
    CryptoTrackerTheme {
        CoinItem(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            coinUi = previewCoinUi,
        )
    }
}

@Composable
fun CoinItem(
    modifier: Modifier,
    coinUi: CoinUi,
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            modifier = Modifier.size(85.dp),
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes) , 
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = coinUi.symbol?.uppercase() ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = coinUi.name ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${coinUi.marketCapUsd?.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            ChangePercentageComponent(
               percentageValue = coinUi.changePercent24Hr ?: DisplayValue()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun ChangePercentageComponentPreview() {
    CryptoTrackerTheme {
        ChangePercentageComponent(
            percentageValue = previewCoinUi.changePercent24Hr ?: DisplayValue()
        )
    }
}

@Composable
fun ChangePercentageComponent(
    percentageValue: DisplayValue?
) {
    val isValueDecreased = (percentageValue?.value ?: 0.0) < 0.0
    val containerColor = if(isValueDecreased) MaterialTheme.colorScheme.errorContainer else Color.Green
    val contentColor = if(isValueDecreased) MaterialTheme.colorScheme.onErrorContainer else primaryLightHighContrast

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100f))
            .background(containerColor)
            .padding(start = 2.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if(isValueDecreased) Icons.Default.ArrowDropDown
                            else Icons.Default.ArrowDropUp,
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = "${percentageValue?.formatted ?: ""} %",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
        )
    }
}