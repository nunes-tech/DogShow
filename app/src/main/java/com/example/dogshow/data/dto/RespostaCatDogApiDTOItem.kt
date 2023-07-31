package com.example.dogshow.data.dto

import com.example.dogshow.domain.model.ImagensPets

data class RespostaTheDogApiDTOItem(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

fun RespostaTheDogApiDTOItem.toImagemPet() : ImagensPets {
    return ImagensPets(
        link = this.url,
        altura = this.height,
        largura = this.width,
        codigo = this.id
    )
}