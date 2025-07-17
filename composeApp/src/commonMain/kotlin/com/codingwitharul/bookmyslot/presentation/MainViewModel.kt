package com.codingwitharul.bookmyslot.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val getUserInfo: GetUserInfoUseCase) : ViewModel() {

    private val _timerState = mutableStateOf(0L)
    val timerState: State<Long> = _timerState

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo = _userInfo.asStateFlow()

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            getUserInfo().onSuccess {
                setUserInfo(it)
            }.onFailure {
                setUserInfo(null)
            }
        }
    }

    fun setUserInfo(userInfo: UserInfo?) {
        print("UserInfo $userInfo")
        _userInfo.value = userInfo
    }
}