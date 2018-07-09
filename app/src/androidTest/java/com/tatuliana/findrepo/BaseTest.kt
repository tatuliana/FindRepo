package com.tatuliana.findrepo

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import org.junit.Before


open class BaseTest {
    protected lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }
}