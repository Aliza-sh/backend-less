package com.aliza.alizaparse.di

import com.aliza.alizaparse.data.ApiService
import com.aliza.alizaparse.data.MainRepository
import com.aliza.alizaparse.di.CheckConnection.provideCM
import com.aliza.alizaparse.di.CheckConnection.provideNR
import com.aliza.alizaparse.utils.net.NetworkChecker
import com.aliza.alizaparse.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //NetworkChecker
    single { NetworkChecker(provideCM(androidContext()), provideNR()) }

    single { ApiService() }
    single { MainRepository(get()) }

    viewModel{ MainViewModel(get()) }

}