package com.adit0080.assesment1.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adit0080.assesment1.R
import com.adit0080.assesment1.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen( modifier: Modifier = Modifier,id : Long? = null, navController: NavHostController){
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var nama by rememberSaveable{ mutableStateOf("") }
    var namaError by rememberSaveable { mutableStateOf(false) }

    var tinggi by rememberSaveable{ mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    var usia by rememberSaveable { mutableStateOf("") }
    var usiaError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(R.string.pria),
        stringResource(R.string.wanita)
    )
    var gender by rememberSaveable { mutableStateOf(radioOptions[0]) }
    var tingkatAktivitas by rememberSaveable{ mutableStateOf("Normal") }

    var kaloriHarian by rememberSaveable { mutableIntStateOf(0) }


    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getKalori(id) ?: return@LaunchedEffect
        nama = data.nama
        tinggi = data.tinggi.toString()
        berat = data.berat.toString()
        usia = data.usia.toString()
        gender = data.gender
        tingkatAktivitas = data.tingkatAktivitas
        kaloriHarian = data.hasilKalori
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(R.string.hapus)) },
            text = { Text(text = stringResource(R.string.pesan_hapus)) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    if (id != null) {
                        viewModel.delete(id)
                        navController.popBackStack()
                    }
                }) {
                    Text(text = stringResource(R.string.tombol_hapus), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(R.string.tombol_batal))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (id == null) stringResource(R.string.tambah_data) else stringResource(R.string.edit_data),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    if (id != null) {
                        TopBarMenu(
                            delete = { showDialog = true },
                            share = {
                                shareData(context, nama, kaloriHarian)
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding) // Wajib ada agar tidak tertimpa TopAppBar
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(text = stringResource(R.string.nama), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
            OutlinedTextField(
                value = nama,
                onValueChange ={nama = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(R.string.masukanNama), color = MaterialTheme.colorScheme.onBackground)
                },
                trailingIcon = {IconPicker(namaError,"")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            ErrorHint(namaError)

            Text(text = stringResource(R.string.height), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
            OutlinedTextField(
                value = tinggi,
                onValueChange ={tinggi = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(R.string.masukanTinggi), color = MaterialTheme.colorScheme.onBackground)
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
                    Text(text = stringResource(R.string.masukanBerat), color = MaterialTheme.colorScheme.onBackground)
                },
                trailingIcon = {IconPicker(beratError,"kg")},
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
                    Text(text = stringResource(R.string.masukanUsia), color = MaterialTheme.colorScheme.onBackground)
                },
                trailingIcon = {IconPicker(usiaError,"")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            ErrorHint(usiaError)

            Text(text = stringResource(R.string.tingkatAktivitas), fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
            listOf(stringResource(R.string.ringan), stringResource(R.string.normal), stringResource(R.string.berat)).forEach { level ->
                ActivityOptionCard(
                    label = level,
                    isSelected = (tingkatAktivitas == level),
                    onSelect = { tingkatAktivitas = level }
                )
            }
            Text(text =stringResource(R.string.gender),fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 8.dp),color = MaterialTheme.colorScheme.onBackground)
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
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                onClick = {
                    // Konversi aman
                    val tinggiValue = tinggi.toDoubleOrNull() ?: 0.0
                    val beratValue = berat.toDoubleOrNull() ?: 0.0
                    val usiaValue = usia.toIntOrNull() ?: 0

                    namaError = nama.isBlank()
                    tinggiError = tinggiValue <= 0
                    beratError = beratValue <= 0
                    usiaError = usiaValue <= 0

                    if (namaError || tinggiError || beratError || usiaError){
                        return@Button
                    }

                    kaloriHarian = hitungKalori(tinggiValue, beratValue, usiaValue, gender == radioOptions[0], tingkatAktivitas, context)

                    if (id == null){
                        viewModel.insert(nama, tinggiValue, beratValue, usiaValue, gender, tingkatAktivitas, kaloriHarian)
                    }else{
                        viewModel.update(nama, id, tinggiValue, beratValue, usiaValue, gender, tingkatAktivitas, kaloriHarian)
                    }

                    navController.popBackStack()
                }

            ) {
                Text(
                    text = if (id == null) stringResource(R.string.hitung) else stringResource(R.string.edit_data),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
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



private fun hitungKalori(tinggi: Double, berat: Double, usia: Int, isMale: Boolean,tingkatAktivitas: String,context: android.content.Context): Int {
    val bmr = if (isMale){
        (10 * berat) + (6.25 * tinggi) - (5 * usia) + 5
    } else {
        (10 * berat) + (6.25 * tinggi) - (5 * usia) - 161
    }
    val multiplier = when (tingkatAktivitas) {
        context.getString(R.string.ringan) -> 1.375
        context.getString(R.string.normal) -> 1.55
        context.getString(R.string.berat) -> 1.725
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


@Composable
fun TopBarMenu(delete: () -> Unit, share: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.bagikan)) },
                onClick = {
                    expanded = false
                    share()
                }
            )

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.hapus)) },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

private fun shareData(context: android.content.Context, nama: String, kalori: Int) {
    val pesan = "Halo! Kebutuhan kalori harian $nama adalah $kalori Kkal. Dihitung menggunakan FitLog!"

    val shareIntent = android.content.Intent().apply {
        action = android.content.Intent.ACTION_SEND
        putExtra(android.content.Intent.EXTRA_TEXT, pesan)
        type = "text/plain"
    }

    context.startActivity(
        android.content.Intent.createChooser(shareIntent, "Bagikan hasil kalori...")
    )
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(),id = null)
}
