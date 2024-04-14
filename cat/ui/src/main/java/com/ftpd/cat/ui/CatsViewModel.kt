package com.ftpd.cat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat
import com.ftpd.base.DataState
import com.ftpd.cat.usecase.AddFavoriteCatInteractor
import com.ftpd.cat.usecase.GetCatsInteractor
import com.ftpd.cat.usecase.GetFavoriteCatsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCatsInteractor: GetCatsInteractor,
    private val getFavoriteCatsInteractor: GetFavoriteCatsInteractor,
    private val addFavoriteCatInteractor: AddFavoriteCatInteractor
) : ViewModel() {
    val catsLiveData = MutableLiveData<DataState<BaseDomain>>()
    val favoriteCatsLiveData = MutableLiveData<DataState<BaseDomain>>()


    fun getCatList(baseDomain: BaseDomain) {
        viewModelScope.launch {
            getCatsInteractor.call(baseDomain).onEach {
                catsLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }
    fun getFavoriteCatList() {
        viewModelScope.launch {
            getFavoriteCatsInteractor.call().onEach {
                favoriteCatsLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }
    fun addFavoriteCat(cat: Cat){
        viewModelScope.launch {
            addFavoriteCatInteractor.run(cat)
        }
    }

}