package com.aliozdemir.kotlindersleri.nesne_tabanli_programlama

// Function to convert kilometers to miles
fun convertKmToMiles(km: Double): Double {
    val miles = km * 0.621
    return miles
}

// Function to calculate the area of a rectangle
fun calculateRectangleArea(length: Double, width: Double) {
    val area = length * width
    println("The area of the rectangle is $area square units.")
}

// Function to calculate factorial of a number
fun calculateFactorial(number: Int): Int {
    if (number == 0 || number == 1) {
        return 1
    }
    var result = 1
    for (i in 2..number) {
        result *= i
    }
    return result
}

// Function to count occurrences of 'e' in a word
fun countLetterE(word: String) {
    val eCount = word.count { it == 'e' }
    println("The word '$word' contains $eCount 'e' letters.")
}

// Function to calculate the sum of interior angles based on the number of sides
fun calculateInteriorAnglesSum(numSides: Int): Int {
    val anglesSum = (numSides - 2) * 180
    return anglesSum
}

// Function to calculate salary based on days worked
fun calculateSalary(dayCount: Int): Double {
    val regularHoursPerDay = 8
    val regularHourlyRate = 40.0
    val overtimeHourlyRate = 80.0
    val maxRegularHours = 150

    val totalWorkHours = dayCount * regularHoursPerDay

    val regularPay = regularHoursPerDay * regularHourlyRate * dayCount

    val totalSalary = if (totalWorkHours > maxRegularHours) {
        val totalOvertimeHours = totalWorkHours - maxRegularHours
        maxRegularHours * regularHourlyRate + totalOvertimeHours * overtimeHourlyRate
    } else {
        regularPay
    }

    return totalSalary
}

// Function to calculate parking fee based on hours
fun calculateParkingFee(hours: Int): Int {
    val baseRate = 50
    val additionalRate = 10

    val totalFee = baseRate + ((hours - 1) * additionalRate)
    return totalFee
}


fun main() {
    // fun 1: Convert Kilometers to Miles
    val kilometers = 10.0
    val miles = convertKmToMiles(kilometers)
    println("$kilometers kilometers is equal to $miles miles.")

    // fun 2: Calculate Rectangle Area
    val length = 5.0
    val width = 8.0
    calculateRectangleArea(length, width)

    // fun 3: Calculate Factorial
    val factorialNumber = 5
    val factorialResult = calculateFactorial(factorialNumber)
    println("The factorial of $factorialNumber is $factorialResult.")

    // fun 4: Count 'e' in a Word
    val word = "example"
    countLetterE(word)

    // fun 5: Calculate Interior Angles Sum
    val sides = 4
    val anglesSum = calculateInteriorAnglesSum(sides)
    println("The sum of interior angles in a $sides-sided polygon is $anglesSum degrees.")

    // fun 6: Calculate Salary based on Days
    val daysWorked = 19
    val salary = calculateSalary(daysWorked)
    println("The salary for working $daysWorked days is $salary dollars.")

    // fun 7: Calculate Parking Fee
    val parkingHours = 3
    val parkingFee = calculateParkingFee(parkingHours)
    println("The parking fee for $parkingHours hours is $parkingFee dollars.")
}