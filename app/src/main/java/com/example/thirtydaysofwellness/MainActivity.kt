package com.example.thirtydaysofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.thirtydaysofwellness.data.TipsRepository
import com.example.thirtydaysofwellness.ui.theme.ThirtyDaysOfWellnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysOfWellnessTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        ThirtyDaysOfWellnessTopAppBar()
                    }
                ) { innerPadding ->
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
fun SwitchWithText(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @StringRes textRes: Int
) {
    Row(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(textRes)
            )
            Spacer(
                modifier = Modifier
                    .width(
                        dimensionResource(
                            R.dimen.padding_medium
                        )
                    )
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun ThirtyDaysOfWellnessApp(modifier: Modifier = Modifier) {
    var columnViewOn by remember {
        mutableStateOf(
            true
        )
    }

    var viewSwitchTextRes by remember {
        mutableStateOf(R.string.column_view_switch_text)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ThirtyDaysOfWellnessSettings(
            columnViewOn = columnViewOn,
            onViewChange = { checked ->
                viewSwitchTextRes = if(checked)
                    R.string.column_view_switch_text
                else
                    R.string.grid_view_switch_text

                columnViewOn = !columnViewOn
            },
            viewSwitchTextRes = viewSwitchTextRes
        )

        if(columnViewOn) {
            TipList(
                tips = TipsRepository.tips,
            )
        } else {
            TipGrid(
                tips = TipsRepository.tips
            )
        }
    }
}

@Composable
fun ThirtyDaysOfWellnessSettings(
    modifier: Modifier = Modifier,
    columnViewOn: Boolean = true,
    onViewChange: (Boolean) -> Unit,
    @StringRes viewSwitchTextRes: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(
                    R.dimen.padding_medium
                )
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SwitchWithText(
            checked = columnViewOn,
            onCheckedChange = onViewChange,
            textRes = viewSwitchTextRes
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysOfWellnessTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        modifier = modifier
    )
}
