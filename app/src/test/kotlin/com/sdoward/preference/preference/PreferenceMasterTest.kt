package com.sdoward.preference.preference

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sdoward.preference.preference.PreferenceContract
import com.sdoward.preference.preference.PreferenceMaster
import com.sdoward.preference.preference.Session
import com.sdoward.preference.preference.Repository
import org.junit.Test

class PreferenceMasterTest {

    val preferenceContract: PreferenceContract = mock()
    val repository: Repository = mock()
    val preferenceMaster = PreferenceMaster(preferenceContract, repository)

    @Test
    fun shouldShowFirstInput() {
        preferenceMaster.start()
        verify(preferenceContract).showFirstPreference()
    }

    @Test
    fun shouldShowSecondInput() {
        preferenceMaster.setFirstPreference(0)
        verify(preferenceContract).showSecondPreference()
    }

    @Test
    fun shouldShowResult() {
        preferenceMaster.setFirstPreference(0)
        preferenceMaster.setSecondPreference(1)
        verify(preferenceContract).showResult("First person: 0 Second person: 1")
    }

    @Test
    fun shouldShowInstructions() {
        preferenceMaster.reset()
        verify(preferenceContract).showStartInstructions()
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenFirstBelow0() {
        preferenceMaster.setFirstPreference(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenFirstAbove10() {
        preferenceMaster.setFirstPreference(11)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenSecondBelow0() {
        preferenceMaster.setSecondPreference(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenSecondAbove10() {
        preferenceMaster.setSecondPreference(11)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenFirstNotSet() {
        preferenceMaster.setSecondPreference(1)
    }

    @Test
    fun shouldSaveSession() {
        preferenceMaster.setFirstPreference(0)
        preferenceMaster.setSecondPreference(1)
        verify(repository).saveSession(Session(0, 1))
    }


}