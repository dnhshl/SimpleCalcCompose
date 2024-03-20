package com.example.simplecalc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplecalc.ui.IntegerInputField
import com.example.simplecalc.ui.theme.SimpleCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalcTheme {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen() {
    var entry1 by remember { mutableStateOf("") }
    var entry2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val isValidEntry = entry1.isNotEmpty() && entry1.toIntOrNull() != null
            && entry2.isNotEmpty() && entry2.toIntOrNull() != null

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!isValidEntry) {
            Text(
                text = stringResource(id = R.string.error_message),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {

                IntegerInputField(onInputChanged = { entry1 = it} )
                Spacer(modifier = Modifier.width(30.dp))
                IntegerInputField(onInputChanged = { entry2 = it} )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                var i = entry1.toInt()
                var j = entry2.toInt()
                result = (i+j).toString()
                },
                enabled = isValidEntry
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = result,
                style = MaterialTheme.typography.headlineLarge)
        }
    }
}

