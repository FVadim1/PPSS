package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin2{

    WebDriver driver;
    CustomerLoginPage poCustomerLoginPage;
    HomePage poHomePage;
    MyAccountPage poMyAccountPage;

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
        poHomePage = new HomePage(driver);
        poCustomerLoginPage = new CustomerLoginPage(driver);
        poMyAccountPage = new MyAccountPage(driver);

        //PASO 1: Verificamos que el título de la página de inicio es el correcto
        assertEquals("Madison Island", poMyAccountPage.getTitle());

        //PASO 2: Seleccionamos account, y a continuación seleccionamos el hiperenlace login
        poHomePage.openLoginPage();

        //PASO 3: Verificamos que el título de la página es el correcto
        assertEquals("Customer Login", poMyAccountPage.getTitle());
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    void test_Login_InCorrect(){
        //PASO 4: Rellenamos los campos con el email de la cuenta que hemos creado con un password incorrecto
        poCustomerLoginPage.login("vf13@alu.ua.es", "vadymvadym_incorrecto");

        //PASO 5: Verificamos que nos aparece el mensaje de error
        assertEquals( "Invalid login or password.",poCustomerLoginPage.getMensajeIncorrecto());
    }
   @Test
    void test_Login_Correct(){
       //PASO 4: Rellenamos los campos con el email de la cuenta sin el password
           poCustomerLoginPage.loginSinPass("vf13@alu.ua.es");

       //PASO 5: Verificamos que nos aparece el mensaje debajo del campo que hemos dejado vacío
           assertEquals("This is a required field.", poCustomerLoginPage.getMensajeRequiredField());

       //PASO 6: Rellenamos el campo con la contraseña y volvemos a enviar los datos
           poCustomerLoginPage.login("vf13@alu.ua.es", "vadymvadym");

       //PASO 7: Verificamos que estamos en la página correcta usando su título
            assertEquals("My Account", poMyAccountPage.getTitle());
    }
}