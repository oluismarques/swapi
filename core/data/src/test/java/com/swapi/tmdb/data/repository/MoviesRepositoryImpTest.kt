package com.swapi.tmdb.data.repository

import JvmUnitTestFakeAssetManager
import asDomainModel
import com.swap.util.Resource
import com.swapi.tmdb.data.network.service.fake.FakeMoviesService
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class MoviesRepositoryImpTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: MoviesRepositoryImp

    private lateinit var fakeMoviesService: FakeMoviesService

    @Before
    fun setup() {

        fakeMoviesService = FakeMoviesService(
            Json { ignoreUnknownKeys = true },
            JvmUnitTestFakeAssetManager
        )

        subject = MoviesRepositoryImp(
            ioDispatcher = UnconfinedTestDispatcher(),
            moviesService = fakeMoviesService
        )
    }

    @Test
    fun `trendingMovies() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.trendingMovies().movieItemResponses
                .map { it.asDomainModel() }

        val resultFlow = subject.trendingMovies()

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }


    @Test
    fun `trendingMovies() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.trendingMovies().drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

    @Test
    fun `popularMovies() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.popularMovies().movieItemResponses
                .map { it.asDomainModel() }

        val resultFlow = subject.popularMovies()

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }

    @Test
    fun `popularMovies() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.popularMovies().drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

    @Test
    fun `nowPlayingMovies() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.nowPlayingMovies().movieItemResponses
                .map { it.asDomainModel() }

        val resultFlow = subject.nowPlayingMovies()

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }

    @Test
    fun `nowPlayingMovies() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.nowPlayingMovies().drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

    @Test
    fun `topRatedMovies() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.topRatedMovies().movieItemResponses
                .map { it.asDomainModel() }

        val resultFlow = subject.topRatedMovies()

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }

    @Test
    fun `topRatedMovies() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.topRatedMovies().drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

    @Test
    fun `upcomingMovies() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.upcomingMovies().movieItemResponses
                .map { it.asDomainModel() }

        val resultFlow = subject.upcomingMovies()

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }

    @Test
    fun `upcomingMovies() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.upcomingMovies().drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

}