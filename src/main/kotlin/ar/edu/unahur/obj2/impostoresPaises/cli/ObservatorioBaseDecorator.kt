package ar.edu.unahur.obj2.impostoresPaises.cli

abstract class ObservatorioBaseDecorator(val observatorio: ObservatorioComponent): ObservatorioComponent {
    override fun cincoDeMayorDensidadPoblacional() : String {
        return this.observatorio.cincoDeMayorDensidadPoblacional()
    }
}