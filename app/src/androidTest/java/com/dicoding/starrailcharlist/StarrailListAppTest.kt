package com.dicoding.starrailcharlist

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme
import com.dicoding.starrailcharlist.ui.navigation.Screen
import com.dicoding.starrailcharlist.model.StarrailData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StarrailListAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            StarrailListTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                StarrailListApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @ExperimentalTestApi
    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("starrailList").performScrollToIndex(40)
        composeTestRule.onNodeWithText(StarrailData.starrail[40].character).performClick()
        navController.assertCurrentRouteName(Screen.DetailStarrail.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.icon_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun search_char_does_not_exist_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_Character).performClick().performTextInput("Anime Test")
        composeTestRule.onNodeWithTag("starrailItem").assertDoesNotExist()
    }

    @Test
    fun search_char_exist_and_go_to_the_detail_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_Character).performClick().performTextInput(StarrailData.starrail[0].character)
        composeTestRule.onNodeWithTag("starrailItem").performClick()
        navController.assertCurrentRouteName(Screen.DetailStarrail.route)
        composeTestRule.onNodeWithText(StarrailData.starrail[0].character).assertIsDisplayed()
        composeTestRule.onNodeWithText(StarrailData.starrail[0].type).assertIsDisplayed()
        composeTestRule.onNodeWithText(StarrailData.starrail[0].path).assertIsDisplayed()
        composeTestRule.onNodeWithText(StarrailData.starrail[0].rare).assertIsDisplayed()
        composeTestRule.onNodeWithText(StarrailData.starrail[0].synopsis).assertIsDisplayed()
    }

    @Test
    fun search_char_exist_and_go_to_the_wrong_detail_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_Character).performClick().performTextInput(StarrailData.starrail[0].character)
        composeTestRule.onNodeWithTag("starrailItem").performClick()
        navController.assertCurrentRouteName(Screen.DetailStarrail.route)
        composeTestRule.onNodeWithText(StarrailData.starrail[1].character).assertDoesNotExist()
        composeTestRule.onNodeWithText(StarrailData.starrail[1].type).assertDoesNotExist()
        composeTestRule.onNodeWithText(StarrailData.starrail[1].path).assertDoesNotExist()
        composeTestRule.onNodeWithText(StarrailData.starrail[1].rare).assertDoesNotExist()
        composeTestRule.onNodeWithText(StarrailData.starrail[1].synopsis).assertDoesNotExist()
    }
}
