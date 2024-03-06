package com.swapi.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swapi.designsystem.theme.DSRed
import com.swapi.designsystem.theme.Dimen16
import com.swapi.designsystem.theme.Dimen2
import com.swapi.designsystem.theme.Dimen4
import com.swapi.designsystem.theme.Dimen8
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.tmdb.designsystem.R

@Composable
fun DSRatingItem(
    modifier: Modifier = Modifier,
    rating: String,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Dimen8),
        colors = CardDefaults.cardColors(
            contentColor = DSRed
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = Dimen4,
                vertical = Dimen4
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DSIcon(
                modifier = Modifier.size(Dimen16),
                iconResourceId = R.drawable.ic_star,
                contentDescription = "start"
            )
            Spacer(modifier = Modifier.width(Dimen2))
            Text(
                text = rating,
                style = TMDBTypography.labelMedium
            )
        }
    }
}

@ThemePreviews
@Composable
private fun DSImagePreview() {
    DSRatingItem(
        modifier = Modifier, rating = "Theo"
    )
}
