import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class hashmap_assignment {
        public static HashMap<String, String> sharePrizeValueFromWeb;
        public static HashMap<String, String> sharePrizeValueFromExcel;


        public static void main(String[] args) throws IOException, InterruptedException {
            sharePrizeValueFromWeb = DataFromWeb();
            sharePrizeValueFromExcel = DataFromExcel();

            String expected = sharePrizeValueFromExcel.get("Angel Fibers");
            String actual = sharePrizeValueFromWeb.get("Angel Fibers");
            //To validate data from different sources
            System.out.println("......Test data validation with actual result.....");
            if (actual.equals(expected)) {
                System.out.println("TC PASS");
            } else {
                System.out.println("TC FAIL");
            }

            //To Compare all data from excelsheet

            System.out.println("......Comparison of data.....excel sheet and webtable......");
            Set<String> ListSet = sharePrizeValueFromExcel.keySet();
            for (String key : ListSet) {

                System.out.println(key);
                if (sharePrizeValueFromExcel.get(key).equals(sharePrizeValueFromWeb.get(key))) {
                    System.out.println("Expected and Actual values are same, Pass for the: " + key);
                } else {
                    System.out.println("Expected and Actual values are not same, Fail for the: " + key);
                }
            }

        }

        public static HashMap<String, String> DataFromWeb() throws InterruptedException {

            System.setProperty("web driver. chrome. driver", "D:\\LTIM\\Chrome\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://money.rediff.com/losers/bse/daily/groupall");
            driver.manage().window().maximize();
            Thread.sleep(2000);
            List<WebElement> companyName = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
            List<WebElement> currentPrize = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
            System.out.println("Size of list :" + currentPrize.size());
            driver.close();

            HashMap<String, String> sharePrizeValueFromWeb = new HashMap<>();
            for (int i = 0; i < 20; i++) {

                System.out.println("key is: " + companyName.get(i).getText().trim() + " " + "Value is: " + currentPrize.get(i).getText().trim());
                sharePrizeValueFromWeb.put(companyName.get(i).getText().trim(), currentPrize.get(i).getText().trim());
            }
            return sharePrizeValueFromWeb;
        }

        public static HashMap<String,String> DataFromExcel() throws IOException {

            HashMap<String,String> sharePrizeValueFromExcel= new HashMap<>();

            FileInputStream file = new FileInputStream("D:\\LTIM\\Test Data\\TestData_WU_Assignment.xlsx");

            Workbook book = WorkbookFactory.create(file);
            Sheet sh= book.getSheet("Sheet 1");
            System.out.println("...........Data from Excel sheet........");
            for(Row rows:sh){

                String key= rows.getCell(0).getStringCellValue();
                String value = rows.getCell(1).getStringCellValue();

                sharePrizeValueFromExcel.put(key,value);


                System.out.println("Key is: "+ key + " " + "Value is: "+ value);

            }
            return sharePrizeValueFromExcel;

        }
    }

