package ar.edu.unahur.obj2.impostoresPaises

import ar.edu.unahur.obj2.impostoresPaises.cli.Observatorio
import ar.edu.unahur.obj2.impostoresPaises.cli.Pais
import ar.edu.unahur.obj2.impostoresPaises.cli.Persona
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class PaisTest: DescribeSpec ({
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

    // variables [argentina, brasil, chile, paraguay, peru]

    val bolivia = Pais("Bolivia", "BOL", 10985059, 1098581.0, "América", "BOB", 6.89,
        mutableListOf("UNASUR"), mutableSetOf("Español", "Quechua", "Aymara")
    )

    val argentina = Pais("Argentina", "ARG",47327407,2780400.0,"America","ARS",200.0,
        mutableListOf("MERCOSUR","CELAC","OEA"), mutableSetOf("Español","Quechua")
    )
    val brasil = Pais("Brasil","BRA",217240060,8515770.0,"America","BRL",5.41,
        mutableListOf("ONU","CPLP","OEA","UNASUR","MERCOSUR"), mutableSetOf("Portugues")
    )
    val chile = Pais("Chile","CHL", 18430408,756950.0,"America","CLP",1050.0,
        mutableListOf("UNASUR","OEA"), mutableSetOf("Español"))
    val paraguay = Pais("Paraguay", "PRY", 406752,7453695.0,"America", "PYG", 6860.2,
            mutableListOf(""), mutableSetOf("Español", "Guarani")
    )
    val peru = Pais("Peru", "PER", 33396698, 1285216.2, "America", "SOL", 3.91,
        mutableListOf(""), mutableSetOf("Español", "Quechua")
    )
    val colombia = Pais("Colombia", "COL", 51609474, 1141748.0, "America", "COP", 4496.5,
        mutableListOf("CELAC"), mutableSetOf("Español")
    )
    val uruguay = Pais("Uruguay", "URG", 555444,120999.2,"America", "URG", 32.5,
        mutableListOf("MERCOSUR"), mutableSetOf("Español")
    )

    bolivia.agregarPaisesLimitofes(mutableListOf(argentina,peru,brasil,chile))
    argentina.agregarPaisesLimitofes(mutableListOf(brasil,uruguay,bolivia,chile,paraguay))
    chile.agregarPaisesLimitofes(mutableListOf(argentina,bolivia,peru))
    peru.agregarPaisesLimitofes(mutableListOf(brasil,chile,colombia))
    brasil.agregarPaisesLimitofes(mutableListOf(argentina,bolivia,paraguay,peru))

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
                bolivia.densidadPoblacional().shouldBe(10)
            }
            it("4-Conocer el vecino más poblado"){

                brasil.vecinoMasPoblado().shouldBe(brasil)
                peru.vecinoMasPoblado().shouldBe(brasil)
            }
        }
        describe("Para dos países en particular"){
            // hace falta aca el observatorio para las siguientes funciones ?
            it("1-Saber si dos países son limitrofes") {

                brasil.esLimitrofeDe(peru).shouldBeTrue()
                bolivia.esLimitrofeDe(brasil).shouldBeTrue()
                peru.esLimitrofeDe(colombia).shouldBeTrue()

                bolivia.esLimitrofeDe(colombia).shouldBeFalse()
                argentina.esLimitrofeDe(colombia).shouldBeFalse()
            }
            it("2-Saber si dos países necesitan traducción para dialogar"){
                brasil.necesitanTraductor(peru).shouldBeTrue()
                brasil.necesitanTraductor(argentina).shouldBeTrue()
                argentina.necesitanTraductor(peru).shouldBeFalse()
            }

            it("3-Conocer si son potenciales aliados"){
                colombia.sonPotencialesAliados(peru).shouldBeFalse()
                argentina.sonPotencialesAliados(chile).shouldBeTrue()
                brasil.sonPotencialesAliados(argentina).shouldBeFalse()
                uruguay.sonPotencialesAliados(argentina).shouldBeTrue()
            }

            it("4-¿Conviene ir de compras de un país al otro?"){
                argentina.convieneComprarEn(brasil).shouldBeFalse()
                brasil.convieneComprarEn(argentina).shouldBeTrue()
                colombia.convieneComprarEn(bolivia).shouldBeFalse()
                bolivia.convieneComprarEn(colombia).shouldBeTrue()

            }

            it("5-Conocer a cuánto equivale un monto en la moneda local, en la moneda de destino"){

                argentina.convertirMonedaA(1000, brasil).shouldBe(27) //27.05
                colombia.convertirMonedaA(300000, chile).shouldBe(70054) //70054.48
                uruguay.convertirMonedaA(700, chile).shouldBe(22615) //22615.38
                peru.convertirMonedaA(950, argentina).shouldBe(48593) //48593.35
                brasil.convertirMonedaA(1000,colombia).shouldBe(831146) //831146.02
            }
        }
    }
    describe("Etapa 2 - Observatorio"){
        /*
        El Observatorio es un objeto que conoce a todos los países y debe
        poder responder las consultas que se enuncian a continuación.
        */
        Observatorio.paises = mutableListOf(bolivia,argentina,brasil,chile,paraguay,peru,colombia,uruguay)

        describe("Para dos países en particular, se envia el nombre por parametro no el objeto"){
            it("1-Si dos países son limitrofes"){
                Observatorio.sonLimitrofes("Brasil","Peru").shouldBeTrue()
                Observatorio.sonLimitrofes("Bolivia", "Brasil").shouldBeTrue()
                Observatorio.sonLimitrofes("Peru", "Colombia").shouldBeTrue()

                Observatorio.sonLimitrofes("Bolivia", "Colombia").shouldBeFalse()
                Observatorio.sonLimitrofes("Argentina", "Colombia").shouldBeFalse()
            }

            it("2-Saber si necesitan traducción para poder dialogar "){
                Observatorio.necesitanTraductor("Brasil", "Peru").shouldBeTrue()
                Observatorio.necesitanTraductor("Brasil", "Peru").shouldBeTrue()
                Observatorio.necesitanTraductor("Argentina", "Peru").shouldBeFalse()
            }


            it("3-Conocer si son potenciales aliados"){
                Observatorio.sonPotencialesAliados("Colombia", "Peru").shouldBeFalse()
                Observatorio.sonPotencialesAliados("Argentina", "Chile").shouldBeTrue()
                Observatorio.sonPotencialesAliados("Brasil", "Argentina").shouldBeFalse()
                Observatorio.sonPotencialesAliados("Uruguay", "Argentina").shouldBeTrue()
            }


            it("4-¿Conviene ir de compras de un país al otro?"){

                // ver nombre funcion?

                Observatorio.convieneComprarDesdeA("Argentina", "Brasil").shouldBeFalse()
                Observatorio.convieneComprarDesdeA("Brasil", "Argentina").shouldBeTrue()
                Observatorio.convieneComprarDesdeA("Colombia", "Bolivia").shouldBeFalse()
                Observatorio.convieneComprarDesdeA("Bolivia", "Colombia").shouldBeTrue()
            }


            it("5-Conocer a cuánto equivale un monto en la moneda local, en la moneda de destino"){

                Observatorio.convertirMoneda(1000,"Argentina","Brasil").shouldBe(27)    //27.05
                Observatorio.convertirMoneda(300000,"Colombia","Chile").shouldBe(70054) //70054.48
                Observatorio.convertirMoneda(700,"Uruguay","Chile").shouldBe(22615)     //22615.38
                Observatorio.convertirMoneda(950,"Peru","Argentina").shouldBe(48593)    //48593.35
                Observatorio.convertirMoneda(1000,"Brasil","Colombia").shouldBe(831146) //831146.02

            }


        }

        describe("Sobre el conjunto de todos los países "){
            it("1-Obtener los códigos ISO de los 5 países con mayor densidad poblacional"){

                // [Colombia, Brasil, Peru, Chile, Argentina]
                // [COL, BRA, PER, CHL, ARG]
                Observatorio.cincoDeMayorDensidadPoblacional().shouldBe(mutableSetOf("COL", "BRA", "PER", "CHL", "ARG"))
            }
            /*
            it("2-Indicar el nombre del continente con más paises plurinacionales"){
                // chequear
                Observatorio.continenteMasPlurinacional().shouldBe("America")
            }
            it("3-Conocer el promedio de densidad poblacional de los países insulares (países que son islas)"){
                // chequear
                Observatorio.promedioDensidadPoblacionalInsulares().shouldBe(5.4)
            }
            */
        }



    }
})


