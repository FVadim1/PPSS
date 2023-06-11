package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    GestorLlamadas gestorLlamadasTestable;
    Calendario calendarioMock;

    @BeforeEach
    void a(){
        //se crean los dobles mock de las dos dependencias que queremos controlar
        calendarioMock = EasyMock.createMock(Calendario.class);
        gestorLlamadasTestable = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock();
    }

    @Test
    void C1_calculaConsumo(){
        int minutos = 22;
        int hora = 10;
        double resultadoEsperado = 457.6;


        // indicamos al mock que tiene que debe realizar una llamada a .getCalendario() y devolver un objeto
        // de tipo calendario
        EasyMock.expect(gestorLlamadasTestable.getCalendario()).andReturn(calendarioMock);
        // indicamos al mock que tiene que debe realizar una llamada a .getHoraActual() y devolver hora (10)
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(hora);
        // Indicamos que ya estamos listos para ejecutar el mock (el estado del mock cambia
        // de "record mode" a "replay mode". Es necesario pasar a este estado antes de ejecutar el mock).
        // Cuando se ejecute el mock se comprobará que los parámetros de invocación sean los correctos y que se
        // llama al método una sola vez
        EasyMock.replay(calendarioMock, gestorLlamadasTestable);

        //Ejecutamos el método a probar utilizando el mock
        double resultadoObtenido = gestorLlamadasTestable.calculaConsumo(minutos);
        //Se verifica que realmente se invoca al mock desde nuestro SUT
        EasyMock.verify(calendarioMock, gestorLlamadasTestable);
        //Comparamos el resultado real con el esperado
        assertEquals(resultadoEsperado, resultadoObtenido, 0.01f);
    }

    @Test
    void C2_calculaConsumo(){
        int minutos = 13;
        int hora = 21;
        double resultadoEsperado = 136.5;

        EasyMock.expect(gestorLlamadasTestable.getCalendario()).andReturn(calendarioMock);
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(hora);
        EasyMock.replay(calendarioMock, gestorLlamadasTestable);

        double resultadoObtenido = gestorLlamadasTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoObtenido, 0.01f);

        EasyMock.verify(calendarioMock, gestorLlamadasTestable);

    }
}