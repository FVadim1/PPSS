package ppss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DataArrayTest {

    @Test
    void deleteC1() throws DataException {

        int elementoABorrar = 5;
        int[] arrayEntrada = new int[]{1,3,5,7};
        int[] arrayEsperado = new int[]{1,3,7};

        DataArray da = new DataArray(arrayEntrada);
        da.delete(elementoABorrar);
        int[] arrayObtenido = da.getColeccion();

        assertArrayEquals(arrayEsperado,arrayObtenido);
    }

    @Test
    void deleteC2() throws DataException {

        int elementoABorrar = 3;
        int[] arrayEntrada = new int[]{1,3,3,5,7};
        int[] arrayEsperado = new int[]{1,3,5,7};

        DataArray da = new DataArray(arrayEntrada);
        da.delete(elementoABorrar);
        int[] arrayObtenido = da.getColeccion();

        assertArrayEquals(arrayEsperado,arrayObtenido);
    }

    @Test
    void deleteC3() throws DataException {

        int elementoABorrar = 4;
        int[] arrayEntrada = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] arrayEsperado = new int[]{1,2,3,5,6,7,8,9,10};

        DataArray da = new DataArray(arrayEntrada);
        da.delete(elementoABorrar);
        int[] arrayObtenido = da.getColeccion();

        assertArrayEquals(arrayEsperado,arrayObtenido);
    }

    @Test
    void deleteC4(){

        DataException excepcionObtenida;
        String mensajeExcepcionEsperado = "No hay elementos en la colección";
        int elementoABorrar = 8;
        int[] arrayEntrada = new int[]{};
        DataArray da = new DataArray(arrayEntrada);

        excepcionObtenida = assertThrows(DataException.class, () -> da.delete(elementoABorrar));
        assertEquals( mensajeExcepcionEsperado , excepcionObtenida.getMessage());
    }

    @Test
    void deleteC5(){

        DataException excepcionObtenida;
        String mensajeExcepcionEsperado = "El valor a borrar debe ser > 0";
        int elementoABorrar = -5;
        int[] arrayEntrada = new int[]{1,3,5,7};
        DataArray da = new DataArray(arrayEntrada);

        excepcionObtenida = assertThrows(DataException.class, () -> da.delete(elementoABorrar));
        assertEquals( mensajeExcepcionEsperado , excepcionObtenida.getMessage());
    }

    @Test
    void deleteC6(){

        DataException excepcionObtenida;
        String mensajeExcepcionEsperado = "Colección vacía. Y el valor a borrar debe ser > 0";
        int elementoABorrar = 0;
        int[] arrayEntrada = new int[]{};
        DataArray da = new DataArray(arrayEntrada);

        excepcionObtenida = assertThrows(DataException.class, () -> da.delete(elementoABorrar));
        assertEquals( mensajeExcepcionEsperado , excepcionObtenida.getMessage());
    }

    @Test
    void deleteC7(){

        DataException excepcionObtenida;
        String mensajeExcepcionEsperado = "Elemento no encontrado";
        int elementoABorrar = 8;
        int[] arrayEntrada = new int[]{1,3,5,7};
        DataArray da = new DataArray(arrayEntrada);

        excepcionObtenida = assertThrows(DataException.class, () -> da.delete(elementoABorrar));
        assertEquals( mensajeExcepcionEsperado , excepcionObtenida.getMessage());
    }

    @ParameterizedTest
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @MethodSource("casosDePrueba_Tabla_D_4a7")
    void testParametrizadoC8(int[] arrayEntrada, int elementoABorrar, String mensajeExcepcionEsperado){

        DataException excepcionObtenida;
        DataArray da = new DataArray(arrayEntrada);

        excepcionObtenida = assertThrows(DataException.class, () -> da.delete(elementoABorrar));
        assertEquals( mensajeExcepcionEsperado , excepcionObtenida.getMessage());

    }
    private static Stream<Arguments> casosDePrueba_Tabla_D_4a7(){
        return Stream.of(
                Arguments.of(new int[]{}, 8, "No hay elementos en la colección"), //C4
                Arguments.of(new int[]{1,3,5,7}, -5, "El valor a borrar debe ser > 0"), //C5
                Arguments.of(new int[]{}, 0, "Colección vacía. Y el valor a borrar debe ser > 0"), //C6
                Arguments.of(new int[]{1,3,5,7}, 8, "Elemento no encontrado")  //C7
        );
    }

    @ParameterizedTest
    @Tag("parametrizado")
    @MethodSource("casosDePrueba_Tabla_D_1a3")
    void testParametrizadoC9(int[] arrayEntrada, int elementoABorrar, int[] arrayEsperado ) throws DataException {

        DataArray da = new DataArray(arrayEntrada);
        da.delete(elementoABorrar);
        int[] arrayObtenido = da.getColeccion();

        assertArrayEquals(arrayEsperado,arrayObtenido);
    }
    private static Stream<Arguments> casosDePrueba_Tabla_D_1a3(){
        return Stream.of(
                Arguments.of(new int[]{1,3,5,7}, 5, new int[]{1,3,7}), //C1
                Arguments.of(new int[]{1,3,3,5,7}, 3,new int[]{1,3,5,7} ), //C2
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 4, new int[]{1,2,3,5,6,7,8,9,10}) //C3
        );
    }

}