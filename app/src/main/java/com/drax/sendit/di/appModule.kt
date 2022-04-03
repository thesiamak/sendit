package com.drax.sendit.di

import com.drax.sendit.data.db.AppDB
import com.drax.sendit.data.repo.*
import com.drax.sendit.domain.network.*
import com.drax.sendit.domain.repo.*
import com.drax.sendit.view.main.MainVM
import com.drax.sendit.view.connections.ConnectionsVM
import com.drax.sendit.view.login.LoginVM
import com.drax.sendit.view.profile.ProfileVM
import com.drax.sendit.view.qr.QrVM
import com.drax.sendit.view.transmissions.TransmissionsVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //DB
    single                     {   AppDB.get(androidContext()) }
    single                     {   get<AppDB>().registryDao() }
    single                     {   get<AppDB>().transactionDao() }
    single                     {   get<AppDB>().connectionDao() }

    viewModel                  {  ConnectionsVM(get(),get())  }
    viewModel                  {  QrVM(get(),get())  }
    viewModel                  {  TransmissionsVM()  }
    viewModel                  {  LoginVM(get(),get(),get(),get(),)  }
    viewModel                  {  ProfileVM(get(), get())  }
    viewModel                  {  MainVM(get(), get())  }

    single<DeviceRepository>  { DeviceRepositoryImpl(get()) }
    single<PushRepository>     { PushRepositoryImpl(get(),get(),get()) }
    single<RegistryRepository> { RegistryRepositoryImpl(get()) }
    single<UserRepository>     { UserRepositoryImpl(get()) }
    single<TransactionRepository>     { TransactionRepositoryImpl(get(), get()) }
    single<AuthRepository>     { AuthRepositoryImpl(get(), get(),get(),get()) }
    single<ConnectionRepository>     { ConnectionRepositoryImpl(get(), get()) }

    single<ApiService>         {   AppRetrofit(get(), get(), get()).getRetrofitClient()
        .create(ApiService::class.java)}
    single                     { AuthInterceptor(get()) }
    single                     { HeaderInterceptor( get()) }
    single                     { ErrorHandlerInterceptor( androidContext().resources) }

}
