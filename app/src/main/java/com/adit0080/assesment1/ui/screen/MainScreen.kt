package com.adit0080.assesment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adit0080.assesment1.R
import com.adit0080.assesment1.navigation.Screen
import com.adit0080.assesment1.navigation.SetupnavGraph
import com.adit0080.assesment1.ui.theme.Assesment1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment1Theme {
                SetupnavGraph()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name),color = MaterialTheme.colorScheme.primary)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(navController,Modifier.padding(innerPadding))
    }

}

@Composable
    fun ScreenContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ) {
    var tinggi by rememberSaveable{ mutableStateOf("") }
    var tinggiError by  rememberSaveable { mutableStateOf(false) }

    var berat by rememberSaveable {mutableStateOf("") }
    var beratError by  rememberSaveable { mutableStateOf(false) }

    var usia by rememberSaveable {mutableStateOf("") }
    var usiaError by  rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(R.string.pria),
        stringResource(R.string.wanita)
    )
    var gender by rememberSaveable {mutableStateOf(radioOptions[0])}
    var tingkatAktivitas by rememberSaveable{mutableStateOf("Ringan") }

    var kaloriHarian by rememberSaveable {mutableIntStateOf(0)}



    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Daily Calorie tracker",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom =16.dp),color = MaterialTheme.colorScheme.onBackground)
        Image(
            painter = painterResource(R.drawable.calories_calculator),
            contentDescription = "Kalkulator Kalori",
            modifier = Modifier.size(150.dp).align(alignment = Alignment.CenterHorizontally)

        )

        Text(text = stringResource(R.string.height), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
    OutlinedTextField(
    value = tinggi,
    onValueChange ={tinggi = it},
    modifier = Modifier.fillMaxWidth(),
    placeholder = {
        Text(text = "Masukan Tinggi Badan", color = MaterialTheme.colorScheme.onBackground)
    },
        trailingIcon = {IconPicker(tinggiError,"cm")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
)
        ErrorHint(tinggiError)

        Text(text = stringResource(R.string.weight), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
        OutlinedTextField(
            value = berat,
            onValueChange ={berat = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Masukan Berat Badan", color = MaterialTheme.colorScheme.onBackground)
            },
            trailingIcon = {IconPicker(beratError,"cm")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        ErrorHint(beratError)
        Text(text = stringResource(R.string.usia), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
        OutlinedTextField(
            value = usia,
            onValueChange ={usia = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Masukan Usia", color = MaterialTheme.colorScheme.onBackground)
            },
            trailingIcon = {IconPicker(usiaError,"cm")},


            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        ErrorHint(usiaError)

        Text(text = "Tingkat Aktivitas", fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
        listOf("Ringan", "Normal", "Berat").forEach { level ->
            ActivityOptionCard(
                label = level,
                isSelected = (tingkatAktivitas == level),
                onSelect = { tingkatAktivitas = level }
            )
        }
        Text(text = "Gender",fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
        Row(
            modifier = Modifier.padding(6.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text -> GenderOption(
                label = text,
                isSelected = gender == text,
                modifier= Modifier.selectable(selected = gender == text,
                    onClick = {gender = text},
                    role = Role.RadioButton
                ).weight(1f).padding(16.dp)
            ) }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                tinggiError = (tinggi == "" || tinggi == "0")
                beratError = (berat == "" || berat == "0")
                usiaError =  (usia == "" || usia == "0")
                if (tinggiError || beratError || usiaError){
                    return@Button
                }

                kaloriHarian = hitungKalori(tinggi.toDouble(),berat.toDouble(),usia.toInt(),gender == radioOptions[0],tingkatAktivitas)
                navController.navigate("${Screen.Result.route}/$kaloriHarian")
            }

        ) {
            Text(text = stringResource(R.string.hitung),color = MaterialTheme.colorScheme.onBackground)
        }
    }



}

@Composable
fun ActivityOptionCard(
    label: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF064E3B)
            else
                MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF4CAF50)) else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = isSelected, onClick = null)
            Spacer(Modifier.width(12.dp))
            Text(text = label, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun GenderOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp),color = MaterialTheme.colorScheme.onBackground
        )
    }
}



private fun hitungKalori(tinggi: Double, berat: Double, usia: Int, isMale: Boolean,tingkatAktivitas: String): Int {
   val bmr = if (isMale){
        (10 * berat) + (6.25 * tinggi) - (5 * usia) + 5
    } else {
        (10 * berat) + (6.25 * tinggi) - (5 * usia) - 161
    }
    val multiplier = when (tingkatAktivitas) {
        "Ringan" -> 1.375
        "Normal" -> 1.55
        "Berat" -> 1.725
        else -> 1.2
    }
    val tdee = bmr * multiplier
    return tdee.toInt()
}


@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid),color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit,color = MaterialTheme.colorScheme.onBackground)
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment1Theme {
        MainScreen(rememberNavController())
    }
}