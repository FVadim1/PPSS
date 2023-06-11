package ejercicio1.sinPageObject;
//http://demo-store.seleniumacademy.com/


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin {

    WebDriver driver;

    @BeforeEach
    public void setup(){

        driver = new ChromeDriver();
        //CONEXIÓN
        driver.get("http://demo-store.seleniumacademy.com/");

        //PASO 1: Verificamos que el título de la página de inicio es el correcto
            assertEquals(driver.getTitle(), "Madison Island");

        //PASO 2: Seleccionamos account, y a continuación seleccionamos el hiperenlace login
            //click en Account
            driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
            //click en Account->Log in
            driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a")).click();

        //PASO 3: Verificamos que el título de la página es el correcto
            assertEquals(driver.getTitle(), "Customer Login");
    }
    @AfterEach
    public void tearDown(){
        driver.close();
    }
    @Test
    public void loginOK() {

        //PASO 4: Rellenamos los campos con el email de la cuenta sin el password
            //Relleno los campos con un email
            driver.findElement(By.id("email")).sendKeys("vf13@alu.ua.es");
            //Envio el formulario
            driver.findElement(By.xpath("//*[@id=\"send2\"]")).click();

        //PASO 5: Verificamos que nos aparece el mensaje debajo del campo que hemos dejado vacío
            WebElement mensajeError = driver.findElement(By.id("advice-required-entry-pass"));
            assertEquals(mensajeError.getText(), "This is a required field.");

        //PASO 6: Rellenamos el campo con la contraseña y volvemos a enviar los datos
            //Relleno los campos con la contraseña
            driver.findElement(By.id("pass")).sendKeys("vadymvadym");
            //Envio el formulario
            driver.findElement(By.xpath("//*[@id=\"send2\"]")).click();

        //PASO 7: Verificamos que estamos en la página correcta usando su título
            assertEquals(driver.getTitle(), "My Account");


    }

   @Test
    public void loginFailed() {

        //PASO 4: Rellenamos los campos con el email de la cuenta que hemos creado con un password incorrecto
            //Relleno los campos con un password incorrecto
            driver.findElement(By.id("email")).sendKeys("vf13@alu.ua.es");
            driver.findElement(By.id("pass")).sendKeys("vadymvadym_incorrecto");
            //Envio el formulario
            driver.findElement(By.xpath("//*[@id=\"send2\"]")).click();

        //PASO 5: Verificamos que nos aparece el mensaje de error
            WebElement mensajeError = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li"));
            assertEquals(mensajeError.getText(), "Invalid login or password.");
    }
}