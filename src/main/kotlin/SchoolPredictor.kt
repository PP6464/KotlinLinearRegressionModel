import java.io.File

@Suppress("unused")
enum class Schools {
    ANKYI,
    CCAAW,
    CIMBB,
    CUQAM,
    DNQDD,
    FBUMG,
    GJJHK,
    GOKXL,
    GOOBU,
    IDGFP,
    KFZMY,
    KZKKE,
    LAYPA,
    OJOBU,
    QOQTS,
    UAGPU,
    UKPGS,
    UUUQX,
    VHDHF,
    VKWQH,
    VVTVA,
    ZMNYA,
    ZOWMK,
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
            xTest.add(Schools.valueOf(it.split(",")[0]).ordinal.toDouble())
            yTest.add(it.split(",").last().toDouble())
        } else {
            xTrain.add(Schools.valueOf(it.split(",")[0]).ordinal.toDouble())
            yTrain.add(it.split(",").last().toDouble())
        }
        index++
    }

    println(xTest)
    println(yTest)

    val model = Model(xTrain, yTrain)
    model.test(xTest, yTest)
}
