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


class DetailRepositoryImpTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: DetailRepositoryImp

    private lateinit var fakeMoviesService: FakeMoviesService

    @Before
    fun setup() {

        fakeMoviesService = FakeMoviesService(
            Json { ignoreUnknownKeys = true },
            JvmUnitTestFakeAssetManager
        )

        subject = DetailRepositoryImp(
            ioDispatcher = UnconfinedTestDispatcher(),
            moviesService = fakeMoviesService
        )
    }

    @Test
    fun `getMovieDetail() returns correct resource`() = testScope.runTest {
        val expectedData =
            fakeMoviesService.getMovieDetail(1096197).asDomainModel()

        val resultFlow = subject.getMovieDetail(2)

        val result = resultFlow.first()
        assertTrue(result is Resource.Loading)

        val emittedResult = resultFlow.drop(1).first() // Drop loading emission
        assertEquals(expectedData, (emittedResult as Resource.Success).data)
    }

    @Test
    fun `getMovieDetail() handles error`() = testScope.runTest {
        fakeMoviesService.setShouldFail(true)

        val resultFlow = subject.getMovieDetail(1).drop(1).first() // Drop loading emission

        assertTrue(resultFlow is Resource.Error)
    }

}