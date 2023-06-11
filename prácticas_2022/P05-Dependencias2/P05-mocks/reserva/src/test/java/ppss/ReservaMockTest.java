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


class ReservaMockTest {

    Reserva reservaTestable;
    IOperacionBO operacionmock;
    IMocksControl ctrl;
    FactoriaBOs factoriamock;

    @BeforeEach
    void a(){
        //se crean los dobles mock de las dos dependencias que queremos controlar
        ctrl = EasyMock.createStrictControl();
        operacionmock = ctrl.createMock(IOperacionBO.class);
        factoriamock = ctrl.createMock(FactoriaBOs.class);
        reservaTestable  = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos", "getFactoriaBOS").createMock(ctrl);
    }

    @Test
    void C1_realizaReserva() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String isbns[] = {"22222"};

        String excepcionEsperada = "ERROR de permisos; ";

        //
        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(false);
        ctrl.replay();
        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        ctrl.verify();

        assertEquals(excepcionEsperada, excepcionObtenida.getMessage());
    }

    @Test
    void C2_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"22222","33333"};

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andReturn(factoriamock);
        EasyMock.expect(factoriamock.getOperacionBO()).andReturn(operacionmock);

        //bucle for
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[1]));
        EasyMock.expectLastCall().andVoid();

        ctrl.replay();
        assertDoesNotThrow(() -> reservaTestable.realizaReserva(login, password, socio, isbns));
        ctrl.verify();
    }

    @Test
    void C3_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"11111"};

        ReservaException excepcionEsperada = new ReservaException("ISBN invalido:11111; ");

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andReturn(factoriamock);
        EasyMock.expect(factoriamock.getOperacionBO()).andReturn(operacionmock);

        //bucle for
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        ctrl.replay();
        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));

        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
        ctrl.verify();

    }
    @Test
    void C4_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String isbns[] = {"22222"};

        ReservaException excepcionEsperada = new ReservaException("SOCIO invalido; ");

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andReturn(factoriamock);
        EasyMock.expect(factoriamock.getOperacionBO()).andReturn(operacionmock);

        //bucle for
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        ctrl.replay();
        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));

        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
        ctrl.verify();

    }

    @Test
    void C5_realizaReserva() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String isbns[] = {"11111","22222","33333"};

        ReservaException excepcionEsperada = new ReservaException("ISBN invalido:11111; CONEXION invalida; ");

        EasyMock.expect(reservaTestable.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(reservaTestable.getFactoriaBOS()).andReturn(factoriamock);
        EasyMock.expect(factoriamock.getOperacionBO()).andReturn(operacionmock);

        //bucle for
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(() -> operacionmock.operacionReserva(socio, isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());

        ctrl.replay();
        ReservaException excepcionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login, password, socio, isbns));
        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
        ctrl.verify();

    }

}