package ppss.ejercicio1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorLlamadasTest {

    GestorLlamadasTestable gestorTestable;

    @BeforeEach
    void be() { gestorTestable = new GestorLlamadasTestable();}

    @Test
    void C1_calculaConsumo() {
        int minutos = 10, hora = 15;
        double resultadoEsperado = 208;

        gestorTestable.setHoraActual(hora);

        double resultadoObtenido = gestorTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    @Test
    void C2_calculaConsumo() {
        int minutos = 10, hora = 22;
        double resultadoEsperado = 105;

        gestorTestable.setHoraActual(hora);

        double resultadoObtenido = gestorTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}
