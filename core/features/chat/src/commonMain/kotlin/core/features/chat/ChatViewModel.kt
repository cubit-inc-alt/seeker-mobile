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
import core.ui.toEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class ChatSessionState(
    val id: String = "0xa1b2c34d4",
    val sendMessageEnable: SingleEvent<Boolean> = SingleEvent(),
    val nextDestination: SingleEvent<AppNavigation> = SingleEvent(),
)

const val MESSAGE_COOL_DOWN_TIME = 60

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
    var remainingTime = MutableStateFlow(0)


    @OptIn(ExperimentalUuidApi::class)
    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newChatList.value = newChatList.value + Chat(
                id = Uuid.random().toString(),
                message = message,
                isLiked = false,
                likes = 0,
                dateTime = Clock.System.now().toEpochMilliseconds(),
                type = ChatType.SEND
            )
            delay(1000)
            receiveMessage("Reply to $message")
        }
        startSendMessageCooldown(MESSAGE_COOL_DOWN_TIME - 1)
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
                    it.copy(
                        isLiked = !it.isLiked,
                        likes = if (it.isLiked) it.likes - 1 else it.likes + 1
                    )
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
