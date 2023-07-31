package com.example.dogshow.data.repository

import com.example.dogshow.data.api.TheDogAPI
import com.example.dogshow.data.dto.toImagemPet
import com.example.dogshow.domain.model.ImagensPets
import javax.inject.Inject
import javax.inject.Named

class RepositoryImagemDogImpl @Inject constructor(
    @Named("theDog") private val apiService : TheDogAPI
) : RepositoryImagemPet {

    override suspend fun recuperarImagensPet(limite : Int): List<ImagensPets> {

        try {
            val response = apiService.getImages(limite)
            if (response.isSuccessful && response.body() != null) {
                val respostaTheDogApiDTO = response.body()

               val listaImagensPet = respostaTheDogApiDTO?.map { it.toImagemPet() }

                if (listaImagensPet != null) {
                    return listaImagensPet
                }
            }

        } catch (e: Exception){
            e.printStackTrace()
            return emptyList()
        }

        return emptyList()
    }

}