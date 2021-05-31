package com.example.basedatos2.di

import com.example.basedatos2.consulta.data.ConsultaRepository
import com.example.basedatos2.consulta.data.ConsultaRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ConsultaRepository> { ConsultaRepositoryImpl(get(), get()) }
}