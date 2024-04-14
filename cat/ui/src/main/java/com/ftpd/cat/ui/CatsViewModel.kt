package com.ftpd.cat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState
import com.ftpd.cat.usecase.GetCatsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCatsInteractor: GetCatsInteractor
) : ViewModel() {
    val catsLiveData = MutableLiveData<DataState<BaseDomain>>()


    fun getPostList(baseDomain: BaseDomain) {
        viewModelScope.launch {
            getCatsInteractor.call(baseDomain).onEach {
                catsLiveData.value = it
            }.launchIn(viewModelScope)
        }

    }

}