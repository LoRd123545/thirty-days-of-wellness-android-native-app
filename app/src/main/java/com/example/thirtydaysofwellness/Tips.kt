package com.example.thirtydaysofwellness

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofwellness.data.TipsRepository
import com.example.thirtydaysofwellness.model.Tip
import com.example.thirtydaysofwellness.ui.theme.ThirtyDaysOfWellnessTheme

@Composable
fun Tip(
    modifier: Modifier = Modifier,
    tip: Tip
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(tip.titleRes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .sizeIn(maxHeight = 200.dp)
            ) {
                Image(
                    painter = painterResource(tip.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = stringResource(tip.descriptionRes),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun SmallTip(
    modifier: Modifier = Modifier,
    tip: Tip
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
            ) {
                Image(
                    painter = painterResource(tip.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(
                modifier = Modifier
                    .width(
                        dimensionResource(
                            R.dimen.padding_small
                        )
                    )
            )
            Text(
                text = stringResource(tip.titleRes),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TipList(
    modifier: Modifier = Modifier,
    tips: List<Tip>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            dimensionResource(
                R.dimen.padding_medium
            )
        )
    ) {
        items(tips) { tip ->
            Tip(
                tip = tip
            )
            Spacer(
                modifier = Modifier
                    .height(
                        dimensionResource(
                            R.dimen.padding_medium
                        )
                    )
            )
        }
    }
}

@Composable
fun TipGrid(
    modifier: Modifier = Modifier,
    tips: List<Tip>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(
            dimensionResource(
                R.dimen.padding_medium
            )
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(
                R.dimen.padding_medium
            )
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(
                R.dimen.padding_medium
            )
        ),
    ) {
        items(tips) { tip ->
            SmallTip(
                tip = tip
            )
        }
    }
}

@Preview
@Composable
fun SmallTipPreview(modifier: Modifier = Modifier) {
    ThirtyDaysOfWellnessTheme {
        SmallTip(
            tip = TipsRepository.tips[0]
        )
    }
}

@Preview
@Composable
fun TipPreview(modifier: Modifier = Modifier) {
    ThirtyDaysOfWellnessTheme {
        Tip(
            tip = TipsRepository.tips[0]
        )
    }
}

@Preview
@Composable
fun TipListPreview(
    modifier: Modifier = Modifier
) {
    ThirtyDaysOfWellnessTheme {
        TipList(
            tips = TipsRepository.tips
        )
    }
}