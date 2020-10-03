package com.example.sliteproj;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)

//IT19058160  W M C S Bandara
public class addDataTest {

    @Rule

    public ActivityTestRule<AddRecordActivity> room = new ActivityTestRule<>(AddRecordActivity.class);

    @Test
    public void addGuide(){
        onView(withText("Information Added Successfully!"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

}
