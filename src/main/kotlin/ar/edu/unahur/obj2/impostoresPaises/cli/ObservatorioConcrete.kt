package ar.edu.unahur.obj2.impostoresPaises.cli

import kotlin.math.roundToInt


class ObservatorioConcrete : ObservatorioComponent{

    var paises = mutableListOf<Pais>()

    override fun cincoDeMayorDensidadPoblacional() : String {
        var res = ""
        paises.sortByDescending { p -> p.densidadPoblacional()}
        paises.forEach { p -> print(p.ISO3)}
        for (i in 0..4){
            val codigo = paises[i].ISO3
            res +=  codigo + " "
        }
        return res
    }

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

    fun continenteMasPlurinacional(): String {
        //Utilizo un Map para indicar CONTINENTE - CANTIDAD
        val ocurrenciasContinente: MutableMap<String, Int> = HashMap()

        for(p in paises){
            if(p.esPlurinacional()) {
                var count = ocurrenciasContinente[p.continente]
                if (count == null) count = 0
                ocurrenciasContinente[p.continente] = count + 1
            }
        }

        return ocurrenciasContinente.maxByOrNull { it.value }?.key.toString()
    }

    fun promedioDensidadPoblacionalInsulares(): Int {
        var paisesInsulares = paises.filter { p -> p.esUnaIsla() }
        return paisesInsulares.sumOf { p -> p.densidadPoblacional() } / paisesInsulares.size

    }


}