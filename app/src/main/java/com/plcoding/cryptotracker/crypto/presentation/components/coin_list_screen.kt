package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@PreviewLightDark
@Composable
fun CoinListScreenPreview(){
    CryptoTrackerTheme {
        CoinListScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            isLoading = false,
            coinList = (1..10).map {
                previewCoinUi
            }
        )
    }
}

@Composable
fun CoinListScreen(
    modifier: Modifier,
    isLoading: Boolean,
    coinList: List<CoinUi>,
) {
    if(isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(coinList) { coin ->
                CoinItem(
                    modifier = Modifier.fillMaxWidth(),
                    coinUi = coin
                )
                HorizontalDivider()
            }
        }
    }
}