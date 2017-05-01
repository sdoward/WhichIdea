package com.sdoward.preference.preference

import android.util.Log
import com.sdoward.preference.data.Repository
import com.sdoward.preference.requireBetween

class PreferenceMaster(private val preferenceContract: PreferenceContract,
                       private val repository: Repository,
                       private val names: MutableList<Pair<String, Int>>) {

    companion object {
        val NOT_SET = -1
        val HIGHER_VALUE = 10
        val LOWER_VALUE = 0
    }

    private var pointer = 0

    fun reset() {
        preferenceContract.showStartInstructions()
    }

    fun start() {
        preferenceContract.showPreferenceInput(names[pointer].first)
    }

    fun setPreference(preference: Int) {
        requireBetween(preference, LOWER_VALUE, HIGHER_VALUE) { getMessage("position: $pointer") }
        names.set(pointer, names[pointer].copy(second = preference))
        pointer++
        if (pointer >= names.size) {
            showResult()
        } else {
            preferenceContract.showPreferenceInput(names[pointer].first)
        }
    }

    private fun showResult() {
        names.fold("") { original, next ->
            original.plus(next.first).plus(" : ").plus(next.second).plus("\n")
        }.let { preferenceContract.showResult(it) }
    }

    private fun getMessage(number: String) = "$number preference isn't between ${LOWER_VALUE} and ${HIGHER_VALUE}"

}


interface PreferenceContract {

    fun showResult(result: String)

    fun showStartInstructions()

    fun showPreferenceInput(name: String)

}
