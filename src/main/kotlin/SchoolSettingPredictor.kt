import java.io.File

@Suppress("unused")
enum class SchoolSettings {
    Urban,
    Suburban,
    Rural,
}

fun main() {
    val xTrain = mutableListOf<Double>()
    val yTrain = mutableListOf<Double>()
    val xTest = mutableListOf<Double>()
    val yTest = mutableListOf<Double>()
    var index = 0

    File("src/main/resources/test_scores.csv").forEachLine {
        if (index == 0) {
            index++
            return@forEachLine
        }
        if (index % 9 == 0) {
            xTest.add(SchoolSettings.valueOf(it.split(",")[1]).ordinal.toDouble())
            yTest.add(it.split(",").last().toDouble())
        } else {
            xTrain.add(SchoolSettings.valueOf(it.split(",")[1]).ordinal.toDouble())
            yTrain.add(it.split(",").last().toDouble())
        }
        index++
    }

    println(xTest)
    println(yTest)

    val model = Model(xTrain, yTrain)
    model.test(xTest, yTest)
}
