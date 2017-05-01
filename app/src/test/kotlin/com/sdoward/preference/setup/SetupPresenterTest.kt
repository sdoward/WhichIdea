package com.sdoward.preference.setup

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sdoward.preference.data.Repository
import com.sdoward.preference.signin.User
import io.reactivex.Single
import org.junit.Test

import org.mockito.Mockito
import java.util.concurrent.TimeUnit

class SetupPresenterTest {

    val repository: Repository = mock()
    val setUpContract: SetUpContract = mock()
    val presenter = SetupPresenter(repository, setUpContract)

    @Test
    fun shouldRenderLoadingState() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.timer(1, TimeUnit.SECONDS).map { emptyList<User>() })
        presenter.start()
        verify(setUpContract).render(SetupViewState(true, emptyList()))
    }

    @Test
    fun shouldRenderLoadedState() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(listOf(User())))
        presenter.start()
        verify(setUpContract).render(SetupViewState(true, emptyList()))
        verify(setUpContract).render(SetupViewState(false, listOf(SelectableUser(""))))
    }

}