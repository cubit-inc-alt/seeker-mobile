package core.features.chat

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.common.Platform
import core.common.platform
import core.data.repository.ChatRepository
import core.database.model.MessageEntity
import core.ui.SingleEvent
import core.ui.components.toast.ToastState
import core.ui.components.toast.toastState
import core.ui.delegates.StateManager
import core.ui.delegates.ViewModelStateManager
import core.ui.delegates.setValue
import core.ui.toEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.uuid.ExperimentalUuidApi


data class ChatSessionState(
  val user: String? = null,
  val sendMessageEnable: SingleEvent<Boolean> = SingleEvent(),
)

const val MESSAGE_COOL_DOWN_TIME = 10

const val globalChatRoomId = "global"


class ChatViewModel(
  val chatRepository: ChatRepository,
) : ViewModel(), StateManager<ChatSessionState> by ViewModelStateManager(
  ChatSessionState()
), ToastState by toastState() {
  val messages = MutableStateFlow<List<MessageEntity>>(emptyList())
  var remainingTime = MutableStateFlow(0)

  val userId = "0x112${if (platform()== Platform.Android) "6723adf" else "6723bbc"}"


  init {
    listenForMessages()
  }

  fun listenForMessages() {
    viewModelScope.launch {
      chatRepository.listenForMessageInRoom(globalChatRoomId)
    }

    viewModelScope.launch {
      chatRepository.getMessageInRoom(globalChatRoomId)
        .map { message -> message.sortedBy { it.sentAt } }
        .collect {
          messages.setValue(it)
        }
    }
  }

  @OptIn(ExperimentalUuidApi::class)
  fun sendMessage(message: String) {
    viewModelScope.launch(Dispatchers.IO) {
      startSendMessageCooldown(MESSAGE_COOL_DOWN_TIME)
      chatRepository.sentNewMessage(globalChatRoomId, userId, message)
    }
  }

  fun startSendMessageCooldown(seconds: Int) {
    updateState { copy(sendMessageEnable = false.toEvent()) }
    remainingTime.value = seconds

    viewModelScope.launch {
      while (remainingTime.value > 0) {
        delay(1000)
        remainingTime.value -= 1
      }
      updateState { copy(sendMessageEnable = true.toEvent()) }
    }
  }

  fun likeChat(id: String) {
//    fun toggleLike(list: List<MessageEntity>): List<MessageEntity>? {
//      var changed = false
//      val newList = list.map {
//        if (it.id == id) {
//          changed = true
//          it.copy(
//            isLiked = !it.isLiked,
//            likes = if (it.isLiked) it.likes - 1 else it.likes + 1
//          )
//        } else it
//      }
//      return if (changed) newList else null
//    }
//
//    toggleLike(messages.value)?.let {
//      messages.value = it
//    }
  }

}


fun Instant.relativeTime(now: Instant = Clock.System.now()): String {
  val duration = now - this
  val seconds = duration.inWholeSeconds

  return when {
    seconds < 20 -> "now"
    seconds < 60 -> "${seconds}s ago"
    duration.inWholeMinutes < 60 -> {
      val minutes = duration.inWholeMinutes
      "${minutes}m ago"
    }

    duration.inWholeHours < 24 -> {
      val hours = duration.inWholeHours
      "${hours}s ago"
    }

    duration.inWholeDays < 30 -> {
      val days = duration.inWholeDays
      "${days}s ago"
    }

    duration.inWholeDays < 365 -> {
      val months = duration.inWholeDays / 30
      "${months}m ago"
    }

    else -> {
      val years = duration.inWholeDays / 365
      "${years}y ago"
    }
  }
}
