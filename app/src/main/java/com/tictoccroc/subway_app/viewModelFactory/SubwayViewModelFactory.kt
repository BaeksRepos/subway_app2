package com.tictoccroc.subway_app.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tictoccroc.subway_app.repository.SubwayRepository

class SubwayViewModelFactory(private val subwayRepository:SubwayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repositoryInstance = modelClass.getConstructor(SubwayRepository::class.java).newInstance(subwayRepository);
        return repositoryInstance;
    }
}