package com.aliozdemir.simplecalculator

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.aliozdemir.simplecalculator.databinding.FragmentCalculatorBinding
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private var currentInput: StringBuilder = StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )

        // Set up button click listeners
        setUpButtonClickListeners()
    }

    // Function to set up button click listeners
    private fun setUpButtonClickListeners() {
        with(binding) {
            // Number buttons
            btn0.setOnClickListener { onNumberButtonClick("0") }
            btn1.setOnClickListener { onNumberButtonClick("1") }
            btn2.setOnClickListener { onNumberButtonClick("2") }
            btn3.setOnClickListener { onNumberButtonClick("3") }
            btn4.setOnClickListener { onNumberButtonClick("4") }
            btn5.setOnClickListener { onNumberButtonClick("5") }
            btn6.setOnClickListener { onNumberButtonClick("6") }
            btn7.setOnClickListener { onNumberButtonClick("7") }
            btn8.setOnClickListener { onNumberButtonClick("8") }
            btn9.setOnClickListener { onNumberButtonClick("9") }

            // Operator buttons
            btnAdd.setOnClickListener { onOperatorButtonClick("+") }
            btnSubtract.setOnClickListener { onOperatorButtonClick("-") }
            btnMultiply.setOnClickListener { onOperatorButtonClick("×") }
            btnDivide.setOnClickListener { onOperatorButtonClick("/") }

            // Other buttons
            btnEquals.setOnClickListener { onEqualsButtonClick() }
            btnClear.setOnClickListener { onClearButtonClick() }
            btnRemainder.setOnClickListener { onRemainderButtonClick() }
            btnDecimal.setOnClickListener { onDecimalButtonClick() }
        }


    }

    // Function to handle number button click
    private fun onNumberButtonClick(number: String) {
        lastNumeric = true
        currentInput.append(number)
        updateDisplayText(currentInput.toString())
    }

    // Function to handle operator button click
    private fun onOperatorButtonClick(operator: String) {
        if (lastNumeric) {
            lastNumeric = false
            lastDot = false
            currentInput.append(operator)
            updateDisplayText(currentInput.toString())
        }
    }

    // Function to handle equals button click
    private fun onEqualsButtonClick() {
        if (lastNumeric) {
            try {
                val result = evaluateExpression()
                updateDisplayText(result.toString())
                currentInput = StringBuilder()
                lastNumeric = false
                lastDot = false
            } catch (e: ArithmeticException) {
                handleCalculationError()
            }
        }
    }

    // Function to handle clear button click
    private fun onClearButtonClick() {
        currentInput = StringBuilder()
        lastNumeric = false
        lastDot = false
        updateDisplayText("0")
    }

    // Function to handle remainder button click
    private fun onRemainderButtonClick() {
        if (lastNumeric) {
            lastNumeric = false
            lastDot = false
            currentInput.append("%")
            updateDisplayText(currentInput.toString())
        }
    }

    // Function to handle decimal button click
    private fun onDecimalButtonClick() {
        if (lastNumeric && !lastDot) {
            lastDot = true
            currentInput.append(".")
            updateDisplayText(currentInput.toString())
        }
    }

    // Function to update the display text
    private fun updateDisplayText(value: String) {
        binding.textView.text = value
    }

    // Function to evaluate the expression
    private fun evaluateExpression(): Any {
        val expression = currentInput.toString()
        val parts = expression.split("[+\\-×/%]".toRegex())

        if (parts.size == 2) {
            val num1 = parts[0].toDouble()
            val num2 = parts[1].toDouble()

            return when {
                expression.contains('+') -> (num1 + num2).removeDecimalIfInteger()
                expression.contains('-') -> (num1 - num2).removeDecimalIfInteger()
                expression.contains('×') -> (num1 * num2).removeDecimalIfInteger()
                expression.contains('/') -> (num1 / num2).removeDecimalIfInteger()
                expression.contains('%') -> (num1 % num2).removeDecimalIfInteger()
                else -> throw ArithmeticException("Invalid operation")
            }
        } else {
            throw ArithmeticException("Invalid expression")
        }
    }

    // Extension function to remove decimal if the result is an integer
    private fun Double.removeDecimalIfInteger(): String {
        return if (this.isInt()) {
            this.toInt().toString()
        } else {
            this.toString()
        }
    }

    // Extension function to check if a double is an integer
    private fun Double.isInt(): Boolean {
        return this % 1 == 0.0
    }

    // Function to handle calculation errors
    private fun handleCalculationError() {
        Snackbar.make(binding.textView, "Enter two numbers only!", Snackbar.LENGTH_SHORT).show()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showExitConfirmationDialog()
        }
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                requireActivity().finish()
            }
            .setNegativeButton("No") { _, _ ->
                // Continue with the app, do nothing on pressing "No"
            }

        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}