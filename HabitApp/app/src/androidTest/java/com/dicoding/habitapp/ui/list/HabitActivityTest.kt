package com.dicoding.habitapp.ui.list

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.dicoding.habitapp.R
import org.junit.Rule
import org.junit.Test

//TODO 16 : Write UI test to validate when user tap Add Habit (+), the AddHabitActivity displayed
class HabitActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(HabitListActivity::class.java)

    @Test
    fun addHabitActivityIsDisplayed() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.sp_priority_level)).check(matches(isDisplayed()))
    }
}