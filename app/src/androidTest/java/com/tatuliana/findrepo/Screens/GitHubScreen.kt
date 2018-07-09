package com.tatuliana.findrepo.Screens

import android.support.test.uiautomator.UiSelector
import org.junit.Assert

class GitHubScreen: BaseScreen() {
    private val gitHubScreen = uiDevice.findObject(UiSelector().resourceId("com.android.chrome:id/coordinator"))

    init {
        Assert.assertTrue("GitHubScreen is not presented", gitHubScreen.waitForExists(5000))
    }

    val urlBar = uiDevice.findObject(UiSelector().resourceId("com.android.chrome:id/url_bar"))
    val actualUrl get() = urlBar.text
}