package ppss;


import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremioTest {

    Premio premioTestable;
    ClienteWebService clienteMock;

    @BeforeEach
    void a(){
        //se crean los dobles mock de las dos dependencias que queremos controlar
        clienteMock = EasyMock.createMock(ClienteWebService.class);
        premioTestable = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createMock();

        premioTestable.cliente = clienteMock;

    }

    @Test
    void C1_compruebaPremio() {
        float numAleatorioGenerado = 0.07f;
        String obtenerPremiodevuelve = "entrada final Champions";

        //Pongo lo que quiero que devuelvan estos métodos (como el override de los stubs)
        EasyMock.expect(premioTestable.generaNumero()).andReturn(numAleatorioGenerado);
        //EasyMock.expect(clienteMock.obtenerPremio()).andReturn(obtenerPremiodevuelve); NO, ya que suelta excepcion
        assertDoesNotThrow(() -> EasyMock.expect(clienteMock.obtenerPremio()).andReturn(obtenerPremiodevuelve));
        EasyMock.replay(clienteMock, premioTestable);

        String resultadoEsperado = "Premiado con entrada final Champions";
        String resultadoObtenido = premioTestable.compruebaPremio();
        //Se verifica que realmente se invoca al mock desde nuestro SUT
        EasyMock.verify(clienteMock, premioTestable);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    void C2_compruebaPremio() {
        float numAleatorioGenerado = 0.03f;

        EasyMock.expect(premioTestable.generaNumero()).andReturn(numAleatorioGenerado);
        assertDoesNotThrow(() -> EasyMock.expect(clienteMock.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        EasyMock.replay(clienteMock, premioTestable);

        String resultadoEsperado = "No se ha podido obtener el premio";
        String resultadoObtenido = premioTestable.compruebaPremio();

        EasyMock.verify(clienteMock, premioTestable);

        assertEquals(resultadoEsperado, resultadoObtenido);

    }

    @Test
    void C3_compruebaPremio() {
        float numAleatorioGenerado = 0.3f;

        EasyMock.expect(premioTestable.generaNumero()).andReturn(numAleatorioGenerado);
        EasyMock.replay(clienteMock, premioTestable);

        String resultadoEsperado = "Sin premio";
        String resultadoObtenido = premioTestable.compruebaPremio();

        EasyMock.verify(clienteMock, premioTestable);

        assertEquals(resultadoEsperado, resultadoObtenido);

    }
}