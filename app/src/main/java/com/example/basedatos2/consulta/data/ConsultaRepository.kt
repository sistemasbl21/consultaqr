package com.example.basedatos2.consulta.data

import com.example.basedatos2.consulta.mObject.Product
import com.example.basedatos2.utils.MResource

interface ConsultaRepository {
    suspend fun searchProduct(id: String) : MResource<List<Product>>
}