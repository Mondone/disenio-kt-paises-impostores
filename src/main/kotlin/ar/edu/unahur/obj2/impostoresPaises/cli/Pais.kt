package ar.edu.unahur.obj2.impostoresPaises.cli

import kotlin.math.roundToInt

class Persona (val nombre: String, val edad: Int){
    fun decimeEdad() = edad
}
// test para paises
/* Ejemplo atributos para un pais
    nombre: "Bolivia"
    codigoIso3: "BOL"
    poblacion: 10985059
    superficie: 1098581.0
    continente: "América"
    codigoMoneda: "BOB"
    cotizacionDolar: 6.89
    // Notar que estos son objetos, no strings
    paisesLimitrofes: [argentina, brasil, chile, paraguay, peru]
    bloquesRegionales: ["UNASUR"]
    idiomasOficiales: ["Español", "Quechua", "Aymara"]
 */


class Pais (val nombre: String, val ISO3: String, val poblacion: Int, val superficie: Double, val continente: String, val codMoneda: String,
            val cotDolar: Double, var bloquesRegionales: MutableList<String>, var idiomasOficiales: MutableList<String>){

    var paisesLimitrofes = mutableListOf<Pais>()

    fun agregarPaisesLimitofes(paises: MutableList<Pais>){
        paisesLimitrofes = paises
    }

    fun esPlurinacional() = ( idiomasOficiales.size > 1 )

    fun esUnaIsla() = paisesLimitrofes.isEmpty()

    fun densidadPoblacional() = (poblacion/superficie).roundToInt()

    fun vecinoMasPoblado() = paisesLimitrofes.maxByOrNull { pais -> pais.poblacion } // aca falta comparar contra el propio pais

    fun sonPaisesLimitrofes(unPais: Pais): Boolean {

    }

    fun necesitanTraductor(unPais: Pais: Pais): Boolean{

    }

    fun sonPotencialesAliados(unPais: Pais: Pais): Boolean {

    }

    fun convieneComprarEn(paisDestino: Pais): Boolean {

    }

    fun convertirMonedaA(monto: Int, paisDestino: Pais): Int {

    }



}

