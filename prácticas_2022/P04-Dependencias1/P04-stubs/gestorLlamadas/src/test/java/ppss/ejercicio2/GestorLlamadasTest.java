package ppss.ejercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    GestorLlamadasTestable gestorTestable;
    CalendarioStub cal;

    @BeforeEach
    void a() {
        gestorTestable = new GestorLlamadasTestable();
        cal = new CalendarioStub();
    }

    @Test
    void C1_calculaConsumo() {
        int minutos = 10, hora = 15;
        double resultadoEsperado = 208;

        cal.setHoraActual(hora);
        gestorTestable.setCalendario(cal);

        double resultadoObtenido = gestorTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    @Test
    void C2_calculaConsumo() {
        int minutos = 10, hora = 22;
        double resultadoEsperado = 105;

        cal.setHoraActual(hora);
        gestorTestable.setCalendario(cal);

        double resultadoObtenido = gestorTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}