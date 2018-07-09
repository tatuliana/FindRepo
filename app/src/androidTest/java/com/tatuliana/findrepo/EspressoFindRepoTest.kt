package com.tatuliana.findrepo

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.pressBack
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tatuliana.findrepo.Screens.MainScreen
import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoFindRepoTest: BaseTest() {

    @get:Rule
    val testRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun searchForDefaultValueRepo() {
        val mainScreen = MainScreen()
        mainScreen.enterRepoName("")
        val searchResultScreen = mainScreen.clickOnSearchRepoButton()
        Thread.sleep(5000)
        val firstRepo = searchResultScreen.repoItemByIndex(0)
        val firstRepoName = firstRepo.repoName
        Assert.assertTrue("Repo name does not contain kotlin", firstRepoName.contains("kotlin"))
    }

    @Test
    fun searchForRepoAndCheckOnGitHub() {
        val mainScreen = MainScreen()
        mainScreen.enterRepoName("DoctorOnDemand")
        val searchResultScreen = mainScreen.clickOnSearchRepoButton()
        Thread.sleep(5000)
        val gitHubScreen = searchResultScreen.clickOnItem(0)
        Thread.sleep(5000)
        val urlBar = gitHubScreen.urlBar
        Assert.assertTrue("Url Bar is not presented", urlBar.waitForExists(5000))
        Assert.assertTrue("Repo name does not contain DoctorOnDemand", gitHubScreen.actualUrl.contains("DoctorOnDemand"))
    }

    @Test
    fun searchForValidGitUser() {
        val mainScreen = MainScreen()
        mainScreen.enterUserName("tatuliana")
        closeSoftKeyboard()
        val searchResultScreen = mainScreen.clickOnSearchUserButton()
        Thread.sleep(5000)
        val firstUser = searchResultScreen.repoItemByIndex(0)
        val firstUserName = firstUser.repoName
        Assert.assertTrue("Search result doesn't contain user name tatuliana", firstUserName.contains("tatuliana"))
    }

    @Test
    fun searchForNotExistingRepo() {
        val mainScreen = MainScreen()
        mainScreen.enterRepoName("kljxzdgnbmhjbvx")
        val searchResultScreen = mainScreen.clickOnSearchRepoButton()
        searchResultScreen.checkSnackBarRepo()
    }

    @Test
    fun searchForNotExistingUser() {
        val mainScreen = MainScreen()
        mainScreen.enterUserName("tatulianasasasadhfggsd")
        closeSoftKeyboard()
        val searchResultScreen = mainScreen.clickOnSearchUserButton()
        searchResultScreen.checkSnackBarUser()
    }

    @Test
    fun checkRepoHint() {
        val mainScreen = MainScreen()
        mainScreen.enterRepoName("DoctorOnDemand")
        val searchResultScreen = mainScreen.clickOnSearchRepoButton()
        Thread.sleep(5000)
        pressBack()
        mainScreen.clearRepoSearch()
        Thread.sleep(1000)
        mainScreen.checkRepoHint("Search for Repos")
    }

    @Test
    fun checkUserHint() {
        val mainScreen = MainScreen()
        mainScreen.enterUserName("tatuliana")
        closeSoftKeyboard()
        val searchResultScreen = mainScreen.clickOnSearchUserButton()
        Thread.sleep(5000)
        pressBack()
        mainScreen.clearUserSearch()
        Thread.sleep(1000)
        mainScreen.checkUserHint("View User's Repos")
    }
}
