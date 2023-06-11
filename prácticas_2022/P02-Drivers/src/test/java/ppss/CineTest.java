package ppss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class CineTest {

    @Test
    void reservaButacasC1(){

        int solicitados = 3;
        boolean[] asientos = new boolean[]{};
        boolean[] arrayEsperado = new boolean[]{};
        Cine c = new Cine();
        boolean boolObtenido = c.reservaButacasV1(asientos,solicitados);
        boolean[] arrayObtenido = c.asientos;


        assertAll("",
                ()->assertFalse(boolObtenido),
                ()->assertArrayEquals(arrayEsperado,arrayObtenido));
    }

    @Test
    void reservaButacasC2(){

        int solicitados = 0;
        boolean[] asientos = new boolean[]{};
        boolean[] arrayEsperado = new boolean[]{};
        Cine c = new Cine();
        boolean boolObtenido = c.reservaButacasV1(asientos,solicitados);
        boolean[] arrayObtenido = c.asientos;


        assertAll("",
                ()->assertFalse(boolObtenido),
                ()->assertArrayEquals(arrayEsperado,arrayObtenido));
    }

    @Test
    void reservaButacasC3(){

        int solicitados = 2;
        boolean[] asientos = new boolean[]{false,false,false,true,true};
        boolean[] arrayEsperado = new boolean[]{true,true,false,true,true};
        Cine c = new Cine();
        boolean boolObtenido = c.reservaButacasV1(asientos,solicitados);
        boolean[] arrayObtenido = c.asientos;


        assertAll("",
                ()->assertTrue(boolObtenido),
                ()->assertArrayEquals(arrayEsperado,arrayObtenido));
    }

    @Test
    void reservaButacasC4(){

        int solicitados = 1;
        boolean[] asientos = new boolean[]{true,true,true};
        boolean[] arrayEsperado = new boolean[]{true,true,true};
        Cine c = new Cine();
        boolean boolObtenido = c.reservaButacasV1(asientos,solicitados);
        boolean[] arrayObtenido = c.asientos;

        assertAll("",
                ()->assertFalse(boolObtenido),
                ()->assertArrayEquals(arrayEsperado,arrayObtenido));
    }



    @ParameterizedTest
    @Tag("parametrizado")
    @DisplayName("[C5] Test parametrizado de la tabla A de CineTest: ReservaButacas")
    @MethodSource("casosDePrueba_Tabla_A")
    void reservaButacasC5(boolean[] asientos, int solicitados, boolean[] arrayEsperado, boolean boolEsperado){

        Cine c = new Cine();
        c.reservaButacasV1(asientos,solicitados);
        boolean[] arrayObtenido = c.asientos;
        boolean boolObtenido = c.reservaButacasV1(asientos,solicitados);

        assertAll("",
                ()->assertEquals(boolEsperado, boolObtenido),
                ()->assertArrayEquals(arrayEsperado,arrayObtenido));
    }
    private static Stream<Arguments> casosDePrueba_Tabla_A(){
        return Stream.of(
                Arguments.of(new boolean[]{}, 3, new boolean[]{}, false), //C1
                Arguments.of(new boolean[]{}, 0, new boolean[]{}, false), //C2
                Arguments.of(new boolean[]{false,false,false,true,true}, 2, new boolean[]{true,true,false,true,true}, true), //C3
                Arguments.of(new boolean[]{true,true,true}, 1, new boolean[]{true,true,true}, false)  //C4
        );
    }
}

