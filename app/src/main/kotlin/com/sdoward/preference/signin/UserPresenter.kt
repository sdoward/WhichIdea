package com.sdoward.preference.signin

import com.sdoward.preference.data.Repository
import io.reactivex.rxkotlin.subscribeBy

class UserPresenter(private val repository: Repository) {

    fun acceptUser(user: User) {
        repository.hasUser(user.uid)
                .filter { it }
                .subscribeBy(
                        onComplete = {repository.saveUser(user)}
                )
    }

}