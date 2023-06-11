import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.dao.DAOException;
import ppss.matriculacion.dao.FactoriaDAO;
import ppss.matriculacion.dao.IAlumnoDAO;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Integracion-fase1")
class AlumnoDAOIT {

    IAlumnoDAO alumnoDAO; //SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    void setUp() throws Exception {
        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new MiJdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "root", "ppss");
        //obtenemos la conexión con la BD
        IDatabaseConnection connection = databaseTester.getConnection();

        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
    }
    //addAlumno(AlumnoTO p)
    @Test
    void testA1() throws Exception {
        AlumnoTO p = new AlumnoTO();
        p.setNif("33333333C");
        p.setNombre("Elena Aguirre Juarez");
        p.setFechaNacimiento(LocalDate.of(1985,02,22));

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla3-init.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();
        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.addAlumno(p));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    
    }
    //addAlumno(AlumnoTO p)
    @Test
    void testA2() {
        AlumnoTO p = new AlumnoTO();
        p.setNif("11111111A");
        p.setNombre("Alfonso Ramirez Ruiz");
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        DAOException excepcionObtenida = assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(p));
        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    //addAlumno(AlumnoTO p)
    @Test
    void testA3() {
        AlumnoTO p = new AlumnoTO();
        p.setNif("44444444D");
        p.setNombre(null);
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        DAOException excepcionObtenida = assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(p));
        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    //addAlumno(AlumnoTO p)
    @Test
    void testA4() {
        AlumnoTO p = null;
        DAOException excepcionObtenida = assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(p));
        Assertions.assertEquals("Alumno nulo", excepcionObtenida.getMessage());
    }
    //addAlumno(AlumnoTO p)
    @Test
    void testA5() {
        AlumnoTO p = new AlumnoTO();
        p.setNif(null);
        p.setNombre("Pedro Garcia Lopez");
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        DAOException excepcionObtenida = assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(p));
        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    //void delAlumno(String nif)
    @Test
    void testB1() throws Exception {
        String nif =  "11111111A";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();
        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.delAlumno(nif));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);

    }
    //void delAlumno(String nif)
    @Test
    void testB2() {

        void metodo1(A , B, C , D){

            int cont = 0;

            if(A && B){
                cont+=1;
            }
            if(C || D){
                cont+=2;
            }else{
                cont+=3;
            }

        }
    }


    

}