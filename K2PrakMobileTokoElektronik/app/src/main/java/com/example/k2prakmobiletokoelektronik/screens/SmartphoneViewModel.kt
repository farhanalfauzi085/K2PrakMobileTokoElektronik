package com.example.k2prakmobiletokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k2prakmobiletokoelektronik.model.Komputer
import com.example.k2prakmobiletokoelektronik.model.Smartphone
import com.example.k2prakmobiletokoelektronik.repositories.KomputerRepository
import com.example.k2prakmobiletokoelektronik.repositories.SmartphoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmartphoneViewModel @Inject constructor(private val smartphoneRepository: SmartphoneRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<Smartphone>> =
        MutableLiveData()
    val list: LiveData<List<Smartphone>> get() = _list
    suspend fun loadItems() {
        _isLoading.postValue(true)
        smartphoneRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(
        model : String,
        warna : String,
        storage : String,
        tanggal_rilis: String,
        sistem_operasi: String,
    ){
        _isLoading.postValue(true)
        smartphoneRepository.insert(
            model, warna, storage, tanggal_rilis, sistem_operasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun loadItem(id: String, onSuccess: (Smartphone?) ->
    Unit) {
        val item = smartphoneRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(
        id: String,
        model : String,
        warna : String,
        storage : String,
        tanggal_rilis: String,
        sistem_operasi : String
    ){
        _isLoading.postValue(true)
        smartphoneRepository.update(
            id, model, warna, storage, tanggal_rilis, sistem_operasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        smartphoneRepository.delete(id, onError = { message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _success.postValue(true)
        }, onSuccess = {
            _toast.postValue("Data berhasil dihapus")
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }

}