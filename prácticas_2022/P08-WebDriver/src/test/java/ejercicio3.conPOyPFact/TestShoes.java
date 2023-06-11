package ejercicio3.conPOyPFact;
//http://demo-store.seleniumacademy.com/


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestShoes{

    WebDriver driver;
    Shoes poShoes;

    @BeforeAll
    static void setupCookies() {
        Cookies.storeCookiesToFile("vf13@alu.ua.es", "vadymvadym");
    }

    @BeforeEach
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        chromeOptions.setHeadless(headless);

        driver = new ChromeDriver(chromeOptions);
        Cookies.loadCookiesFromFile(driver);
        poShoes = PageFactory.initElements(driver, Shoes.class);
    }

    @AfterEach
    public void tearDown(){
      driver.close();
    }

    @Test
    void compareShoes() {
        //PASO 1: Verificamos que el título de la página es el correcto
            assertEquals("My Account", poShoes.getTitle());

        //PASO2: Seleccionamos Accesories -> Shoes
            poShoes.entrarEnShoes();

        //PASO 3: Verificamos que el título de la página es el correcto
            assertEquals("Shoes - Accessories", poShoes.getTitle());

        //PASO 4: Seleccionamos dos zapatos para compararlos (pulsando sobre "Add to Compare". En concreto queremos seleccionar los dos últimos)
            poShoes.selectShoesToCompare(5);
            poShoes.selectShoesToCompare(6);

        //PASO 5: Seleccionamos el botón "COMPARE"
            poShoes.compareSelectedShoes();

        //PASO 6: Verificamos que estamos en la página correcta usando el títuo "Products Comparison List"
            poShoes.cambiar_a_Ventana_Compare();
            assertEquals("Products Comparison List - Magento Commerce", poShoes.getTitle());

        //PASO 7: Cerramos la ventana con la comparativa de productos
            poShoes.cerrarVentanaComparacion();

        //PASO 8: Verificamos que estamos de nuevo en la ventana "Shoes - Accesories"
            poShoes.cambiar_a_Ventana_Principal();
            assertEquals("Shoes - Accessories", poShoes.getTitle());

        //PASO 9: click en "clear all"
            String mensajeAlert = poShoes.clearAll();

        //PASO 10: Verificamos que en la página aparece el mensaje "the comparison list was cleared"
            assertEquals("Are you sure you would like to remove all products from your comparison?", mensajeAlert);
    }
}