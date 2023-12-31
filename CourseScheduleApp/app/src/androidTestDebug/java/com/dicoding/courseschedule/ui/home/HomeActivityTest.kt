package com.dicoding.courseschedule.ui.home

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.dicoding.courseschedule.R

class HomeActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun addCourseActivityIsDisplayed() {
        onView(withId(R.id.action_add)).perform(click())
        onView(withId(R.id.ed_course_name)).check(matches(isDisplayed()))
    }
}