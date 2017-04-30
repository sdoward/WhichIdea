package com.sdoward.preference

import com.google.firebase.database.DatabaseReference

class FirebaseSessionRepository(private val database: DatabaseReference): SessionRepository {

    override fun saveSession(session: Session) {
        database.push().setValue(session)
    }

}

interface SessionRepository {

    fun saveSession(session: Session)

}