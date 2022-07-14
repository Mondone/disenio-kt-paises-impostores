package ar.edu.unahur.obj2.impostoresPaises

import ar.edu.unahur.obj2.impostoresPaises.cli.Persona
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

import io.kotest.matchers.nulls.shouldBeNull
class PersonaTest: DescribeSpec ({
    describe("Persona"){
        val p1 = Persona("Hernan", 20)
        p1.decimeEdad().shouldBe(20)
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

    // variables

    val bolivia = Pais("Bolivia", "BOL", 10985059, 1098581.0, "América", "BOB", 6.89,
        [argentina, brasil, chile, paraguay, peru], ["UNASUR"], ["Español", "Quechua", "Aymara"])

    describe("Etapa 1 - Calentando motores") {
        /*
         Modelar los países con los atributos mencionados y resolver los siguientes requerimientos.
        */
        describe("Para un país"){
            it("1-Saber si un país es plurinacional, esto ocurre si tiene mas de un idioma oficial"){
                bolivia.esPlurinacional().shouldBeTrue()
            }
            it("2-Saber si un país es una isla, (no tiene paises limitrofes)"){
                bolivia.esUnaIsla().shouldBeFalse()
            }
            it("3-Saber la densidad poblacional (devuelve Int)"){
                bolivia.densidadPoblacional().shouldBe(9)
            }
            it("4-Conocer el vecino más poblado"){
                brasil.vecinoMasPoblado().shoudBe(peru)
                peru.vecinoMasPoblado().shoudBe(brasil)
            }
        }
        describe("Para dos países en particular"){
            // hace falta aca el observatorio para las siguientes funciones ?
            it("1-Saber si dos países son limitrofes") {

                sonPaisesLimitrofes(brasil, peru).shouldBeTrue()
                sonPaisesLimitrofes(bolivia,brasil).shouldBeTrue()
                sonPaisesLimitrofes(peru,colombia).shouldBeTrue()

                sonPaisesLimitrofes(bolivia,colombia).shouldBeFalse()
                sonPaisesLimitrofes(argentina,colombia).shouldBeFalse()
            }
            it("2-Saber si dos países necesitan traducción para dialogar"){
                necesitanTraductor(brasil,peru).shouldBeTrue()
                necesitanTraductor(brasil,argentina).shouldBeTrue()
            }
            it("3-Conocer si son potenciales aliados"){
                sonPotencialesAliados(colombia,peru).shouldBeTrue()
                sonPotencialesAliados(argentina,chile).shouldBeTrue()
            }
            it("4-¿Conviene ir de compras de un país al otro?"){
                /*
                Verdadero cuando la cotizacion del dólar en el país destino es mayor.

                Ejemplo: Argentina la cotización es de 190 y en Bolivia de 6.89,
                no conviene ir de Argentina a Bolivia pero sí al revés.
                */
                argentina.convieneComprarEn(brasil).shouldBeFalse()
                brasil.convieneComprarEn(argentina).shouldBeTrue()

            }
            it("5-Conocer a cuánto equivale un monto en la moneda local, en la moneda de destino"){
                /*
                 Como la referencia que estamos tomando es el precio del dólar,
                la equivalencia se haría convirtiendo primero a dólar y luego a la moneda de destino.
                */
                argentina.convertirMonedaA(1000, brasil).shouldBe(999) // verificar numeros
                colombia.convertirMonedaA(3000, chile).shouldBe(500)   // verificar numeros
            }
        }
    }



})