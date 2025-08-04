package core.features.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.designSystem.elements.SimpleOutlinedTextField
import core.designSystem.theme.AppDimensions.size_12
import core.designSystem.theme.AppDimensions.size_16
import core.designSystem.theme.AppDimensions.size_20
import core.designSystem.theme.AppDimensions.size_26
import core.designSystem.theme.AppDimensions.size_30
import core.designSystem.theme.AppDimensions.size_4
import core.designSystem.theme.AppDimensions.size_6
import core.designSystem.theme.AppTheme.colors
import core.designSystem.theme.AppTheme.typography
import core.resources.generated.resources.Res
import core.resources.generated.resources.banter
import core.resources.generated.resources.forum
import core.resources.generated.resources.ic_achievement
import core.resources.generated.resources.ic_close
import core.resources.generated.resources.ic_info
import core.resources.generated.resources.ic_send
import core.resources.generated.resources.ic_send_enabled
import core.resources.generated.resources.ic_speech_recognization
import core.resources.generated.resources.post_restriction_notice
import core.resources.generated.resources.seeker
import core.resources.generated.resources.send_message
import core.resources.generated.resources.you_can_message_again_in
import core.ui.navigation.AppNavigation
import core.ui.rememberMutableStateOf
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChatScreen(
  viewModel: ChatViewModel,
  navigateToNext: (AppNavigation) -> Unit
) {
  val state by viewModel.state.collectAsState()
  val chatList by viewModel.messages.collectAsState()
  val remainingTime by viewModel.remainingTime.collectAsState()
  var inputMessage by rememberMutableStateOf("")
  var showTimeWarning by rememberMutableStateOf<Boolean?>(false)

  val focusManager = LocalFocusManager.current
  LaunchedEffect(state.sendMessageEnable) {
    state.sendMessageEnable.actUpOn {
      showTimeWarning = !it
    }
  }


  Scaffold(
    modifier = Modifier,
    containerColor = Color.Transparent, bottomBar = {
      val imePadding = WindowInsets.ime.getBottom(LocalDensity.current)
      val showExtraPadding = imePadding > with(LocalDensity.current) { 100.dp.toPx() }

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .padding(bottom = if (showExtraPadding) size_20 else 0.dp)
      ) {
        Box(
          modifier = Modifier.fillMaxWidth().height(size_12).background(Color.Transparent)
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent, Color.Black.copy(alpha = 0.04f)
                )
              )
            )
        )
        if (showTimeWarning == true) {
          Row(
            Modifier.fillMaxWidth().wrapContentHeight().padding(size_16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(size_16)
          ) {
            Icon(
              painter = painterResource(Res.drawable.ic_info),
              contentDescription = "",
              modifier = Modifier.wrapContentHeight(),
            )
            Text(
              stringResource(Res.string.post_restriction_notice),
              style = typography.body.SmallRegular,
              color = colors.secondary,
              modifier = Modifier.weight(1f),
              textAlign = TextAlign.Center,
              overflow = TextOverflow.Ellipsis,
              maxLines = 1
            )

            Icon(
              painter = painterResource(Res.drawable.ic_close),
              contentDescription = "",
              modifier = Modifier.wrapContentHeight()
                .clickable { showTimeWarning = null },
            )
          }
          HorizontalDivider()
        }
        Row(
          Modifier.fillMaxWidth().wrapContentHeight()
            .padding(vertical = size_20, horizontal = size_16),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(size_16)
        ) {


          SimpleOutlinedTextField(
            value = inputMessage,
            label = null,
            hint = if (showTimeWarning != false)
              stringResource(resource = Res.string.you_can_message_again_in) + " $remainingTime s"
            else stringResource(Res.string.send_message),
            onValueChange = {
              inputMessage = it
            },
            enabled = showTimeWarning == false,
            modifier = Modifier.weight(1f).wrapContentHeight(),
            trailingIcon = {
              Icon(
                painter = painterResource(Res.drawable.ic_speech_recognization),
                contentDescription = "",
                Modifier.clickable {
                })
            },
          )
          Image(
            painter = if (inputMessage.isNotEmpty()) painterResource(Res.drawable.ic_send_enabled) else painterResource(
              Res.drawable.ic_send
            ),
            contentDescription = "",
            modifier = Modifier.clickable {
              if (inputMessage.isNotEmpty())
                viewModel.sendMessage(inputMessage)
              inputMessage = ""
            })

        }
      }
    }) { paddingValues ->
    Column(
      modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
      Row(
        Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = size_16)
          .padding(bottom = size_16),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(Modifier.size(size_30).clip(CircleShape).background(Color(0xFF6B7280)))
        Text(
          stringResource(Res.string.seeker),
          style = typography.heading.LargeSemiBold,
          modifier = Modifier.padding(start = size_6)
        )

        Text(
          state.user ?: "",
          modifier = Modifier.padding(horizontal = size_12).weight(1f),
          textAlign = TextAlign.End
        )

        Image(
          painter = painterResource(Res.drawable.ic_achievement),
          contentDescription = ""
        )
      }


      var selectedTabIndex by rememberMutableStateOf(0)
      val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
      val tabs = listOf(
        stringResource(Res.string.banter),
        stringResource(Res.string.forum),
      )
      val coroutineScope = rememberCoroutineScope()
      LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
      }
      TabRow(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
          .padding(horizontal = size_16, vertical = size_12)
          .clip(RoundedCornerShape(size_26)).background(Color(0xFFF3F4F6)),
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        divider = { null },
        indicator = { null }) {
        tabs.forEachIndexed { index, title ->
          Tab(
            modifier = Modifier.wrapContentSize().padding(size_4).clip(
              shape = RoundedCornerShape(size_26),
            )
              .background(if (selectedTabIndex == index) Color.White else Color.Transparent),
            interactionSource = null,
            selected = selectedTabIndex == index,
            onClick = {
              selectedTabIndex = index
              coroutineScope.launch {
                pagerState.scrollToPage(index)
              }

            },
            text = {
              Text(
                text = title,
                style = typography.heading.DefaultSemiBold,
                color = if (selectedTabIndex == index) LocalContentColor.current else colors.secondary

              )
            },
          )
        }
      }


      HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = size_16)
      ) { page ->

        when (page) {
          0 -> {
            ChatListView(
              loggedInUserId = viewModel.userId,
              chatList
            ) {
              viewModel.likeChat(it)
            }
          }

          else -> {

          }

        }
      }
    }
  }
}

