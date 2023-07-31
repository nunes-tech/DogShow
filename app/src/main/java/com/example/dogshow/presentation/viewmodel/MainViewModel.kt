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
class MainViewModel @Inject constructor(
    @Named("theDog") private val getImagesDogUseCase: GetImagensDogUseCase,
    @Named("theCat") private val getImagesCatUseCase: GetImagensCatUseCase
) : ViewModel() {

    private val _listaImagensDogs = MutableLiveData<List<ImagensPets>>()
    val listaImagensDogs:LiveData<List<ImagensPets>>
        get() = _listaImagensDogs

    private val _listaImagensCats = MutableLiveData<List<ImagensPets>>()
    val listaImagensCats:LiveData<List<ImagensPets>>
        get() = _listaImagensCats

    @SuppressLint("SuspiciousIndentation")
    fun recuperarImagensDogs(limite : Int){
        viewModelScope.launch {
          val listaImagens =  getImagesDogUseCase(limite)
            _listaImagensDogs.postValue( listaImagens )
        }
    }

    fun recuperarImagensCats(limite : Int){
        viewModelScope.launch {
            val listaImagens =  getImagesCatUseCase(limite)
            _listaImagensCats.postValue( listaImagens )
        }
    }

}