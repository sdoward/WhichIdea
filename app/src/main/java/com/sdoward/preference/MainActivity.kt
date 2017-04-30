package com.sdoward.preference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), PreferenceContract {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    private val preferenceMaster = PreferenceMaster(this, FirebaseSessionRepository(databaseReference))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startView.setOnClickListener { preferenceMaster.start() }
        resultView.setOnClickListener { preferenceMaster.reset() }
        firstPreferenceButton.setOnClickListener {
            preferenceMaster.setFirstPreference(firstPreferenceNumberPicker.value)
        }
        secondPreferenceButton.setOnClickListener {
            preferenceMaster.setSecondPreference(secondPreferenceNumberPicker.value)
        }
        listOf(firstPreferenceNumberPicker, secondPreferenceNumberPicker)
                .forEach {
                    it.minValue = 0
                    it.maxValue = 10
                }
    }

    override fun showFirstPreference() {
        startView.visibility = GONE
        secondPreferenceView.visibility = GONE
        firstPreferenceView.visibility = VISIBLE
        resultView.visibility = GONE
    }

    override fun showSecondPreference() {
        startView.visibility = GONE
        secondPreferenceView.visibility = VISIBLE
        firstPreferenceView.visibility = GONE
        resultView.visibility = GONE
    }

    override fun showResult(result: String) {
        startView.visibility = GONE
        secondPreferenceView.visibility = GONE
        firstPreferenceView.visibility = GONE
        resultView.visibility = VISIBLE
        resultTextView.text = result
    }

    override fun showStartInstructions() {
        startView.visibility = VISIBLE
        secondPreferenceView.visibility = GONE
        firstPreferenceView.visibility = GONE
        resultView.visibility = GONE
    }

}
