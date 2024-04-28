package Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.espresso.MainActivity;
import com.example.espresso.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TC_E_01 {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void clickButtonHome() {
        onView(withId(R.id.navigation_home)).perform(waitFor(2000));
        onView(withId(R.id.navigation_home)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonDashboard() {
        onView(withId(R.id.navigation_dashboard)).perform(waitFor(2000));
        onView(withId(R.id.navigation_dashboard)).perform(waitFor(2000),click());
        onView(withId(R.id.text_dashboard)).check(matches(withText("This is dashboard fragment")));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.text_home)).perform(waitFor(5000)).check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonNotifications() {
        onView(withId(R.id.navigation_notifications)).perform(waitFor(2000));
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.text_notifications)).check(matches(withText("This is notifications fragment")));
    }

public static ViewAction waitFor(final long millis) {
return new ViewAction() {
    @Override
    public Matcher<View> getConstraints() {
        return isDisplayed();
    }

    @Override
    public String getDescription() {
        return "wait for " + millis + " milliseconds";
    }

    @Override
    public void perform(UiController uiController, View view) {
        uiController.loopMainThreadForAtLeast(millis);
    }
};
}
}
