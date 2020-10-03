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

    private AddRecordActivity AddRecordActivity;

    @Before //execute before each test case you write and preapre enviroment for each test cases
    public void setup(){//settle the enviroment for testing
        AddRecordActivity = new AddRecordActivity();
    }

    @Test//used for each an every test case we write inside the class
//IT19058160  W M C S Bandara
    public void testsubstractionCalculation(){
        int result = AddRecordActivity.substractionCalculation(1000,800);
        assertEquals(200,result);

    }
    @Test
    //IT19058160  W M C S Bandara
    public void testsubstractionCalculation2(){
        int result = AddRecordActivity.substractionCalculation(1000,1000);
        assertEquals(0,result);

    }
    @Test
    //IT19058160  W M C S Bandara
    public void testsubstractionCalculation3(){
        int result = AddRecordActivity.substractionCalculation(800,1000);
        assertEquals(-200,result);

    }

}