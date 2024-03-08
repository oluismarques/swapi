package com.swapi.search

sealed interface SearchEvent {
    data class ChangeQuery(val value: String) : SearchEvent
}
