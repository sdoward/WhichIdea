package com.sdoward.preference.data

import com.google.firebase.database.*
import com.sdoward.preference.getSessionsReference
import com.sdoward.preference.getUsersReference
import com.sdoward.preference.preference.Session
import com.sdoward.preference.signin.User
import io.reactivex.Single

class FirebaseRepository(private val database: DatabaseReference) : Repository {

    override fun saveSession(session: Session) {
        database.getSessionsReference().push().setValue(session)
    }

    override fun saveUser(user: User) {
        val user = mapOf(Pair(user.uid, user))
        database.getUsersReference().setValue(user)
    }

    override fun getUsers() : Single<List<User>> {
        return Single.create { emitter ->
            database.getUsersReference().addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    emitter.onError(Throwable(databaseError.message))
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children
                            .map { it.getValue(User::class.java) }
                            .let { emitter.onSuccess(it) }
                }

            })
        }
    }

    override fun hasUser(uid: String): Single<Boolean> {
        return Single.create<Boolean> {
            database.getUsersReference().addListenerForSingleValueEvent(object : ValueEventListener {

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

    fun getUsers(): Single<List<User>>
}