package com.example.pencatatanharian.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import com.example.pencatatanharian.model.PencatatanKeuangan
import com.example.pencatatanharian.persistences.PencatatanHarianDao
import com.example.pencatatanharian.ui.theme.Purple700
import com.example.pencatatanharian.ui.theme.Teal200
import kotlinx.coroutines.launch


@Composable
fun FormPencatatanKeuangan(PencatatanKeuangan: PencatatanHarianDao) {
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val keterangan = remember { mutableStateOf(TextFieldValue("")) }
    val pemasukan = remember { mutableStateOf(TextFieldValue("")) }
    val pengeluaran = remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Keterangan") },
            value = keterangan.value,
            onValueChange = {
                keterangan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Sentences, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = ".......") }
        )
        OutlinedTextField(
            label = { Text(text = "Pemasukan") },
            value = pemasukan.value,
            onValueChange = {
                pemasukan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = { Text(text = "Rp.") }


        )
        OutlinedTextField(
            label = { Text(text ="Pengeluaran") },
            value = pengeluaran.value,
            onValueChange = {
                pengeluaran.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Number
            ),
            leadingIcon = { Text(text = "Rp.") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                val item = PencatatanKeuangan(
                    tanggal.value.text, keterangan.value.text,
                    pemasukan.value.text, pengeluaran.value.text

                )

                if (tanggal.value.text.isEmpty()) {
                    Toast.makeText(context, "Tanggal harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (keterangan.value.text.isEmpty()) {
                    Toast.makeText(context, "Keterangan harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (pemasukan.value.text.isEmpty() && pengeluaran.value.text.isEmpty()) {
                    Toast.makeText(context, "Pemasukan atau Pengeluaran harus diisi!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (!pemasukan.value.text.isEmpty() && !pengeluaran.value.text.isEmpty()) {
                    Toast.makeText(context, "Hanya satu field boleh diisi (Pemasukan atau Pengeluaran)!",
                        Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val id = uuid4().toString()
                val item = PencatatanKeuangan(id, tanggal.value.text, keterangan.value.text,
                    pemasukan.value.text, pengeluaran.value.text)
                scope.launch {
                    pencatatanHarianDao.insertAll(item)
                }
                tanggal.value = TextFieldValue("")
                keterangan.value = TextFieldValue("")
                pemasukan.value = TextFieldValue("")
                pengeluaran.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                keterangan.value = TextFieldValue("")
                pemasukan.value = TextFieldValue("")
                pengeluaran.value = TextFieldValue("")
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
