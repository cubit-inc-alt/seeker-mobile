package core.features.chat.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.common.getRelativeTimeString
import core.common.maskString
import core.designSystem.theme.AppDimensions.size_10
import core.designSystem.theme.AppDimensions.size_12
import core.designSystem.theme.AppDimensions.size_16
import core.designSystem.theme.AppDimensions.size_26
import core.designSystem.theme.AppDimensions.size_4
import core.designSystem.theme.AppDimensions.size_6
import core.designSystem.theme.AppDimensions.size_8
import core.designSystem.theme.AppTheme.typography
import core.resources.generated.resources.Res
import core.resources.generated.resources.chat_triangle_head
import core.ui.rememberMutableStateOf
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatSentCard(
  modifier: Modifier,
  id: String, dateTime: Long, message: String, isLiked: Boolean, likes: Int, onLikeClicked: (String) -> Unit
) {
  var formattedDate by rememberMutableStateOf("")
  LaunchedEffect(dateTime) {
    formattedDate = getRelativeTimeString(dateTime)
  }

  Row(modifier.height(IntrinsicSize.Max), horizontalArrangement = Arrangement.End) {
    Column(
      modifier = Modifier.background(
        color = Color.Black, shape = RoundedCornerShape(size_8)
      ).wrapContentSize().padding(horizontal = size_12, vertical = size_8),
      verticalArrangement = Arrangement.spacedBy(size_8)
    ) {
      Box(Modifier.wrapContentSize().widthIn(min = 150.dp)) {
        Text(
          formattedDate,
          style = typography.body.SmallRegular,
          color = Color.White,
          modifier = Modifier.align(Alignment.TopEnd).padding(start = size_8)
        )
        Column(
          modifier = Modifier
            .align(Alignment.TopStart),
          horizontalAlignment = Alignment.Start
        ) {
          Text(id.maskString(), color = Color.White, style = typography.body.SmallRegular)



          Spacer(modifier = Modifier.height(size_4)) // Optional spacing

          Text(
            message, color = Color.White,
            style = typography.body.DefaultRegular,
          )
        }

      }
      Row(
        Modifier.background(color = Color(0xFFE5E7EB), RoundedCornerShape(size_26))
          .padding(horizontal = size_10, vertical = size_6),
        horizontalArrangement = Arrangement.spacedBy(size_8), verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = if (!isLiked) Icons.Outlined.FavoriteBorder else Icons.Default.Favorite,
          contentDescription = "Likes", Modifier.height(size_16).clickable { onLikeClicked(id) }
        )
        Text(
          likes.toString()
        )
      }

    }

    Icon(
      painter = painterResource(Res.drawable.chat_triangle_head),
      contentDescription = "",
      Modifier.align(Alignment.Bottom).rotate(-25f).offset(x = (-8).dp, y = (-2).dp), tint = Color.Black
    )


  }

}


