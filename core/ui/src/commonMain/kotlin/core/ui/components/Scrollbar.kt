package core.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.fastSumBy
import core.designSystem.theme.AppDimensions.size_8
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun Modifier.drawHorizontalScrollbar(
  state: ScrollState, reverseScrolling: Boolean = false
): Modifier = drawScrollbar(state, Orientation.Horizontal, reverseScrolling)

fun Modifier.drawVerticalScrollbar(
  state: ScrollState, reverseScrolling: Boolean = false
): Modifier = drawScrollbar(state, Orientation.Vertical, reverseScrolling)

private fun Modifier.drawScrollbar(
  state: ScrollState, orientation: Orientation, reverseScrolling: Boolean
): Modifier = drawScrollbar(
  orientation, reverseScrolling
) { reverseDirection, atEnd, color, alpha ->
  if (state.maxValue > 0) {
    val canvasSize = if (orientation == Orientation.Horizontal) size.width else size.height
    val totalSize = canvasSize + state.maxValue
    val thumbSize = canvasSize / totalSize * canvasSize
    val startOffset = state.value / totalSize * canvasSize
    drawScrollbar(
      orientation, reverseDirection, atEnd, color, alpha, thumbSize, startOffset
    )
  }
}

fun Modifier.drawHorizontalScrollbar(
  state: LazyListState, reverseScrolling: Boolean = false, bottomPaddingOffset: Float = 0f
): Modifier = drawScrollbar(state, Orientation.Horizontal, reverseScrolling, bottomPaddingOffset)

fun Modifier.drawVerticalScrollbar(
  state: LazyListState, reverseScrolling: Boolean = false, bottomPaddingOffset: Float = 0f
): Modifier = drawScrollbar(state, Orientation.Vertical, reverseScrolling, bottomPaddingOffset)

private fun Modifier.drawScrollbar(
  state: LazyListState, orientation: Orientation, reverseScrolling: Boolean,
  bottomPaddingOffset: Float
): Modifier = drawScrollbar(
  orientation, reverseScrolling
) { reverseDirection, atEnd, color, alpha ->
  val layoutInfo = state.layoutInfo
  val viewportSize = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
  val items = layoutInfo.visibleItemsInfo
  val itemsSize = items.fastSumBy { it.size }
  if (items.size < layoutInfo.totalItemsCount || itemsSize > viewportSize) {
    val estimatedItemSize = if (items.isEmpty()) 0f else itemsSize.toFloat() / items.size
    val totalSize = estimatedItemSize * layoutInfo.totalItemsCount
    val canvasSize = if (orientation == Orientation.Horizontal) size.width else size.height
    val thumbSize = viewportSize / totalSize * canvasSize
    val startOffset = if (items.isEmpty()) 0f else items.first().run {
      (estimatedItemSize * index - offset) / totalSize * canvasSize
    } - bottomPaddingOffset
    drawScrollbar(
      orientation, reverseDirection, atEnd, color, alpha, thumbSize, startOffset
    )
  }
}

private fun DrawScope.drawScrollbar(
  orientation: Orientation,
  reverseDirection: Boolean,
  atEnd: Boolean,
  color: Color,
  alpha: () -> Float,
  thumbSize: Float,
  startOffset: Float
) {
  val thicknessPx = Thickness.toPx()
  val topLeft = if (orientation == Orientation.Horizontal) {
    Offset(
      if (reverseDirection) size.width - startOffset - thumbSize else startOffset,
      if (atEnd) size.height - thicknessPx else 0f
    )
  } else {
    Offset(
      if (atEnd) size.width - thicknessPx else 0f,
      (if (reverseDirection) size.height - startOffset - thumbSize else startOffset)
    )
  }
  val size = if (orientation == Orientation.Horizontal) {
    Size(thumbSize, thicknessPx)
  } else {
    Size(thicknessPx, thumbSize)
  }
  drawRoundRect(
    color = color,
    topLeft = topLeft,
    size = size,
    alpha = alpha(),
    cornerRadius = CornerRadius(50f)
  )
}

private fun Modifier.drawScrollbar(
  orientation: Orientation, reverseScrolling: Boolean, onDraw: DrawScope.(
    reverseDirection: Boolean, atEnd: Boolean, color: Color, alpha: () -> Float
  ) -> Unit
): Modifier = composed {
  val scrolled = remember {
    MutableSharedFlow<Unit>(
      extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
  }
  val nestedScrollConnection = remember(orientation, scrolled) {
    object : NestedScrollConnection {
      override fun onPostScroll(
        consumed: Offset, available: Offset, source: NestedScrollSource
      ): Offset {
        val delta = if (orientation == Orientation.Horizontal) consumed.x else consumed.y
        if (delta != 0f) scrolled.tryEmit(Unit)
        return Offset.Zero
      }
    }
  }

  val isLtr = LocalLayoutDirection.current == LayoutDirection.Ltr
  val reverseDirection = if (orientation == Orientation.Horizontal) {
    if (isLtr) reverseScrolling else !reverseScrolling
  } else reverseScrolling
  val atEnd = if (orientation == Orientation.Vertical) isLtr else true

  val color = BarColor

  Modifier.nestedScroll(nestedScrollConnection).drawWithContent {
    drawContent()
    onDraw(reverseDirection, atEnd, color, Animatable(1f)::value)
  }
}

private val BarColor: Color
  @Composable get() = Color(0xFFE2E8F0)

private val Thickness = size_8
