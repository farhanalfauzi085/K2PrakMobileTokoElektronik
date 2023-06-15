package com.example.kursusalatmusik.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import com.example.kursusalatmusik.model.KursusAlatMusik
import com.example.kursusalatmusik.persistences.KursusAlatMusikDao
import com.example.kursusalatmusik.ui.theme.Purple200
import com.example.kursusalatmusik.ui.theme.Purple500
import kotlinx.coroutines.launch

@Composable
fun FormKursusAlatMusik(kursusAlatMusikDao: KursusAlatMusikDao) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val jeniskelamin = remember { mutableStateOf(TextFieldValue("")) }
    val nohp = remember { mutableStateOf(TextFieldValue("")) }
    val alatmusik = listOf("Gitar", "Bass", "Drum", "Biola", "Piano")
    var expanded by remember { mutableStateOf(false) }
    var selectedalatmusik by remember { mutableStateOf("") }
    val harikursus = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aplikasi Pendaftaran Kursus Alat Musik",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A237E),
            modifier = Modifier.padding(vertical = 24.dp)
        )

            OutlinedTextField(
                label = { Text(text = "Nama") },
                value = nama.value,
                onValueChange = {
                    nama.value = it
                },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                placeholder = { Text(text = "Masukan Nama Lengkap") }
            )

            OutlinedTextField(
                label = { Text(text = "Jenis Kelamin") },
                value = jeniskelamin.value,
                onValueChange = {
                    jeniskelamin.value = it
                },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                placeholder = { Text(text = "Laki - laki / Perempuan") }
            )
            OutlinedTextField(
                label = { Text(text = "Nomor Telepon") },
                value = nohp.value,
                onValueChange = {
                    nohp.value = it
                },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType =
                    KeyboardType.Decimal
                ),
               leadingIcon = { Text(text = "+62")}

            )


            TextField(
                label = { Text(text = "Pilih Kursus Alat Musik") },
                value = selectedalatmusik,
                onValueChange = { selectedalatmusik = it },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier.clickable {
                            expanded = true
                        })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                alatmusik.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedalatmusik = option
                            expanded = false
                        }
                    ) {
                        Text(text = option)
                    }
                }
            }

            OutlinedTextField(
                label = { Text(text = "Hari") },
                value = harikursus.value,
                onValueChange = {
                    harikursus.value = it
                },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                placeholder = { Text(text = "Ketik Hari yang Akan di Pilih") }
            )
            val loginButtonColors = ButtonDefaults.buttonColors(
                backgroundColor = Purple200,
                contentColor = Purple200
            )
            val resetButtonColors = ButtonDefaults.buttonColors(
                backgroundColor = Purple500,
                contentColor = Purple500
            )
            Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                Button(modifier = Modifier.weight(10f), onClick = {

                    if (nama.value.text.isEmpty()){
                        Toast.makeText(context, "Nama Harus Diisi", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (jeniskelamin.value.text.isEmpty()){
                        Toast.makeText(context, "Jenis Kelamin Harus Diisi", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (nohp.value.text.isEmpty()){
                        Toast.makeText(context, "No. Handphone Harus Diisi", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (selectedalatmusik.isEmpty()){
                        Toast.makeText(context, "AlatMusik Harus Diisi", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (harikursus.value.text.isEmpty()){
                        Toast.makeText(context, "Hari Harus Diisi", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val id = uuid4().toString()
                    val item = KursusAlatMusik(
                        id, nama.value.text, jeniskelamin.value.text,
                        nohp.value.text, selectedalatmusik, harikursus.value.text
                    )
                    scope.launch {
                        kursusAlatMusikDao.insertAll(item)
                    }

                    nama.value = TextFieldValue("")
                    jeniskelamin.value = TextFieldValue("")
                    nohp.value = TextFieldValue("")
                    selectedalatmusik = ("")
                    harikursus.value = TextFieldValue("")

                }, colors = loginButtonColors) { Text(
                        text = "Simpan",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp
                        ), modifier = Modifier.padding(8.dp)
                    )
                }
                Button(modifier = Modifier.weight(10f),
                    onClick = {
                    nama.value = TextFieldValue("")
                    jeniskelamin.value = TextFieldValue("")
                    nohp.value = TextFieldValue("")
                    selectedalatmusik = ("")
                    harikursus.value = TextFieldValue("")
                }, colors = resetButtonColors) {
                    Text(
                        text = "Reset",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp
                        ), modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
