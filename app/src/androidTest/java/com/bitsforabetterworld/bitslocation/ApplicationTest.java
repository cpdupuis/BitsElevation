package com.bitsforabetterworld.bitslocation;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testLatitude()
    {
        assertEquals("90\u00b0 0\' 0.000\" N", FormatUtils.formatLatitude(90.0));
        assertEquals("90\u00b0 0\' 0.000\" S", FormatUtils.formatLatitude(-90.0));
        assertEquals("45\u00b0 30\' 0.000\" S", FormatUtils.formatLatitude(-45.5));
        assertEquals("32\u00b0 7\' 30.000\" N", FormatUtils.formatLatitude(32.125));
    }

    public void testLongitude()
    {
        assertEquals("90\u00b0 0\' 0.000\" E", FormatUtils.formatLongitude(90.0));
        assertEquals("90\u00b0 0\' 0.000\" W", FormatUtils.formatLongitude(-90.0));
        assertEquals("45\u00b0 30\' 0.000\" W", FormatUtils.formatLongitude(-45.5));
        assertEquals("32\u00b0 7\' 30.000\" E", FormatUtils.formatLongitude(32.125));
    }
}