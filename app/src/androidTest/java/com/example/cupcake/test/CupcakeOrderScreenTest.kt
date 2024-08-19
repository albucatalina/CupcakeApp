import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.test.onNodeWithStringId
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.R
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent(){
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavours)
        }

        //check that all flavours are displayed on the screen
        flavours.forEach{flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }

        //check the subtotal price
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        //check that the next button is not clickable
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_nextButtonEnabled(){
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavours)
        }

        composeTestRule.onNodeWithText("Vanilla").performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}