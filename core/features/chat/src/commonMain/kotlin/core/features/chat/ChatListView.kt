package core.features.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import core.designSystem.theme.AppDimensions.size_12
import core.designSystem.theme.AppDimensions.size_16
import core.designSystem.theme.AppDimensions.size_32
import core.features.chat.card.ChatReceiveCard
import core.features.chat.card.ChatSentCard
import core.models.enum.ChatType
import core.models.local.Chat
import core.ui.components.drawVerticalScrollbar

@Composable
fun ChatListView(chatList: List<Chat>, newChatList: List<Chat>, onLike: (String) -> Unit) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    LaunchedEffect(newChatList.size) {
        if (newChatList.isNotEmpty()) {
            val extraOffset = with(density) { 100.dp.toPx().toInt() }
            listState.animateScrollToItem(index = newChatList.lastIndex, scrollOffset = extraOffset)
        }
    }
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
            .drawVerticalScrollbar(listState, bottomPaddingOffset = 40f),
        verticalArrangement = Arrangement.spacedBy(size_12)
    ) {
        items(chatList) {
            when (it.type) {
                ChatType.SEND -> ChatSentCard(
                    modifier = Modifier.fillMaxWidth().padding(start = size_32)
                        .offset(x = -size_16),
                    id = it.id,
                    dateTime = it.dateTime,
                    message = it.message,
                    isLiked = it.isLiked,
                    likes = it.likes,
                ) {
                    onLike(it)
                }

                ChatType.RECEIVED -> ChatReceiveCard(
                    modifier = Modifier.fillMaxWidth().padding(end = size_32),
                    id = it.id,
                    dateTime = it.dateTime,
                    message = it.message,
                    isLiked = it.isLiked,
                    likes = it.likes,
                ) {
                    onLike(it)
                }
            }

        }
        items(newChatList) {
            when (it.type) {
                ChatType.SEND -> ChatSentCard(
                    modifier = Modifier.fillMaxWidth().padding(start = size_32)
                        .offset(x = -size_16),
                    id = it.id,
                    dateTime = it.dateTime,
                    message = it.message,
                    isLiked = it.isLiked,
                    likes = it.likes,
                ) {
                    onLike(it)
                }

                ChatType.RECEIVED -> ChatReceiveCard(
                    modifier = Modifier.fillMaxWidth().padding(end = size_32),
                    id = it.id,
                    dateTime = it.dateTime,
                    message = it.message,
                    isLiked = it.isLiked,
                    likes = it.likes,
                ) {
                    onLike(it)
                }
            }

        }


    }
}
