package com.sdoward.preference.preference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.firebase.database.FirebaseDatabase
import com.sdoward.preference.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), PreferenceContract {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    private val preferenceMaster = PreferenceMaster(this, FirebaseRepository(databaseReference),
            mutableListOf(Pair("Sam", -1), Pair("Michaela", -1)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startView.setOnClickListener { preferenceMaster.start() }
        resultView.setOnClickListener { preferenceMaster.reset() }
        submitButton.setOnClickListener {
            preferenceMaster.setPreference(numberPicker.value)
        }
        numberPicker.apply {
            minValue = 0
            maxValue = 10
        }
    }


    override fun showPreferenceInput(name: String) {
        startView.visibility = GONE
        resultView.visibility = GONE
        playerTextView.text = name
        numberPicker.value = 0
        preferenceView.visibility = VISIBLE
    }

    override fun showResult(result: String) {
        startView.visibility = GONE
        preferenceView.visibility = GONE
        resultView.visibility = VISIBLE
        resultTextView.text = result
    }

    override fun showStartInstructions() {
        startView.visibility = VISIBLE
        preferenceView.visibility = GONE
        resultView.visibility = GONE
    }

}
