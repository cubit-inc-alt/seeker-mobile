package core.features.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.models.enum.ChatType
import core.models.local.Chat
import core.ui.SingleEvent
import core.ui.components.toast.ToastState
import core.ui.components.toast.toastState
import core.ui.delegates.StateManager
import core.ui.delegates.ViewModelStateManager
import core.ui.navigation.AppNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class ChatSessionState(
  val id: String = "0xa1b2c34d4",
  val achievement: Int? = null,
  val isWalletActive: Boolean = true,
  val wager: Float? = 0.0f,
  val reset: Boolean = false,
  val nextDestination: SingleEvent<AppNavigation> = SingleEvent(),
)


class ChatViewModel(
) : ViewModel(), StateManager<ChatSessionState> by ViewModelStateManager(
  ChatSessionState()
), ToastState by toastState() {
  @OptIn(ExperimentalUuidApi::class)
  val chatDummyList = listOf(
    Chat(
      id = Uuid.random().toString(),
      message = "Hey! How's it going?",
      isLiked = false,
      likes = 0,
      dateTime = Clock.System.now().toEpochMilliseconds(),
      type = ChatType.SEND
    ),
    Chat(
      id = Uuid.random().toString(),
      message = "All good! Just working on something.",
      isLiked = true,
      likes = 2,
      dateTime = Clock.System.now().toEpochMilliseconds(),
      type = ChatType.RECEIVED
    ),
    Chat(
      id = Uuid.random().toString(),
      message = "Nice! Keep it up.",
      isLiked = false,
      likes = 1,
      dateTime = Clock.System.now().toEpochMilliseconds(),
      type = ChatType.SEND
    )
  )
  val chatList = MutableStateFlow<List<Chat>>(chatDummyList)
  val newChatList = MutableStateFlow<List<Chat>>(emptyList())


  @OptIn(ExperimentalUuidApi::class)
  fun sendMessage(message: String) {
    viewModelScope.launch {
      newChatList.value = newChatList.value + Chat(
        id = Uuid.random().toString(),
        message = message,
        isLiked = false,
        likes = 0,
        dateTime = Clock.System.now().toEpochMilliseconds(), type = ChatType.SEND
      )
      delay(1000)
      receiveMessage("Reply to $message")
    }

  }


  @OptIn(ExperimentalUuidApi::class)
  fun receiveMessage(message: String) {
    newChatList.value = newChatList.value + Chat(
      id = Uuid.random().toString(),
      message = message,
      isLiked = false,
      likes = 0,
      dateTime = Clock.System.now().toEpochMilliseconds(), type = ChatType.RECEIVED
    )
  }

  fun likeChat(id: String) {
    fun toggleLike(list: List<Chat>): List<Chat>? {
      var changed = false
      val newList = list.map {
        if (it.id == id) {
          changed = true
          it.copy(isLiked = !it.isLiked, likes = if (it.isLiked) it.likes - 1 else it.likes + 1)
        } else it
      }
      return if (changed) newList else null
    }

    toggleLike(newChatList.value)?.let {
      newChatList.value = it
      return
    }

    toggleLike(chatList.value)?.let {
      chatList.value = it
    }
  }

}
