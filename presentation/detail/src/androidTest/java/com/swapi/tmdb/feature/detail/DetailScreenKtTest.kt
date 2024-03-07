package com.swapi.tmdb.feature.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.swap.util.test.assertExists
import com.swapi.tmdb.domain.detail.mockDetailItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var errorMessage: String
    private lateinit var noGenre: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            errorMessage = getString(R.string.detail_something_went_wrong)
            noGenre = getString(R.string.no_genre)
        }
    }


    @Test
    fun detailItem_whenTrendingIsSuccess_isShown() {
        composeTestRule.setContent {
            DetailScreen(
                detailResultUiState = DetailResultUiState.Success(mockDetailItem),
                onBackButtonClick = { -> }
            )
        }

        with(composeTestRule) {
            onNodeWithText(
                composeTestRule.activity.getString(
                    R.string.details_runtime_text,
                    mockDetailItem.runtime,
                )
            ).assertIsDisplayed()

            onNodeWithText(
                composeTestRule.activity.getString(
                    R.string.details_vote_count,
                    mockDetailItem.voteCount,
                )
            ).assertIsDisplayed()

            onNodeWithText(mockDetailItem.title).assertIsDisplayed()
            onNodeWithText(mockDetailItem.overview).assertIsDisplayed()
            onNodeWithText(mockDetailItem.releaseDate.orEmpty()).assertIsDisplayed()
            onNodeWithText(noGenre).assertIsDisplayed()
            onNodeWithTag("posterPath_tag").assertExists()
        }
    }

    @Test
    fun loading_indicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            DetailScreen(
                detailResultUiState = DetailResultUiState.Success(mockDetailItem),
                onBackButtonClick = { -> }
            )
        }

        composeTestRule.onAllNodesWithTag("loading_tag").assertExists()
    }

    @Test
    fun error_whenScreenIsError_isShow() {
        composeTestRule.setContent {
            DetailScreen(
                detailResultUiState = DetailResultUiState.Success(mockDetailItem),
                onBackButtonClick = { -> }
            )
        }

        composeTestRule.onAllNodesWithText(errorMessage).assertExists()
        composeTestRule.onAllNodesWithTag("error_tag").assertExists()
    }
}