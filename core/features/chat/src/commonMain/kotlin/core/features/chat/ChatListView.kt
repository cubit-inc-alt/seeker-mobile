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
import core.database.model.MessageEntity
import core.designSystem.theme.AppDimensions.size_12
import core.designSystem.theme.AppDimensions.size_16
import core.designSystem.theme.AppDimensions.size_32
import core.features.chat.card.ChatReceiveCard
import core.features.chat.card.ChatSentCard
import core.ui.components.drawVerticalScrollbar

@Composable
fun ChatListView(
  loggedInUserId: String,
  chatList: List<MessageEntity>,
  onLike: (String) -> Unit
) {
  val listState = rememberLazyListState()
  val density = LocalDensity.current

  LaunchedEffect(chatList.size) {
    if (chatList.isNotEmpty()) {
      val extraOffset = with(density) { 100.dp.toPx().toInt() }
      listState.animateScrollToItem(index = chatList.lastIndex, scrollOffset = extraOffset)
    }
  }

  LazyColumn(
    state = listState,
    modifier = Modifier.fillMaxSize()
      .drawVerticalScrollbar(listState, bottomPaddingOffset = 40f),
    verticalArrangement = Arrangement.spacedBy(size_12)
  ) {
    items(chatList) { message ->
      if (message.senderId == loggedInUserId) {
        ChatSentCard(
          modifier = Modifier.fillMaxWidth().padding(start = size_32)
            .offset(x = -size_16),
          id = message.senderId,
          dateTime = message.sentAt,
          message = message.message,
          isLiked = false,
          likes = message.likedBy.size,
        ) {
          onLike(it)
        }
      } else {
        ChatReceiveCard(
          modifier = Modifier.fillMaxWidth().padding(end = size_32),
          id = message.senderId,
          dateTime = message.sentAt,
          message = message.message,
          isLiked = false,
          likes = message.likedBy.size,
        ) {
          onLike(it)
        }
      }
    }

  }
}
