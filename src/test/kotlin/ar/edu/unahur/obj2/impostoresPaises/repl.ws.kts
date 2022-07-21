import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import ar.edu.unahur.obj2.impostoresPaises.cli.CincoMayorDensidadConcreteDecorator
import ar.edu.unahur.obj2.impostoresPaises.cli.ObservatorioConcrete
import ar.edu.unahur.obj2.impostoresPaises.cli.*

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API
/*
val api = RestCountriesAPI()
api.buscarPaisesPorNombre("guay")
api.paisConCodigo("CHL")

val currencyApi = CurrencyConverterAPI("poné acá la API key")
// PEN es el código del sol peruano
currencyApi.convertirDolarA("PEN")
*/

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
var observatorio = ObservatorioConcrete()

observatorio.paises = mutableListOf(bolivia,argentina,brasil,chile,paraguay,peru,colombia,uruguay)
val observatorioDecorado = CincoMayorDensidadConcreteDecorator(observatorio)

observatorioDecorado.cincoDeMayorDensidadPoblacional()