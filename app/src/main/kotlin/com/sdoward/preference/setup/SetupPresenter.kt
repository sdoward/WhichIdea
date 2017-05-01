package com.sdoward.preference.setup

import com.sdoward.preference.preference.Repository
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy

class SetupPresenter(private val repository: Repository, private val setUpContract: SetUpContract) {

    private var disposable = Disposables.empty()

    fun start() {
        setUpContract.render(SetupViewState(true, emptyList()))
        disposable = repository.getUsers()
                .map { SetupViewState(false, it) }
                .subscribeBy(
                        onSuccess = { setUpContract.render(it) }
                )
    }

    fun stop() {
        disposable.dispose()
    }

}

interface SetUpContract {

    fun render(setupViewState: SetupViewState)

}