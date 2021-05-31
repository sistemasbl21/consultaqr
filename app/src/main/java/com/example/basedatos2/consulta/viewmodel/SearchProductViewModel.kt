package com.example.basedatos2.consulta.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basedatos2.consulta.data.ConsultaRepository
import com.example.basedatos2.consulta.mObject.Product
import com.example.basedatos2.utils.MResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchProductViewModel(private val repository: ConsultaRepository) : ViewModel() {

    private val _listProd = MutableLiveData<List<Product>>()
    val listProd : LiveData<List<Product>> = _listProd


    fun getProd(id: String){
        try {
            if (id.isNullOrEmpty()){
                //_errorMsg.value = "Ingrese un rut"
            }else{
                viewModelScope.launch {
                    val result : MResource<List<Product>> = withContext(Dispatchers.IO){
                        repository.searchProduct(id)
                    }

                    when(result){
                        is MResource.Success -> {
                            result.data?.let {
                                if (!it.isNullOrEmpty()){
                                    _listProd.postValue(it)
                                }
                            }
                        }
                        is MResource.Failure -> {
                            Log.d("VM-B", result.exception.message?:"")
                            //_errorMsg.postValue(result.exception.message)
                        }
                    }
                }
            }
        }catch (e: Exception){
            //_errorMsg.value = "Se ha producido un error"
            Log.d("VM-ByRut", e.message?:"")
        }
    }



}