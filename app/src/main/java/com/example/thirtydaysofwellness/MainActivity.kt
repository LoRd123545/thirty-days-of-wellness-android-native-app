package com.example.thirtydaysofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofwellness.data.TipsRepository
import com.example.thirtydaysofwellness.model.Tip
import com.example.thirtydaysofwellness.ui.theme.ThirtyDaysOfWellnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysOfWellnessTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThirtyDaysOfWellnessApp(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysOfWellnessApp(modifier: Modifier = Modifier) {

}

@Composable
fun Tip(
    modifier: Modifier = Modifier,
    tip: Tip
) {
    Card(
        modifier = modifier
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
                    .height(300.dp)
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
fun TipList(
    modifier: Modifier = Modifier,
    tips: List<Tip>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tips) { tip ->
            Tip(
                tip = tip
            )
        }
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
