package core.app

import androidx.lifecycle.ViewModel
import core.data.repository.AuthRepository
import core.models.local.User
import core.ui.components.toast.ToastState
import core.ui.components.toast.toastState
import core.ui.delegates.StateManager
import core.ui.delegates.ViewModelStateManager

object AppState

class AppUIViewModel(
  private val authRepository: AuthRepository
) : ViewModel(), StateManager<AppState> by ViewModelStateManager(
  AppState
), ToastState by toastState() {
  init {
    authRepository.setUser(User("0xa1b2c34d4", "John"))
  }

  val isLoggedIn = authRepository.getIsLoggedIn()
  fun getUser(): User? {
    return authRepository.getUser()
  }
}
