package com.swapi.tmdb.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.swap.util.asMutable
import com.swap.util.test.MainDispatcherRule
import com.swapi.tmdb.domain.detail.FakeDetailRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs


class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DetailViewModel

    private lateinit var fakeDetailRepository: FakeDetailRepository

    private val savedStateHandle = SavedStateHandle(mapOf(ARG_ID to 2222))


    @Before
    fun setup() {
        fakeDetailRepository = FakeDetailRepository()
        viewModel = DetailViewModel(
            savedStateHandle = savedStateHandle, detailRepository = fakeDetailRepository
        )
    }

    @Test
    fun `detailResultUiState emits Error state`() = runTest {
        fakeDetailRepository.setShouldFail(true)

        // Act: Initialize the ViewModel, triggering the repository call indirectly
        viewModel = DetailViewModel(
            savedStateHandle = savedStateHandle,
            detailRepository = fakeDetailRepository
        )

        val resultState = viewModel.detailResultUiState.value
        assertTrue(resultState is DetailResultUiState.Error)
    }


    @Test
    fun `detailResultUiState emits Success state`() = runTest {
        val resultState = viewModel.detailResultUiState.value
        assertTrue(resultState is DetailResultUiState.Success)
    }
}

