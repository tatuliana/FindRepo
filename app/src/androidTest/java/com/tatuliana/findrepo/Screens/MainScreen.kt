package com.tatuliana.findrepo.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.uiautomator.UiSelector
import com.tatuliana.findrepo.R
import junit.framework.Assert

class MainScreen: BaseScreen() {
    private val mainScreen = uiDevice.findObject(UiSelector().resourceId("android:id/content"))
    private val searchRepoField = onView(withId(R.id.searchEditText))
    private val searchRepoButton = onView(withId(R.id.searchButton))
    private val searchUserField = onView(withId(R.id.userRepoEditText))
    private val searchUserButton = onView(withId(R.id.userRepoButton))

    init {
        Assert.assertTrue("MainScreen is not presented", mainScreen.waitForExists(5000))
    }

    fun enterRepoName(repo: String) {
        searchRepoField.perform(click())
        searchRepoField.perform(typeText(repo))
    }

    fun clickOnSearchRepoButton() : SearchResultScreen {
        searchRepoButton.perform(click())
        return SearchResultScreen()
    }

    fun enterUserName(user: String) {
        searchUserField.perform(click())
        searchUserField.perform(typeText(user))
    }

    fun clickOnSearchUserButton() : SearchResultScreen {
        searchUserButton.perform(click())
        return SearchResultScreen()
    }

    fun clearRepoSearch(){
        searchRepoField.perform(ViewActions.clearText())
    }

    fun clearUserSearch(){
        searchUserField.perform(ViewActions.clearText())
    }

    fun checkRepoHint(hint: String) {
        searchRepoField.check(matches(withHint(hint)))
    }

    fun checkUserHint(hint: String) {
        searchUserField.check(matches(withHint(hint)))
    }
}