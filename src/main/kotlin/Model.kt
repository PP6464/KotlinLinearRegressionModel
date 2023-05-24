import kotlin.math.pow
import kotlin.math.sqrt

fun List<Double>.mean(): Double {
    return sum() / count().toDouble()
}

class Model(private val independentVariables: List<Double>, private val dependentVariables: List<Double>) {
    private val meanX = independentVariables.mean()
    private val meanY = dependentVariables.mean()
    private val variance = independentVariables.stream().mapToDouble { (it - meanX).pow(2) }.sum()
    private fun covariance(): Double {
        var covariance = 0.0
        for (i in independentVariables.indices) {
            val xPart = independentVariables[i] - meanX
            val yPart = dependentVariables[i] - meanY
            covariance += xPart * yPart
        }
        return covariance
    }
    private val b1 = covariance() / variance
    private val b0 = meanY - b1 * meanX

    fun test(xTest: List<Double>, yTest: List<Double>) {
        var errorSum = 0.0
        var sst = 0.0
        var ssr = 0.0
        for (i in 0 until xTest.count()) {
            val x = xTest[i]
            val y = yTest[i]
            val yPredicted = predict(x)
            errorSum += (yPredicted - y).pow(2)
            sst += (y - meanY).pow(2)
            ssr += (y - yPredicted).pow(2)
        }
        println("RMSE = " + sqrt(errorSum / xTest.size.toDouble()))
        println("R² = " + (1.0 - (ssr / sst)))
    }

    fun predict(independentVariable: Double) = b0 + b1 * independentVariable
}