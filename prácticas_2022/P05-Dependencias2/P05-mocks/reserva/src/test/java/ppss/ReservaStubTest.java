package ppss;


import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaStubTest {

    Reserva reservaTestable;
    IOperacionBO operacionstub;
    FactoriaBOs factoriastub;

    @BeforeEach
    void a(){
        //se crean los dobles mock de las dos dependencias que queremos controlar
        reservaTestable  = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos", "getFactoriaBOS").createMock();
        factoriastub  = EasyMock.createNiceMock(FactoriaBOs.class);
        operacionstub = EasyMock.createNiceMock(IOperacionBO.class);
    }

    @Test
    void C1_realizaReserva() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String isbns[] = {"22222"};

        String excepcionEsperada = "ERROR de permisos; ";

        //
        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(false);
        EasyMock.replay(reservaTestable);

        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        assertEquals(excepcionEsperada, excepcionObtenida.getMessage());
    }

    @Test
    void C2_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"22222","33333"};

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andStubReturn(factoriastub);
        EasyMock.expect(factoriastub.getOperacionBO()).andStubReturn(operacionstub);
        EasyMock.replay(reservaTestable, factoriastub, operacionstub);

        assertDoesNotThrow(() -> reservaTestable.realizaReserva(login, password, socio, isbns));
    }

    @Test
    void C3_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"11111"};

        String excepcionEsperada = "ISBN invalido:11111; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andStubReturn(factoriastub);
        EasyMock.expect(factoriastub.getOperacionBO()).andStubReturn(operacionstub);

        //bucle for
        assertDoesNotThrow(() -> operacionstub.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());

        EasyMock.replay(reservaTestable, factoriastub, operacionstub);

        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        assertEquals(excepcionEsperada, excepcionObtenida.getMessage());
    }
    @Test
    void C4_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String isbns[] = {"22222"};

        String excepcionEsperada = "SOCIO invalido; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andStubReturn(factoriastub);
        EasyMock.expect(factoriastub.getOperacionBO()).andStubReturn(operacionstub);

        //bucle for
        assertDoesNotThrow(() -> operacionstub.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andStubThrow(new SocioInvalidoException());

        EasyMock.replay(reservaTestable, factoriastub, operacionstub);

        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        assertEquals(excepcionEsperada, excepcionObtenida.getMessage());

    }

    @Test
    void C5_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"11111","22222","33333"};

        String excepcionEsperada = "ISBN invalido:11111; CONEXION invalida; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);

        //bucle for
        assertDoesNotThrow(() -> operacionstub.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());
        assertDoesNotThrow(() -> operacionstub.operacionReserva(socio, isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(() -> operacionstub.operacionReserva(socio, isbns[2]));
        EasyMock.expectLastCall().andStubThrow(new JDBCException());

        EasyMock.replay(reservaTestable, factoriastub, operacionstub);

        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        assertEquals(excepcionEsperada, excepcionObtenida.getMessage());

    }

}