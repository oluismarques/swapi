package com.swapi.tmdb.launchpad

import com.swap.util.test.MainDispatcherRule
import com.swapi.tmdb.domain.movie.FakeMoviesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LaunchpadViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LaunchpadViewModel

    private lateinit var fakeMoviesRepository: FakeMoviesRepository


    @Before
    fun setup() {
        fakeMoviesRepository = FakeMoviesRepository()
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )
    }

    @Test
    fun `trendingUiState emits Error state`() = runTest {
        fakeMoviesRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )

        val resultState = viewModel.trendingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Error)
    }


    @Test
    fun `trendingUiState emits Success state`() = runTest {
        val resultState = viewModel.trendingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Success)
    }

    @Test
    fun `popularUiState emits Error state`() = runTest {
        fakeMoviesRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )

        val resultState = viewModel.popularUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Error)
    }

    @Test
    fun `popularUiState emits Success state`() = runTest {
        val resultState = viewModel.popularUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Success)
    }

    @Test
    fun `nowPlayingUiState emits Error state`() = runTest {
        fakeMoviesRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )

        val resultState = viewModel.nowPlayingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Error)
    }

    @Test
    fun `nowPlayingUiState emits Success state`() = runTest {
        val resultState = viewModel.nowPlayingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Success)
    }

    @Test
    fun `topRatedUiState emits Error state`() = runTest {
        fakeMoviesRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )

        val resultState = viewModel.topRatedUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Error)
    }

    @Test
    fun `topRatedUiState emits Success state`() = runTest {
        val resultState = viewModel.topRatedUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Success)
    }

    @Test
    fun `upcomingUiState emits Error state`() = runTest {
        fakeMoviesRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = LaunchpadViewModel(
            moviesRepository = fakeMoviesRepository
        )

        val resultState = viewModel.upcomingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Error)
    }

    @Test
    fun `upcomingUiState emits Success state`() = runTest {
        val resultState = viewModel.upcomingUiState.value
        Assert.assertTrue(resultState is LaunchpadResultUiState.Success)
    }
}
