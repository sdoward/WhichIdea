package com.sdoward.preference.signin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.sdoward.preference.preference.MainActivity
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.content.Intent
import android.widget.Toast
import com.firebase.ui.auth.ResultCodes


class SigninActivity : AppCompatActivity() {

    companion object {

        val RESULT_CODE = 123

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            MainActivity.start(this)
        } else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RESULT_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == ResultCodes.OK) {
                MainActivity.start(this)
                finish()
                return
            } else {
                if (response == null) {
                    finish()
                    return
                }
                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "No network, please try later", Toast.LENGTH_LONG).show()
                    finish()
                    return
                }
                if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, "Unable to sign in, please try later", Toast.LENGTH_LONG).show()
                    finish()
                    return
                }
            }
        }
    }
}
