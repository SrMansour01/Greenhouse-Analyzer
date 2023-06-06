import java.io.File

data class SensorData(val sensorId: Int, val value: Double)

class GreenhouseAnalyzer {

    private val sensorDataList: MutableList<SensorData> = mutableListOf()

    fun readDataFromFile(filePath: String) {
        val file = File(filePath)
        val lines = file.readLines()

        for (line in lines) {
            val values = line.split(",")
            val sensorId = values[0].toInt()
            val value = values[1].toDouble()
            val sensorData = SensorData(sensorId, value)
            sensorDataList.add(sensorData)
        }
    }

    fun calculateAverage(): Double {
        val sum = sensorDataList.sumOf { it.value }
        return sum / sensorDataList.size
    }

    fun findMaxValue(): Double {
        return sensorDataList.maxOf { it.value }
    }

    fun findMinValue(): Double {
        return sensorDataList.minOf { it.value }
    }

    fun identifyAndHandleOutliers() {
        val values = sensorDataList.map { it.value }.toDoubleArray()

        val q1 = calculatePercentile(values, 25)
        val q3 = calculatePercentile(values, 75)

        val iqr = q3 - q1
        val lowerLimit = q1 - 1.5 * iqr
        val upperLimit = q3 + 1.5 * iqr

        sensorDataList.removeAll { it.value < lowerLimit || it.value > upperLimit }
    }

    fun calculatePercentile(values: DoubleArray, percentile: Int): Double {
        val sortedValues = values.sorted()

        val index = (percentile / 100.0) * (sortedValues.size - 1)
        val lowerIndex = index.toInt()
        val upperIndex = lowerIndex + 1

        if (lowerIndex == upperIndex) {
            return sortedValues[lowerIndex]
        }

        val lowerValue = sortedValues[lowerIndex]
        val upperValue = sortedValues[upperIndex]

        return lowerValue + (index - lowerIndex) * (upperValue - lowerValue)
    }

    private fun calculateStandardDeviation(): Double {
        val average = calculateAverage()
        val sumOfSquares = sensorDataList.sumOf { (it.value - average) * (it.value - average) }
        val variance = sumOfSquares / sensorDataList.size
        return kotlin.math.sqrt(variance)
    }

    fun displayMenu() {
        println("----- Greenhouse Analyzer -----")
        println("1. Read data from CSV file")
        println("2. Calculate average")
        println("3. Find maximum value")
        println("4. Find minimum value")
        println("5. Identify and handle outliers")
        println("6 - exibir tudo")
        println("0. Exit")
        println("--------------------------------")

        print("Enter your choice: ")
        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {
                print("Enter the CSV file path: ")
                val filePath = readLine()
                if (filePath != null) {
                    readDataFromFile(filePath)
                    println("Data loaded successfully.")
                    print("deseja ver os arquivos s ou n")
                    var read = readLine().toString().uppercase()
                    if (read == "S"){
                        println(sensorDataList)
                    }

                } else {
                    println("Invalid file path.")
                }
            }
            2 -> {
                val average = calculateAverage()
                println("Average: $average")
            }
            3 -> {
                val maxValue = findMaxValue()
                println("Maximum value: $maxValue")
            }
            4 -> {
                val minValue = findMinValue()
                println("Minimum value: $minValue")
            }
            5 -> {
                identifyAndHandleOutliers()
                println("Outliers identified and handled.")
            }
            6 -> {
                val average = calculateAverage()
                println("Average: $average")
                val maxValue = findMaxValue()
                println("Maximum value: $maxValue")
                val minValue = findMinValue()
                println("Minimum value: $minValue")
            }
            0 -> {
                println("Exiting...")
                return
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }

        displayMenu()
    }
}

fun main() {
    val analyzer = GreenhouseAnalyzer()
    analyzer.displayMenu()
}
