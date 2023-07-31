package com.example.dogshow.di

import com.example.dogshow.data.api.TheDogAPI
import com.example.dogshow.data.repository.RepositoryImagemPet
import com.example.dogshow.data.repository.RepositoryImagemDogImpl
import com.example.dogshow.domain.usecase.GetImagensDogUseCase
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
object TheDogModule {

    ///The Dog API
    @Provides
    @Named("theDog")
    fun proverTheDogOkHttpClient(): OkHttpClient {

        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", Constantes.theDogApi.API_KEY)
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
    @Named("theDog")
    fun proverTheDogRetrofit( @Named("theDog") okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl( Constantes.theDogApi.BASE_URL )
            .addConverterFactory(GsonConverterFactory.create())
            .client( okHttpClient )
            .build()
    }

    @Provides
    @Named("theDog")
    fun proverTheDogApiService(
        @Named("theDog") retrofit: Retrofit
    ) : TheDogAPI {
        return retrofit.create(TheDogAPI::class.java)
    }

    @Provides
    @Named("theDog")
    fun proverRepositoryImagemPetImpl(
        @Named("theDog") apiService:TheDogAPI
    ) : RepositoryImagemPet {
        return RepositoryImagemDogImpl( apiService )
    }

    @Provides
    @Named("theDog")
    fun proverGetImagensDogUseCase(
        @Named("theDog") repository : RepositoryImagemPet
    ) : GetImagensDogUseCase {
        return GetImagensDogUseCase(repository)
    }

}