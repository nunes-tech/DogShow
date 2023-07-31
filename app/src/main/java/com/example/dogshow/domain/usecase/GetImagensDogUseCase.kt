package com.example.dogshow.domain.usecase

import com.example.dogshow.data.repository.RepositoryImagemPet
import com.example.dogshow.domain.model.ImagensPets
import javax.inject.Inject
import javax.inject.Named

class GetImagensDogUseCase @Inject constructor(
    @Named("theDog") private val repository : RepositoryImagemPet
)  {
    suspend operator fun invoke(limite : Int) : List<ImagensPets> {

        try {
            val lista = repository.recuperarImagensPet(limite)
            if ( lista.isNotEmpty() ) {
                return lista
            }

        } catch (erroException : Exception) {
            erroException.printStackTrace()
            return emptyList()
        }
        return emptyList()
    }
}