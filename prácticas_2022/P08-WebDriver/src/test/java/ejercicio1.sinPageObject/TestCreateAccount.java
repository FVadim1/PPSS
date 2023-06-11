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
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestCreateAccount {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown(){
        //driver.close();
    }
    @Tag("OnlyOnce")
    @Test
    public void createAccount() {

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

        //PASO 4: Seleccionamos el botón "Create Account"
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a")).click();

        //PASO 5: Verificamos que estamos en la página correcta usando el título de la misma
        assertEquals(driver.getTitle(), "Create New Customer Account");

        //PASO 6: Rellenamos los campos con los datos de la cuenta excepto el campo "Confirmación" y enviamos
        //Relleno los datos
        driver.findElement(By.id("firstname")).sendKeys("vadym");
        driver.findElement(By.id("middlename")).sendKeys("vadym");
        driver.findElement(By.id("lastname")).sendKeys("vadym");
        driver.findElement(By.id("email_address")).sendKeys("vf13@alu.ua.es");
        driver.findElement(By.id("password")).sendKeys("vadymvadym");
        //driver.findElement(By.id("confirmation")).sendKeys("");
        //Envio los datos
        driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();

        //PASO 7: Verificamos que nos aparece el mensaje "This is a required field" debajo del campo vacío
        WebElement mensajeError = (driver.findElement(By.id("advice-required-entry-confirmation")));
        assertEquals("This is a required field.", mensajeError.getText());

        //PASO 8: Rellenamos el campo que nos falta y volvemos a enviar los datos del formulario.
        //Relleno el dato que falta
        driver.findElement(By.id("confirmation")).sendKeys("vadymvadym");
        //Envio los datos
        driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();

        //PASO 9: Verificamos que estamos en la página correcta usando su título
        assertEquals(driver.getTitle(), "My Account");
    }

    @Test
    public void createAccount_sinXpath(){

        //CONEXIÓN
        driver.get("http://demo-store.seleniumacademy.com/");

        //PASO 1: Verificamos que el título de la página de inicio es el correcto
        assertEquals(driver.getTitle(), "Madison Island");

        //PASO 2: Seleccionamos account, y a continuación seleccionamos el hiperenlace login
        //click en Account
        //driver.findElement(By.className("skip-account")).click(); //Nota: no se puede poner las 2 clases separados por espacio a la vez (webdriver no lo soporta)
        driver.findElement(By.cssSelector("a.skip-link.skip-account")).click(); // driver.findElement(By.cssSelector("skip-link.skip-account")).click(); tmb funciona
        //click en Account->Log in
        driver.findElement(By.linkText("Log In")).click(); //driver.findElement(By.partialLinkText("Log")).click(); tmb funciona

        //PASO 3: Verificamos que el título de la página es el correcto
        assertEquals(driver.getTitle(), "Customer Login");

        //PASO 4: Seleccionamos el botón "Create Account"
        driver.findElement(By.cssSelector("a.button")).click();

        //PASO 5: Verificamos que estamos en la página correcta usando el título de la misma
        assertEquals(driver.getTitle(), "Create New Customer Account");

        //PASO 6: Rellenamos los campos con los datos de la cuenta excepto el campo "Confirmación" y enviamos
        //Relleno los datos
        driver.findElement(By.id("firstname")).sendKeys("vadym");
        driver.findElement(By.id("middlename")).sendKeys("vadym");
        driver.findElement(By.id("lastname")).sendKeys("vadym");
        driver.findElement(By.id("email_address")).sendKeys("vf13@alu.ua.es");
        driver.findElement(By.id("password")).sendKeys("vadymvadym");
        //driver.findElement(By.id("confirmation")).sendKeys("vadymvadym");
        //Envio los datos
        driver.findElement(By.cssSelector("button.button")).click();

        //PASO 7: Verificamos que nos aparece el mensaje "This is a required field" debajo del campo vacío
        WebElement mensajeError = (driver.findElement(By.id("advice-required-entry-confirmation")));
        assertEquals("This is a required field.", mensajeError.getText());

        //PASO 8: Rellenamos el campo que nos falta y volvemos a enviar los datos del formulario.
        //Relleno el dato que falta
        //driver.findElement(By.id("confirmation")).sendKeys("vadymvadym");
        //Envio los datos
        //driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();

        //PASO 9: Verificamos que estamos en la página correcta usando su título
        //assertEquals(driver.getTitle(), "My Account");
    }


}
