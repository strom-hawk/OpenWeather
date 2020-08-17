package com.demoapps.openweather

import android.os.SystemClock
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demoapps.openweather.util.AssetReaderUtil
import com.demoapps.openweather.view.HomeScreen
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    private val mockWebServer = MockWebServer()
    private var SYSTEM_CLOCK_SLEEP_TIME = 2000L

    @get:Rule
    var activityTestRule = ActivityTestRule(HomeScreen::class.java, false, true)

    @Before
    fun setup() {
        /*PLEASE TURN ON THE LOCATION )AND TURN OFF THE MOBILE DATA OR WIFI BEFORE RUNNING THE TEST*/
        mockWebServer.start()
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpClient()
            )
        )
    }

    @Test
    @Ignore
    fun testUIInitalization() {
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)
        Espresso.onView(ViewMatchers.withId(R.id.positiveButton)).perform(ViewActions.click())
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)
        Espresso.onView(ViewMatchers.withId(R.id.tvLocation))
            .check(matches(ViewMatchers.withText("City")))
        Espresso.onView(ViewMatchers.withId(R.id.tvDateTime))
            .check(matches(ViewMatchers.withText("Time and date will appear here")))
        Espresso.onView(ViewMatchers.withId(R.id.tvCurrentTemperature))
            .check(matches(ViewMatchers.withText("0°C")))
        Espresso.onView(ViewMatchers.withId(R.id.tvWeaterDescription))
            .check(matches(ViewMatchers.withText("Current weather description will appear here")))
        Espresso.onView(ViewMatchers.withId(R.id.tvSunrise))
            .check(matches(ViewMatchers.withText("Sunrise")))
        Espresso.onView(ViewMatchers.withId(R.id.tvWind))
            .check(matches(ViewMatchers.withText("Wind")))
        Espresso.onView(ViewMatchers.withId(R.id.tvFeelsLike))
            .check(matches(ViewMatchers.withText("Feels Like")))
    }

    @Test
    fun testSuccessReponseWithActualAPI() {
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    AssetReaderUtil.asset(
                        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
                        "successResponse"
                    )
                )
        )
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)

        Espresso.onView(ViewMatchers.withId(R.id.etCity)).perform(clearText())
        Espresso.onView(ViewMatchers.withId(R.id.etCity))
            .perform(ViewActions.typeText("Bangalore"), closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.getWeaterButton)).perform(ViewActions.click())
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)

        Espresso.onView(ViewMatchers.withId(R.id.tvCurrentTemperature))
            .check(ViewAssertions.matches(not(ViewMatchers.withText("0°C"))))
        SystemClock.sleep(SYSTEM_CLOCK_SLEEP_TIME)

    }


    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}