package ro.pub.cs.systems.eim.calculatorwebservice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
) {
    var operator1 by remember { mutableStateOf("") }
    var operator2 by remember { mutableStateOf("") }
    var selectedOperation by remember { mutableStateOf("plus") }
    var selectedMethod by remember { mutableIntStateOf(0) }
    var expandedOperation by remember { mutableStateOf(false) }
    var expandedMethod by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text("Operator 1") },
            value = operator1,
            onValueChange = { operator1 = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Operator 2") },
            value = operator2,
            onValueChange = { operator2 = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedOperation,
            onExpandedChange = { expandedOperation = !expandedOperation }
        ) {
            TextField(
                value = selectedOperation,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedOperation) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedOperation,
                onDismissRequest = { expandedOperation = false }
            ) {
                listOf("plus", "minus", "times", "divide").forEach { operation ->
                    DropdownMenuItem(
                        text = { Text(operation) },
                        onClick = {
                            selectedOperation = operation
                            expandedOperation = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedMethod,
            onExpandedChange = { expandedMethod = !expandedMethod }
        ) {
            TextField(
                value = if (selectedMethod == 0) "GET" else "POST",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMethod) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedMethod,
                onDismissRequest = { expandedMethod = false }
            ) {
                listOf("GET", "POST").forEach { method ->
                    DropdownMenuItem(
                        text = { Text(method) },
                        onClick = {
                            selectedMethod = if (method == "GET") 0 else 1
                            expandedMethod = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.calculate(operator1, operator2, selectedOperation, selectedMethod)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = viewModel.result.value ?: "No result",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}