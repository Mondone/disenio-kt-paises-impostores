package ar.edu.unahur.obj2.impostoresPaises.cli

import kotlin.math.roundToInt

object Observatorio {

    var paises = mutableListOf<Pais>()

    fun sonLimitrofes(unPais: String, otroPais: String): Boolean {
        val primerPais = paises.find { p -> p.nombre == unPais}!!
        val segundoPais = paises.find { p -> p.nombre == otroPais}!!

        var res = false
        if (primerPais.paisesLimitrofes.contains(segundoPais)) {
            res = true
        }
        return res
    }


    fun necesitanTraductor(unPais: String, otroPais: String): Boolean {
        val primerPais = paises.find { p -> p.nombre == unPais}!!
        val segundoPais = paises.find { p -> p.nombre == otroPais}!!

        var res = false
        if (primerPais.idiomasOficiales.intersect(segundoPais.idiomasOficiales).isEmpty()) {
            res = true
        }
        return res
    }

    fun sonPotencialesAliados(unPais: String, otroPais: String): Boolean {
        val primerPais = paises.find { p -> p.nombre == unPais}!!
        val segundoPais = paises.find { p -> p.nombre == otroPais}!!

        var res = false
        if(!primerPais.necesitanTraductor(segundoPais) && primerPais.bloquesRegionales.toMutableSet().intersect(segundoPais.bloquesRegionales.toMutableSet()).isNotEmpty()){
            res = true
        }
        return res
    }

   fun convieneComprarDesdeA(unPais: String, paisDestino: String): Boolean {
        val origen = paises.find { p -> p.nombre == unPais}!!
        val destino = paises.find { p -> p.nombre == paisDestino}!!

        return origen.cotDolar < destino.cotDolar
    }

    // fun convertirMonedaA(monto: Int, paisDestino: Pais): Int = (monto/cotDolar*paisDestino.cotDolar).roundToInt()
    fun convertirMoneda(monto: Int, paisOrigen: String, paisDestino: String): Int {
        val origen = paises.find { p -> p.nombre == paisOrigen}!!
        val destino = paises.find { p -> p.nombre == paisDestino}!!

        return (monto/origen.cotDolar*destino.cotDolar).roundToInt()

    }

    /*
    fun cincoDeMayorDensidadPoblacional(): Set<String> {
        
    }

    fun continenteMasPlurinacional(): String {

    }

    fun promedioDensidadPoblacionalInsulares(): Double {

    }
*/

}