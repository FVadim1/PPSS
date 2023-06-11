package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultipathExampleTest {

    MultipathExample mpexample;

    @BeforeEach
    void setup(){
        mpexample = new MultipathExample();
    }

    @Test /* ENTRA EN AMBOS IF */
    public void C1_multiPath1(){

        int a = 6;
        int b = 6;
        int c = 0;

        int resultadoEsperado = 12;
        int resultadoObtenido = mpexample.multiPath1(a,b,c);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }

    @Test /* NO ENTRA EN LOS IF */
    public void C2_multiPath1(){

        int a = 3;
        int b = 3;
        int c = 0;

        int resultadoEsperado = 0;
        int resultadoObtenido = mpexample.multiPath1(a,b,c);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }

    @Test
    public void C3_multiPath1(){

        int a = 3;
        int b = 6;
        int c = 2;

        int resultadoEsperado = 8;
        int resultadoObtenido = mpexample.multiPath1(a,b,c);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }



    @ParameterizedTest
    @MethodSource("multiPath2_args")
    public void multiPath2(int a, int b, int c, int resultadoEsperado){
        int resultadoObtenido = mpexample.multiPath2(a,b,c);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }

    private static Stream<Arguments> multiPath2_args(){
        return Stream.of(
                Arguments.of(5, 5, 5, 5), //No entra en ningún if
                Arguments.of(6, 4, 6, 16),//Primer if:(true && true) Segundo if: (true)
                Arguments.of(6, 5, 6, 11) //Primer if: (true && false) Segundo if: (true)
        );
    }

    @ParameterizedTest
    @MethodSource("multiPath3_args")
    public void multiPath3(int a, int b, int c, int resultadoEsperado){
        int resultadoObtenido = mpexample.multiPath3(a,b,c);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }

    private static Stream<Arguments> multiPath3_args(){
        return Stream.of(
                Arguments.of(5, 5, 5, 5), //No entra en ningún if
                Arguments.of(6, 4, 6, 16), //Primer if:(true && true) Segundo if: (true)
                Arguments.of(6, 5, 6, 11) //Primer if: (true && false) Segundo if: (true)
        );
    }
}
