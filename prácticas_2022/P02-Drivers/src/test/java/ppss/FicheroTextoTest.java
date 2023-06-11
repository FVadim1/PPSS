package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FicheroTextoTest {

    String nombreFichero;
    int resultadoEsperado;
    FicheroException excepcionEsperada;
    String mensajeExcepcionEsperado;
    FicheroTexto ft;

    @Test
    void contarCaracteresC1(){

        nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P02-Drivers/src/test/resources/ficheroC1.txt";
        mensajeExcepcionEsperado = nombreFichero + " (No existe el archivo o el directorio)";

        ft = new FicheroTexto();
        excepcionEsperada = assertThrows(FicheroException.class, () -> ft.contarCaracteres(nombreFichero));
        assertEquals( mensajeExcepcionEsperado , excepcionEsperada.getMessage());
    }

    @Test
    void contarCaracteresC2() throws FicheroException {

        nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P02-Drivers/src/test/resources/ficheroCorrecto.txt";
        resultadoEsperado = 3;
        ft = new FicheroTexto();
        int resultadoObtenido = ft.contarCaracteres(nombreFichero);

        assertEquals(resultadoEsperado , resultadoObtenido);
    }

    @Tag("excluido")
    @Test
    void contarCaracteresC3(){

        nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P02-Drivers/src/test/resources/ficheroC3.txt";
        mensajeExcepcionEsperado = nombreFichero + " (Error al leer el archivo)";

        ft = new FicheroTexto();
        excepcionEsperada = assertThrows(FicheroException.class, () -> ft.contarCaracteres(nombreFichero));
        assertEquals( mensajeExcepcionEsperado , excepcionEsperada.getMessage());
    }

    @Tag("excluido")
    @Test
    void contarCaracteresC4(){

        nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P02-Drivers/src/test/resources/ficheroC4.txt";
        mensajeExcepcionEsperado = nombreFichero + " (Error al leer el archivo)";

        ft = new FicheroTexto();
        excepcionEsperada = assertThrows(FicheroException.class, () -> ft.contarCaracteres(nombreFichero));
        assertEquals( mensajeExcepcionEsperado , excepcionEsperada.getMessage());
    }
}