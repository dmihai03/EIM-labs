package ro.pub.cs.systems.eim.calculatorwebservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ro.pub.cs.systems.eim.calculatorwebservice.ui.theme.CalculatorWebServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel: CalculatorViewModel by viewModels ()

        setContent {
            CalculatorWebServiceTheme {
                CalculatorScreen(
                    viewModel = calculatorViewModel,
                )
            }
        }
    }
}
