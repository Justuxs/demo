package com.example.demo;

import com.beust.jcommander.internal.Console;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.constant.Constable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
//chrome.exe -remote-debugging-port=9222 -user-data-dir=C:\chromeData
    public static String maxPrice= "20000";
    public static String marginXpath="/html/body/cw-root/mat-sidenav-container/mat-sidenav-content/div/cw-withdraw/cw-withdraw-search-grid/form/mat-form-field[2]/div/div[1]/div[3]";
    public static String margin0Xpath="/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span";

    public static String maxPriceXpath="/html/body/cw-root/mat-sidenav-container/mat-sidenav-content/div/cw-withdraw/cw-withdraw-search-grid/form/mat-form-field[4]/div/div[1]/div[3]/input";

    public static String containerGrid="//div[@class=\"grid ng-star-inserted\"]";

    public static String containerItems="//div[@class=\"grid ng-star-inserted\"]/div";

    public static String containerName= "//div[@class=\"grid ng-star-inserted\"]/div//*[contains(@class, 'brand')]";


    public static String[] gun= {"Glock-18","Nova","MAC-10","Galil AR","SSG","M249","P2000","XM1014","MP9","FAMAS","AWP","Negev","USP-S","MAG-7","UMP-45","AK-47","G3SG1",
            "P250","Sawed-Off","MP7","M4A4","SCAR-20","Dual Berettas","PP-Bizon","M4A1-S","Tec-9","P90","SG 553","Five-SeveN","AUG","CZ75-Auto",
            "Desert Eagle","R8 Revolver"};

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pegas\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions opt=new ChromeOptions();

        opt.setExperimentalOption("debuggerAddress","localhost:9222 ");

        driver=new ChromeDriver(opt);

        driver.get("https://www.csgoroll.com/en/withdraw/csgo/p2p");
        Thread.sleep(2000);


        //prepair();

        waiter(containerGrid," itemu gridas");

        try{
            Selectinimas();
        }
        catch (Exception ex){
            Selectinimas();
        }


}


    public static void Selectinimas() throws InterruptedException {
        while(true){
            Thread.sleep(1000);


            List<WebElement> elem= driver.findElements(By.xpath(/*containerItems*/ containerName));

            for (WebElement item : elem
            ) {

                try{
                    if(isItem(item.getText())) item.click();
                }
                catch (Exception ex){
                    Selectinimas();
                }


            }
            System.out.println("Rasta itemu -> "+elem.size());

        }
    }

    public static void waiter(String xptah, String name){
        System.out.println("Laukiu --> "+name);
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xptah)));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xptah)));
        System.out.println("Rasta");
    }

    public static void click(String xptah) throws InterruptedException {
        driver.findElement(By.xpath(xptah)).click();
    }

    public static void send(String xptah,String value){
        driver.findElement(By.xpath(xptah)).sendKeys(value);
    }

    public static void prepair() throws InterruptedException {
        System.out.println("Laukiu margin mygtuko");

        waiter(marginXpath,"margin");

        click(marginXpath);

        waiter(margin0Xpath,"0 pasirinkimo");

        click(margin0Xpath);

        waiter(maxPriceXpath,"Max price");

        send(maxPriceXpath,maxPrice);

    }
    public static boolean isItem(String name){
        for (String item: gun) {
            if(name.toLowerCase().contains(item.toLowerCase())){
                System.out.println("Atitinka ginklu sarasa -> "+name);
                return true;
            }
        }
        System.out.println("Neatitiko ginklu saraso -> "+name);

        return false;
    }


    public static void main12(String[] args) {
        ChromeDriver driver=new ChromeDriver();

// getCapabilities will return all browser capabilities
        Capabilities cap=driver.getCapabilities();

// asMap method will return all capability in MAP
        Map<String, Object> myCap=cap.asMap();

// print the map data-
        System.out.println(myCap);
    }



}
