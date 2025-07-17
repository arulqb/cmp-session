package com.codingwitharul.bookmyslot.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnBoardViewModel(val permissionsController: PermissionsController, val authRepo: AuthRepo) :
    ViewModel() {

    private var _state = MutableStateFlow(PermissionState())
    val state = _state.asStateFlow()

    suspend fun requestPermission(permission: Permission) {
        try {
            permissionsController.providePermission(permission)
            _state.value = state.value.copy(isGranted = true, permission = permission)
        } catch (e: DeniedException) {
            e.printStackTrace()
            _state.value = state.value.copy(
                isDenied = true,
                message = "Permission Denied",
                permission = permission
            )
        } catch (e: DeniedAlwaysException) {
            e.printStackTrace()
            _state.value = state.value.copy(
                isPermanentlyDeclined = true,
                message = "Permission Denied Permanently",
                permission = permission
            )
        } catch (e: RequestCanceledException) {
            e.printStackTrace()
            _state.value = state.value.copy(message = "Request Cancelled", permission = permission)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onBoardUser() {
        viewModelScope.launch {
            authRepo.updateOnBoardInfo(true)
        }
    }

    suspend fun isPermissionGranted(permission: Permission): Boolean =
        permissionsController.isPermissionGranted(permission)

}

data class PermissionState(
    val isPermanentlyDeclined: Boolean = false,
    val isGranted: Boolean = false,
    val isDenied: Boolean = false,
    val isCancelled: Boolean = false,
    val isRequesting: Boolean = false,
    val message: String = "",
    val permission: Permission? = null
)