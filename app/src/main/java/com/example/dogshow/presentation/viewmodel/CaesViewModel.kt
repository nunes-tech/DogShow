package com.example.dogshow.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogshow.domain.model.ImagensPets
import com.example.dogshow.domain.usecase.GetImagensCatUseCase
import com.example.dogshow.domain.usecase.GetImagensDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CaesViewModel @Inject constructor(
    @Named("theDog") private val getImagesDogUseCase: GetImagensDogUseCase
) : ViewModel() {

    private val _listaImagensDogs = MutableLiveData<List<ImagensPets>>()
    val listaImagensDogs:LiveData<List<ImagensPets>>
        get() = _listaImagensDogs

    @SuppressLint("SuspiciousIndentation")
    fun recuperarImagensDogs(limite : Int){
        viewModelScope.launch {
          val listaImagens =  getImagesDogUseCase(limite)
            _listaImagensDogs.postValue( listaImagens )
        }
    }

}