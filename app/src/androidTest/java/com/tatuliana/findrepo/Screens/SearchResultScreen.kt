package com.tatuliana.findrepo.Screens

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.UiCollection
import android.support.test.uiautomator.UiObject
import android.support.test.uiautomator.UiSelector
import com.tatuliana.findrepo.R
import junit.framework.Assert
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything

class SearchResultScreen: BaseScreen() {
    private val searchResultList = UiCollection(UiSelector().resourceId("android:id/content"))
    private val searchListItem = UiSelector().resourceId("com.tatuliana.findrepo:id/linearLayout")
    private val repoList = onData(anything()).inAdapterView(withId(R.id.repoListView))
    private val snackbarRepo = onView(allOf(withId(R.id.snackbar_text), withText("REPO not found!!! GO BACK and try again!!!")))
    private val snackbarUser = onView(allOf(withId(R.id.snackbar_text), withText("USER not found!!! GO BACK and try again!!!")))

    init {
        Assert.assertTrue("SearchResultScreen is not presented", searchResultList.waitForExists(5000))
    }

    fun repoItemByIndex(index: Int) : RepoItem {
        return  RepoItem(searchResultList.getChildByInstance(searchListItem, index))
    }

    fun clickOnItem(index: Int): GitHubScreen {
        repoList.atPosition(index).perform(click())
        return GitHubScreen()
    }

    fun checkSnackBarRepo() {
        snackbarRepo.check(matches(isDisplayed()))
    }

    fun checkSnackBarUser() {
        snackbarUser.check(matches(isDisplayed()))
    }
}

class RepoItem(itemObject: UiObject) {
    val nameSelector = UiSelector().resourceId("com.tatuliana.findrepo:id/repoTextView")
    val repoName = itemObject.getChild(nameSelector).text
}