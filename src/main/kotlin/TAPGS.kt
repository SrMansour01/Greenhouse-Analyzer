import java.io.File
import kotlin.math.round
import kotlin.math.floor
import kotlin.math.ceil

data class Data(val ID: Int, val luz: Double)

data class Sensor(val ID:Int, val value:Double)

class Analizer{

    private val sensorDataList: MutableList<Data> = mutableListOf()

    private val outliersLuz: MutableList<Sensor> = mutableListOf()


    fun readDataFileIni() {
        val file = File("C:\\Users\\Mansour\\IdeaProjects\\UML\\src\\main\\kotlin\\sensor.csv")
        if (file.exists()) {
            val lines = file.readLines()
            println("documento encontrado exemplo encontrado")
            for (line in lines) {
                val values = line.split(",")
                val ID = values[0].toInt()
                val luz = values[1].toDouble()
                val SensorData = Data(ID, luz)
                sensorDataList.add(SensorData)
            }
            println("leitura efetuada do CSV Exemplo")
            print("deseja vizualizar os dados? (S - sim | N - não): ")
            val visu = readLine().toString().uppercase()
            if (visu == "S"){
                println("Dados do sensores")
                for (data in sensorDataList) {
                    //println("ID: ${data.ID} - luz: ${"%0,4f".format(data.luz)} | umidade do solo ${"%0,4f".format(data.umin)} |temperatura  ${"%0,4f".format(data.temp)}")
                    println(data)
                }
            }
        } else {
            println("erro para encontrar documento")
        }
    }

    fun readDataFile() {
        print("caminho compreto do documento(C:\\Users\\Mansour\\IdeaProjects\\UML\\src\\main\\kotlin\\sensor.csv): ")
        val filePath = readLine().toString()
        val file = File(filePath)
        if (file.exists()) {
            println("documento encontrado, removendo dados de exemplo")
            sensorDataList.clear()
            println("dados apagados: ${sensorDataList}")
            val lines = file.readLines()
            for (line in lines) {
                val values = line.split(",")
                val ID = values[0].toInt()
                val luz = values[1].toDouble()
                val SensorData = Data(ID, luz)
                sensorDataList.add(SensorData)

            }
        } else {
            println("documento nn encontrado")
        }
    }

    fun CalcularMediaSensore():Double {
        val sumLuz = round(sensorDataList.sumOf { it.luz } / sensorDataList.size*100)/100
        return sumLuz
    }

    fun MaxMin(){
        val maxLuz = sensorDataList.maxOf { it.luz }

        val minLuz = sensorDataList.minOf { it.luz }

        println("sensor luz max: ${maxLuz} e mim ${minLuz}")
    }

    fun outliers() {
        val luz = CalcularMediaSensore()

        val Q1Index = (sensorDataList.size + 1) / 4.0
        val Q1Lower = floor(Q1Index).toInt()
        val Q1Upper = ceil(Q1Index).toInt()

        val Q3Index = 3 * (sensorDataList.size + 1) / 4.0
        val Q3Lower = floor(Q3Index).toInt()
        val Q3Upper = ceil(Q3Index).toInt()

        val Q1Luz = sensorDataList[Q1Lower - 1].luz + (Q1Index - Q1Lower) * (sensorDataList[Q1Upper - 1].luz - sensorDataList[Q1Lower - 1].luz)
        val Q3Luz = sensorDataList[Q3Lower - 1].luz + (Q3Index - Q3Lower) * (sensorDataList[Q3Upper - 1].luz - sensorDataList[Q3Lower - 1].luz)

        println("luz: Quartil 1: $Q1Luz | Quartil 3: $Q3Luz")

        val mediaLuz = luz

        val IQR_Luz = Q3Luz - Q1Luz
        println("IQR luz: $IQR_Luz")

        val L_InfLuz = mediaLuz + 1.5 * IQR_Luz
        val L_SupLuz = mediaLuz - 1.5 * IQR_Luz

        println("Limite Superior Luz: $L_SupLuz | Limite Inferior Luz: $L_InfLuz")

        for (data in sensorDataList) {
            if (data.luz > L_InfLuz || data.luz < L_SupLuz) {
                outliersLuz.add(Sensor(data.ID, data.luz))
            }
        }

        println("${outliersLuz.size}")
        println("${outliersLuz}")
    }




    fun Display () {
        println("\n----- EstufaSmart Analizer -----")
        println("1 → Reler os dados do CSV File ")
        println("2 → Calculo de media")
        println("3 → valor max e min dos sensores")
        println("4 → Identificar e tratar outliers")
        println("0. Exit")
        println("--------------------------------")
        print("\nDigite sua escolha: ")
        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {readDataFile()}
            2 -> {
                val luz = CalcularMediaSensore()
                println("a media é: \n  luz: ${luz}")
            }
            3 -> {
                MaxMin()
            }
            4 -> {
                outliers()
            }
        }
    }


}

fun main() {
    val analizer = Analizer()
    analizer.readDataFileIni()
    while (true) {
        analizer.Display()
    }
}