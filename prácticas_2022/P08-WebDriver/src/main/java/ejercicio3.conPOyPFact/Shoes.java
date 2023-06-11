package ejercicio3.conPOyPFact;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class Shoes {

    WebDriver driver;


    public Shoes(WebDriver driver){

        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account");
    }


    public void entrarEnShoes(){
        //Muevo el ratón hasta Accesories para que se desplegue el menú (sin hacer click)
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[3]/a")));
        builder.perform();
        //Una vez desplegado el menú al hacer "hover" sobre accesories le doy click en shoes
        driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[3]/ul/li[4]/a")).click();
    }

    void selectShoesToCompare(int n){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch(n){
            case 5:
                WebElement WingTipShoe_li = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]"));
                jse.executeScript("arguments[0].scrollIntoView();", WingTipShoe_li);
                WebElement WingTipShoe_addCompare = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]/div/div[2]/ul/li[2]/a"));
                WingTipShoe_addCompare.click();
                break;

            case 6:

                WebElement SuedeShoe_li = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]"));
                jse.executeScript("arguments[0].scrollIntoView();", SuedeShoe_li);
                WebElement SuedeShoe_addCompare = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]/div/div[2]/ul/li[2]/a"));
                SuedeShoe_addCompare.click();
                break;
        }
    }

    public void compareSelectedShoes(){
        WebElement boton_div = driver.findElement(By.id("compare-items"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", boton_div);

        //Nota: El botón deseparece si cambiamos el tamaño de la ventana
        WebElement boton_Compare = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div/button"));
        boton_Compare.click();
    }

    public void cambiar_a_Ventana_Compare() {
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        driver.switchTo().window(handleIds[1]);
    }

    public void cambiar_a_Ventana_Principal() {
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        driver.switchTo().window(handleIds[0]); //Switch to first tab
        driver.switchTo().defaultContent(); //Switch to default frame
    }
    
    public String clearAll(){
        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div/a")).click();
        Alert alert = driver.switchTo().alert();
        String mensajeAlert = alert.getText();
        alert.accept();
        //NOTA: alert.dismiss() tambíen se puede usar , o alert.sendKeys("algo") si aparece para teclear
        return mensajeAlert;
    }
    
    public void cerrarVentanaComparacion(){
        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[3]/button")).click();
    }

    public String getTitle(){
        return driver.getTitle();
    }


}
