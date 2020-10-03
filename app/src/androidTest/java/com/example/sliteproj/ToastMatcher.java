package com.example.sliteproj;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
//IT19058160  W M C S Bandara
public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    protected boolean matchesSafely(Root root) {

        int type = root.getWindowLayoutParams().get().type;
        if((type == WindowManager.LayoutParams.TYPE_TOAST)){
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();

            if (windowToken == appToken) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Toast Fail");

    }
}
