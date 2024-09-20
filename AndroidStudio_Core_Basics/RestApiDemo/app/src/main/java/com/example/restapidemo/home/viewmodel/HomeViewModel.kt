package com.example.restapidemo.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restapidemo.home.data.HomeRepository
import com.example.restapidemo.home.data.PostModel


// calling this method from our view model
class HomeViewModel {
    private var homeRepository : HomeRepository?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }
}