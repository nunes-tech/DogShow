package com.example.dogshow.di

import com.example.dogshow.data.api.TheCatAPI
import com.example.dogshow.data.repository.RepositoryImagemCatImpl
import com.example.dogshow.data.repository.RepositoryImagemPet
import com.example.dogshow.domain.usecase.GetImagensCatUseCase
import com.example.dogshow.util.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object TheCatModule {

    ///The Cat API
    @Provides
    @Named("theCat")
    fun proverTheCatOkHttpClient(): OkHttpClient {

        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", Constantes.theCatAPI.API_KEY)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    @Named("theCat")
    fun proverTheCatRetrofit( @Named("theCat") okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl( Constantes.theCatAPI.BASE_URL )
            .addConverterFactory(GsonConverterFactory.create())
            .client( okHttpClient )
            .build()
    }


    @Provides
    @Named("theCat")
    fun proverTheCatApiService(
        @Named("theCat") retrofit: Retrofit
    ) : TheCatAPI {
        return retrofit.create(TheCatAPI::class.java)
    }

    @Provides
    @Named("theCat")
    fun proverRepositoryImagemPetImpl(
        @Named("theCat") apiService: TheCatAPI
    ) : RepositoryImagemPet {
        return RepositoryImagemCatImpl( apiService )
    }

    @Provides
    @Named("theCat")
    fun proverGetImagemCatUseCase(
        @Named("theCat") repository : RepositoryImagemPet
    ) : GetImagensCatUseCase {
        return GetImagensCatUseCase(repository)
    }


}