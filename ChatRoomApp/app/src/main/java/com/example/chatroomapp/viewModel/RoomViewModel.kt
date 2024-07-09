package com.example.chatroomapp.viewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.Injection
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.Room
import com.example.chatroomapp.repository.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms
    private val roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRooms()
    }

    fun createRoom(name: String) {
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }

    fun loadRooms(){
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()){
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> Log.e("RoomData","Not able to create")
            }
        }
    }
}