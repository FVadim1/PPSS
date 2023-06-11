package ejercicio2.conPO;
//http://demo-store.seleniumacademy.com/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerLoginPage{
    WebDriver driver;
    String mensajeIncorrecto;
    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password){
        //Limpio por si está relleno
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("pass")).clear();
        //Relleno los campos
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).submit();
    }

    public void loginSinPass(String email){
        //Limpio por si está relleno
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("pass")).clear();
        //Relleno los campos
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("send2")).submit();
        driver.findElement(By.xpath("//*[@id=\"advice-required-entry-pass\"]")).getText();

    }
    public String getMensajeIncorrecto(){
        return driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li/span")).getText();
    }

    public String getMensajeRequiredField(){
        return driver.findElement(By.xpath("//*[@id=\"advice-required-entry-pass\"]")).getText();
    }

}