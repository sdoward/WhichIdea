package com.sdoward.preference.preference

import com.sdoward.preference.requireBetween

class PreferenceMaster(private val preferenceContract: PreferenceContract,
                       private val repository: Repository) {

    companion object {
        val NOT_SET = -1
        val HIGHER_VALUE = 10
        val LOWER_VALUE = 0
    }

    private var firstPreference: Int = NOT_SET
    private var secondPreference: Int = NOT_SET

    fun reset() {
        firstPreference = NOT_SET
        secondPreference = NOT_SET
        preferenceContract.showStartInstructions()
    }

    fun start() {
        firstPreference = NOT_SET
        secondPreference = NOT_SET
        preferenceContract.showFirstPreference()
    }

    fun setFirstPreference(firstPreference: Int) {
        requireBetween(firstPreference, LOWER_VALUE, HIGHER_VALUE) { getMessage("first") }
        this.firstPreference = firstPreference
        preferenceContract.showSecondPreference()
    }

    fun setSecondPreference(secondPreference: Int) {
        requireBetween(secondPreference, LOWER_VALUE, HIGHER_VALUE) { getMessage("second") }
        this.secondPreference = secondPreference
        require(firstPreference != NOT_SET) { getMessage("first") }
        repository.saveSession(Session(firstPreference, secondPreference))
        preferenceContract.showResult("First person: $firstPreference Second person: $secondPreference")
    }

    private fun getMessage(number: String) = "$number preference isn't between ${LOWER_VALUE} and ${HIGHER_VALUE}"

}


interface PreferenceContract {


    fun showFirstPreference()


    fun showSecondPreference()


    fun showResult(result: String)

    fun showStartInstructions()

}
