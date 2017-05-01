package com.sdoward.preference.preference

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sdoward.preference.preference.PreferenceContract
import com.sdoward.preference.preference.PreferenceMaster
import com.sdoward.preference.preference.Session
import com.sdoward.preference.data.Repository
import org.junit.Test

class PreferenceMasterTest {

    val preferenceContract: PreferenceContract = mock()
    val repository: Repository = mock()
    val preferenceMaster = PreferenceMaster(preferenceContract, repository,
            mutableListOf(Pair("Sam", -1), Pair("Michaela", -1)))

    @Test
    fun shouldShowFirstInput() {
        preferenceMaster.start()
        verify(preferenceContract).showPreferenceInput("Sam")
    }

    @Test
    fun shouldShowSecondInput() {
        preferenceMaster.setPreference(0)
        verify(preferenceContract).showPreferenceInput("Michaela")
    }

    @Test
    fun shouldShowResult() {
        preferenceMaster.setPreference(0)
        preferenceMaster.setPreference(1)
        verify(preferenceContract).showResult("Sam : 0\nMichaela : 1\n")
    }

    @Test
    fun shouldShowInstructions() {
        preferenceMaster.reset()
        verify(preferenceContract).showStartInstructions()
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenFirstBelow0() {
        preferenceMaster.setPreference(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenFirstAbove10() {
        preferenceMaster.setPreference(11)
    }

}