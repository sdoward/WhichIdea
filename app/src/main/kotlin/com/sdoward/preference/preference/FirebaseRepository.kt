package com.sdoward.preference.preference

import android.util.Log
import com.google.firebase.database.*
import com.sdoward.preference.signin.User
import io.reactivex.Single


class FirebaseRepository(private val database: DatabaseReference) : Repository {

    override fun saveSession(session: Session) {
        database.child("sessions").push().setValue(session)
    }

    override fun saveUser(user: User) {
        val user = mapOf(Pair(user.uid, user))
        database.child("users").setValue(user)
    }

    override fun hasUser(uid: String): Single<Boolean> {
        return Single.create<Boolean> {
            database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(databaseError: DatabaseError) {
                    it.onError(Throwable(databaseError.message))
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    it.onSuccess(snapshot.hasChild(uid))
                }

            })
        }

    }

}

interface Repository {

    fun saveSession(session: Session)

    fun saveUser(user: User)

    fun hasUser(uid: String): Single<Boolean>
}