package com.swapi.tmdb.launchpad

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import com.swap.util.test.assertAreDisplayed
import com.swap.util.test.assertExists
import com.swap.util.test.assertHasClickAction
import com.swapi.tmdb.domain.movie.mockMovies
import com.swapi.tmdb.feature.launchpad.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LaunchpadScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var errorMessage: String
    private lateinit var emptyMessage: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            errorMessage = getString(R.string.launchpad_error_message)
            emptyMessage = getString(R.string.launchpad_empty_results)
        }
    }

    @Test
    fun movieDetails_whenTrendingIsSuccess_isShown() {
        composeTestRule.setContent {
            LaunchPadScreen(
                trendingResultUiState = LaunchpadResultUiState.Success(mockMovies),
                popularResultUiState = LaunchpadResultUiState.Success(mockMovies),
                upcomingUiState = LaunchpadResultUiState.Success(mockMovies),
                nowPlayingUiState = LaunchpadResultUiState.Success(mockMovies),
                topRatedUiState = LaunchpadResultUiState.Success(mockMovies),
                navigateToDetail = {}
            )
        }

        with(composeTestRule) {
            onAllNodesWithText(mockMovies.first().originalTitle).assertAreDisplayed()
            onAllNodesWithText(mockMovies.first().posterUrl.orEmpty()).assertAreDisplayed()
            onAllNodesWithTag("movie_card_tag").assertHasClickAction()
        }
    }

    @Test
    fun loading_indicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            LaunchPadScreen(
                trendingResultUiState = LaunchpadResultUiState.Loading,
                popularResultUiState = LaunchpadResultUiState.Loading,
                upcomingUiState = LaunchpadResultUiState.Loading,
                nowPlayingUiState = LaunchpadResultUiState.Loading,
                topRatedUiState = LaunchpadResultUiState.Loading,
                navigateToDetail = {}
            )
        }

        composeTestRule.onAllNodesWithTag("loading_tag").assertExists()
    }

    @Test
    fun empty_whenScreenIsEmpty_isShow() {
        composeTestRule.setContent {
            LaunchPadScreen(
                trendingResultUiState = LaunchpadResultUiState.Empty,
                popularResultUiState = LaunchpadResultUiState.Empty,
                upcomingUiState = LaunchpadResultUiState.Empty,
                nowPlayingUiState = LaunchpadResultUiState.Empty,
                topRatedUiState = LaunchpadResultUiState.Empty,
                navigateToDetail = {}
            )
        }

        composeTestRule.onAllNodesWithText(emptyMessage).assertExists()
        composeTestRule.onAllNodesWithTag("empty_tag").assertExists()
    }

    @Test
    fun error_whenScreenIsError_isShow() {
        composeTestRule.setContent {
            LaunchPadScreen(
                trendingResultUiState = LaunchpadResultUiState.Error,
                popularResultUiState = LaunchpadResultUiState.Error,
                upcomingUiState = LaunchpadResultUiState.Error,
                nowPlayingUiState = LaunchpadResultUiState.Error,
                topRatedUiState = LaunchpadResultUiState.Error,
                navigateToDetail = {}
            )
        }

        composeTestRule.onAllNodesWithText(errorMessage).assertExists()
        composeTestRule.onAllNodesWithTag("error_tag").assertExists()
    }
}



