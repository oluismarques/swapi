package com.swapi.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import com.swapi.designsystem.theme.Dimen24
import com.swapi.designsystem.theme.MoviesTheme
import com.swapi.tmdb.designsystem.R


@Composable
fun DSSearchTopBar(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        shape = RoundedCornerShape(Dimen24),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = onQueryChange,
        placeholder = { Text(stringResource(id = R.string.search_placeholder)) },
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = "search")
        },
        trailingIcon = {
            IconButton(onClick = { onQueryChange(TextFieldValue("")) }) {
                Icon(Icons.Rounded.Close, contentDescription = "clear")
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
    )
}

@Composable
@ThemePreviews
fun DSSearchTopBarPreview() {
    MoviesTheme {
        DSSearchTopBar(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue("Justice"),
            onQueryChange = { _ -> },
        )
    }
}
