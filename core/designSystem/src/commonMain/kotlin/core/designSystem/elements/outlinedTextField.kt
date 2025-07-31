package core.designSystem.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import core.designSystem.theme.AppDimensions.size_24
import core.designSystem.theme.AppDimensions.size_26
import core.designSystem.theme.AppTheme.colors
import core.designSystem.theme.AppTheme.typography

@Composable
fun DefaultOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  modifier: Modifier = Modifier,
  isError: Boolean = false,
  errorText: String? = null,
  enabled: Boolean = true,
  leadingIcon: Painter? = null,
  leadingIconView: (@Composable () -> Unit)? = null,
  trailingIcon: (@Composable () -> Unit)? = null,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = true,
) {
  Column(modifier = modifier) {
    OutlinedTextField(
      value = value,
      onValueChange = onValueChange,
      label = {
        Text(text = label, color = colors.onSurfaceVariant)
      },
      isError = isError,
      modifier = Modifier.fillMaxWidth(),
      leadingIcon = leadingIcon?.let {
        {
          Icon(
            painter = it,
            contentDescription = null,
            modifier = Modifier.size(size_24)
          )
        }
      } ?: leadingIconView,
      enabled = enabled,
      trailingIcon = trailingIcon,
      visualTransformation = visualTransformation,
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      singleLine = singleLine
    )
    if (!errorText.isNullOrBlank()) {
      AnimatedVisibility(visible = true) {
        Text(
          text = errorText,
          color = colors.error,
          style = typography.body.SmallMedium,
        )
      }
    }
  }
}


@Composable
fun SimpleOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String?,
  hint: String? = null,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  leadingIcon: Painter? = null,
  leadingIconView: (@Composable () -> Unit)? = null,
  trailingIcon: (@Composable () -> Unit)? = null,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = true,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = label?.let {
      {
        Text(text = label, color = colors.onSurfaceVariant)
      }
    },
    modifier = modifier,
    enabled = enabled,
    placeholder = {
      if (value.isEmpty()) {
        Text(text = hint ?: "", color = colors.onSurfaceVariant, style  = typography.body.DefaultRegular)
      }
    },
    leadingIcon = leadingIcon?.let {
      {
        Icon(
          painter = it,
          contentDescription = null,
          modifier = Modifier.size(size_24)
        )
      }
    } ?: leadingIconView,
    trailingIcon = trailingIcon,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    shape = RoundedCornerShape(size_26),
    colors = OutlinedTextFieldDefaults.colors().copy(
      unfocusedIndicatorColor = colors.outlineVariant
    )
  )

}


val nonFocusableTextFieldColor: TextFieldColors
  @Composable get() = colors(
    disabledBorderColor = colors.outline,
    disabledLeadingIconColor = colors.onSurfaceVariant,
    disabledTrailingIconColor = colors.onSurfaceVariant,
    disabledPlaceholderColor = colors.onSurface,
    disabledLabelColor = colors.onSurface,
    disabledTextColor = colors.onSurface
  )
