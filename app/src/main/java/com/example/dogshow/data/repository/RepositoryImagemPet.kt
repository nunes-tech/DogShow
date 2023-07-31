package com.example.dogshow.data.repository

import com.example.dogshow.domain.model.ImagensPets

interface RepositoryImagemPet {

    suspend fun recuperarImagensPet(limite : Int) : List<ImagensPets>

}