package com.example.basedatos2.consulta.mObject


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("available")
    val available: Int?,
    @SerializedName("unit_sell")
    val unitSell: Int?,
    @SerializedName("value")
    val value: Int?,
    @SerializedName("tot_value")
    val totValue: Int?,
    @SerializedName("marca")
    val marca: String?,
    @SerializedName("estado_equipo")
    val estadoEquipo: String?,
    @SerializedName("fecha_ingreso")
    val fechaIngreso: String?,
    @SerializedName("fecha_salida")
    val fechaSalida: String?,
    @SerializedName("observaciones")
    val observaciones: String?
)