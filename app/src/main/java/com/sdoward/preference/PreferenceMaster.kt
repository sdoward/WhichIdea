package com.sdoward.preference

class PreferenceMaster(private val preferenceContract: PreferenceContract) {

    private var firstPreference: Int = 0
    private var secondPreference: Int = 0

    fun reset() {
        firstPreference = 0
        secondPreference = 0
        preferenceContract.showStartInstructions()
    }

    fun start() {
        firstPreference = 0
        secondPreference = 0
        preferenceContract.showFirstPreference()
    }

    fun setFirstPreference(firstPereference: Int) {
        this.firstPreference = firstPereference
        preferenceContract.showSecondPreference()
    }

    fun setSecondPreference(secondPereference: Int) {
        this.secondPreference = secondPereference
        preferenceContract.showResult("First person: $firstPreference Second person: $secondPereference")
    }

}

interface PreferenceContract {


    fun showFirstPreference()


    fun showSecondPreference()


    fun showResult(result: String)

    fun showStartInstructions()

}
