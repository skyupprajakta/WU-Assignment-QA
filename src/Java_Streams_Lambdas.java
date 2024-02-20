import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Java_Streams_Lambdas {
    public static void main(String[] args)  {

        System.setProperty("web driver.chrome.driver", "D:/LTIM/Chrome");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");

            List<WebElement> AllLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + AllLinks.size());

        System.out.println("List Of Links Using For Each Loop:");

            for (WebElement link : AllLinks) {
                System.out.println(link.getAttribute("href"));
            }


        System.out.println("List Of Links Using Java Lambdas and Streams:");

            AllLinks.stream()
                        .map(link -> link.getAttribute("href"))
                        .forEach(System.out::println);

        System.out.println("List Of Links Using Parallel Streams:");

            AllLinks.stream()
                        .map(link -> link.getAttribute("href"))
                        .forEach(System.out::println);


        driver.quit();

    }
}