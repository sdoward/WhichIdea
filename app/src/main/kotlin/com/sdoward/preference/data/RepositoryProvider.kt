package com.sdoward.preference.data

import com.google.firebase.database.FirebaseDatabase

object RepositoryProvider {

    private val repository by lazy {
        FirebaseRepository(FirebaseDatabase.getInstance().reference)
    }

    fun get(): Repository {
        return repository
    }

}