package com.example.countermvvm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val repository: CounterRepository = CounterRepository()
    private val _count = mutableStateOf(repository.getCounter().count)

    // Exposing the count to view
    val count: MutableState<Int> = _count

    fun increment() {
        repository.IncrementCounter()
        _count.value = repository.getCounter().count
    }

    fun decrement() {
        repository.DecrementCounter()
        _count.value = repository.getCounter().count
    }
}