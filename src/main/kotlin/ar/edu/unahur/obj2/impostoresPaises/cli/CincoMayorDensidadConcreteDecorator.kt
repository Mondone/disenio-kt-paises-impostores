package ar.edu.unahur.obj2.impostoresPaises.cli

class CincoMayorDensidadConcreteDecorator(observatorio: ObservatorioComponent): ObservatorioBaseDecorator(observatorio) {
    override fun cincoDeMayorDensidadPoblacional(): String {
        return "Los 5 con Mayor Densidad (Decorado) son: " + super.cincoDeMayorDensidadPoblacional()
    }
}