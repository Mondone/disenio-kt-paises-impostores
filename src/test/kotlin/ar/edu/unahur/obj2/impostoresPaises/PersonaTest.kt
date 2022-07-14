package ar.edu.unahur.obj2.impostoresPaises

import ar.edu.unahur.obj2.impostoresPaises.cli.Persona
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PersonaTest: DescribeSpec ({
    describe("Persona"){
        val p1 = Persona("Hernan", 20)
        p1.decimeEdad().shouldBe(20)
    }
})