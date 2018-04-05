package com.tatuliana.findrepo


import android.content.res.Resources
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tatuliana.findrepo.R.id.*
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith




    @RunWith(AndroidJUnit4::class)
    class EspressoFindRepoTest {

        @get:Rule
//        @JvmField
        val testRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, true)
        protected lateinit var resources: Resources

        @Before
        fun setUp() {
            resources = InstrumentationRegistry.getTargetContext().getResources()
        }
        @Test
        fun userCanSearchForDefaultValueRepo() {
        onView(withId(searchButton))
                .perform(click())
            Thread.sleep(5000)
            onData(anything()).inAdapterView(withId(R.id.repoListView)).atPosition(0).perform(click())
            Thread.sleep(5000)
        }

        @Test
        fun userCanSearchForRepo() {
            onView(withId(searchEditText))
                    .perform(typeText("DoctorOnDemand"))
            Thread.sleep(5000)
            onView(withId(searchButton))
                    .perform(click())
            Thread.sleep(5000)
            onData(anything()).inAdapterView(withId(R.id.repoListView)).atPosition(0).perform(click())
            Thread.sleep(5000)
        }

        @Test
        fun userCanSearchForValidGitUser() {
            onView(withId(userRepoEditText))
                    .perform(typeText("tatuliana"))
            Espresso.closeSoftKeyboard()
            Thread.sleep(5000)
            onView(withId(userRepoButton))
                    .perform(click())
            Thread.sleep(5000)
            onData(anything()).inAdapterView(withId(R.id.repoListView)).atPosition(0).perform(click())
            Thread.sleep(5000)
        }

        @Test
        fun userCanSearchForNotExistingGitUserAndGetsErrorMessage() {
            onView(withId(userRepoEditText))
                .perform(typeText("tatulianasasasa"))
            Espresso.closeSoftKeyboard()
            Thread.sleep(5000)
            onView(withId(userRepoButton))
//                    .perform(scrollTo())
                         .perform(click())
//            Thread.sleep(5000)
//            onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("USER not found!!! GO BACK and try again!!!")))
//                    .check(matches(withEffectiveVisibility(
//                            ViewMatchers.Visibility.VISIBLE)))
//            onView(withId(snackbar_text, "USER not found!!! GO BACK and try again!!!"))
//                    .check(matches(isDisplayed()));
//            onView(withContentDescription("USER not found!!! GO BACK and try again!!!"))
//                    .check(matches(isDisplayed()))
        }

}
