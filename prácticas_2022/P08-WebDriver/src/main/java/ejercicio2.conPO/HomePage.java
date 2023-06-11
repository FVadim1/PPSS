package ejercicio2.conPO;
//http://demo-store.seleniumacademy.com/
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage{
    WebDriver driver;
    WebElement Icono_Account;
    WebElement Menu_LogIn;

    public HomePage(WebDriver driver){
        this.driver = driver;
        //CONEXIÓN
        driver.get("http://demo-store.seleniumacademy.com/");
            //Account
        Icono_Account = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a"));
        //Account->Log in
        Menu_LogIn = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
    }
    public WebElement getIcono_Account(){
        return Icono_Account;
    }
    public WebElement getMenu_LogIn(){
        return Menu_LogIn;
    }
    public void openLoginPage(){
        getIcono_Account().click();  //click en Account
        getMenu_LogIn().click(); //click en Account->Log in
    }
}