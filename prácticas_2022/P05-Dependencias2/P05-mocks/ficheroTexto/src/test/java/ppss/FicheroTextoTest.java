package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.dgc.Lease;

import static org.junit.jupiter.api.Assertions.*;

class FicheroTextoTest {

    FicheroTexto ficheroTestable;
    FileReader filereadermock;

    @BeforeEach
    void a(){
        ficheroTestable = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("getFileReader").createMock();
        filereadermock = EasyMock.createMock(FileReader.class);

    }

    @Test
    void C1_contarCaracteres(){
        String nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P05-Dependencias2/src/test/resources/ficheroC1.txt";
        String mensajeExcepcionEsperado = nombreFichero + " (Error al leer el archivo)";


        assertDoesNotThrow(() -> EasyMock.expect(ficheroTestable.getFileReader(nombreFichero)).andReturn(filereadermock));
        assertDoesNotThrow(() -> EasyMock.expect(filereadermock.read())
                .andReturn((int) 'a').andReturn((int) 'b').andThrow(new IOException()));

        EasyMock.replay(ficheroTestable,filereadermock);
        FicheroException excepcionObtenida = assertThrows(FicheroException.class, () -> ficheroTestable.contarCaracteres(nombreFichero));
        EasyMock.verify();

        assertEquals(mensajeExcepcionEsperado,excepcionObtenida.getMessage());
    }

    @Test
    void C2_contarCaracteres(){
        String nombreFichero = "/home/ppss/ppss-2022-g1-formanyuk/P05-Dependencias2/src/test/resources/ficheroC2.txt";
        String mensajeExcepcionEsperado = nombreFichero + " (Error al cerrar el archivo)";

        assertDoesNotThrow(() -> EasyMock.expect(ficheroTestable.getFileReader(nombreFichero)).andReturn(filereadermock));
        assertDoesNotThrow(() -> EasyMock.expect(filereadermock.read())
                .andReturn((int) 'a').andReturn((int) 'b').andReturn((int) 'c'));

        EasyMock.replay(ficheroTestable,filereadermock);
        FicheroException excepcionObtenida = assertThrows(FicheroException.class, () -> ficheroTestable.contarCaracteres(nombreFichero));
        EasyMock.verify();

        assertEquals(mensajeExcepcionEsperado,excepcionObtenida.getMessage());

    }
}