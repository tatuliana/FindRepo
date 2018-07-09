package com.tatuliana.findrepo.Screens

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice


open class BaseScreen {
    protected val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
}
