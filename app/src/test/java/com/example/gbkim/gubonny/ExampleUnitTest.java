package com.example.gbkim.gubonny;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String dateTime = "1234^12342";

        String[] recordDateTime = splitStringToArray(dateTime);

        for (int i = 0; i < recordDateTime.length; i++) {
            System.out.println(recordDateTime[i]);
        }
    }

    private String[] splitStringToArray(String string) {
        if (string != null) {
            String[] arr;

            if (string.contains("^")) {
                arr = string.split("\\^");

            } else {
                arr = new String[1];

                arr[0] = string;
            }

            return arr;
        }

        return null;
    }
}