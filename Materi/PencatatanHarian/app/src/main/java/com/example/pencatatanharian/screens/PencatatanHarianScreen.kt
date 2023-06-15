package com.example.pencatatanharian.screens

import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.pencatatanharian.model.PencatatanKeuangan
import com.example.pencatatanharian.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
@Composable
fun PencatatanHarian(PencatatanHarianDao) {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pencatatan-harian"
    ).build()
    val pencatatanHarianDao = db.pencatatanHarianDao()

    val list : LiveData<List<PencatatanKeuangan>> = pencatatanHarianDao.loadAll()
    val items: List<PencatatanKeuangan> by list.observeAsState(initial = listOf())
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal", fontSize = 14.sp)
                        Text(text = item.tanggal, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Keterangan", fontSize = 14.sp)
                        Text(text = item.keterangan, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Pemasukan", fontSize = 14.sp)
                        Text(text = "Rp. ${item.pemasukan}", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Pengeluaran", fontSize = 14.sp)
                        Text(text = "Rp. ${item.pengeluaran}", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}
