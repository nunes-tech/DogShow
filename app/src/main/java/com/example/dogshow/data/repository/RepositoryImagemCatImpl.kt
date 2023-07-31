package com.example.dogshow.data.repository

import com.example.dogshow.data.api.TheCatAPI
import com.example.dogshow.data.dto.toImagemPet
import com.example.dogshow.domain.model.ImagensPets
import javax.inject.Inject
import javax.inject.Named

class RepositoryImagemCatImpl @Inject constructor(
    @Named("theCat") private val apiService : TheCatAPI
) : RepositoryImagemPet {

    override suspend fun recuperarImagensPet(limite: Int): List<ImagensPets> {
        try {
            val response = apiService.getImages(limite)
            if (response.isSuccessful && response.body() != null) {
                val respostaCatDogApiDTO = response.body()

                val listaImagensPet = respostaCatDogApiDTO?.map { it.toImagemPet() }

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