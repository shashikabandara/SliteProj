package com.example.sliteproj;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private AddRecordActivity addRecordActivity;

    @Before //execute before each test case you write and preapre enviroment for each test cases
    public void setup(){//settle the enviroment for testing
        addRecordActivity = new AddRecordActivity();
    }

    @Test//used for each an every test case we write inside the class

    public void testsubstractionCalculation(){
        int result = addRecordActivity.substractionCalculation(1000,800);
        assertEquals(200,result);

    }

}