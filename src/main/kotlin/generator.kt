import java.io.File
import kotlin.random.Random

fun main() {
    val random = Random(System.currentTimeMillis())
    val numberOfEntries = 25  // Número de entradas no arquivo CSV
    val minValue = 50.0
    val maxValue = 150.0
    val average = (minValue + maxValue) / 2  // Média entre os valores mínimo e máximo
    val maxDifference = 5.0  // Diferença máxima permitida entre um valor aleatório e a média
    val rareThreshold = 0.1  // Limiar de raridade para permitir valores que ultrapassem o maxDifference

    val csvFile = File("src/main/kotlin/sensor.csv")
    csvFile.bufferedWriter().use { writer ->

        for (i in 1..numberOfEntries) {
            val id = i
            var luz = random.nextDouble(minValue, maxValue)
            var umidade = random.nextDouble(minValue, maxValue)
            var temperatura = random.nextDouble(minValue, maxValue)

            if (random.nextDouble() < rareThreshold) {
                luz += random.nextDouble(-maxDifference, maxDifference)
                umidade += random.nextDouble(-maxDifference, maxDifference)
                temperatura += random.nextDouble(-maxDifference, maxDifference)
            } else {
                while (Math.abs(luz - average) > maxDifference) {
                    luz = random.nextDouble(minValue, maxValue)
                }

                while (Math.abs(umidade - average) > maxDifference) {
                    umidade = random.nextDouble(minValue, maxValue)
                }

                while (Math.abs(temperatura - average) > maxDifference) {
                    temperatura = random.nextDouble(minValue, maxValue)
                }
            }
            writer.write("$id,$luz,$umidade,$temperatura\n")
        }
    }

    println("Arquivo CSV gerado com sucesso!")
}
