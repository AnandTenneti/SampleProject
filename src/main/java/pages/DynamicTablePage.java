package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DynamicTablePage {
    WebDriver driver;

    public DynamicTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@aria-label='Tasks']/div[3]/div")
    private List<WebElement> rows;

    @FindBy(xpath = "//span[@role='columnheader']")////div[@aria-label='Tasks']/div[@role
    // ='rowgroup']
    private List<WebElement> columns;

    @FindBy(xpath = "//span[@role='cell']")////div[@aria-label='Tasks']/div[@role='rowgroup']
    private List<WebElement> cellValues;

    @FindBy(css = "p.bg-warning")
    private WebElement messageInLabel;


    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return columns.size();
    }

    public String getLabelInMessage() {
        return messageInLabel.getText();
    }

    public String getValues(String taskName) {
        for (int i = 1; i <= getRowCount(); i++) {
            for (int j = 1; j <= getColumnCount(); j++) {
                String value = driver.findElement(
                                By.xpath("//div[@role='rowgroup'][2]/div[@role='row'][" + i + "]" +
                                        "//span[@role='cell'][" + j + "]"))
                        .getText();
                if (value.equalsIgnoreCase(taskName)) {

                    String cpuValue = driver.findElement(
                            By.xpath("//div[@role='rowgroup'][2]/div[@role='row'][" + i + "]" +
                                    "//span[@role='cell'][" + j + "]//following-sibling" +
                                    "::span[contains(text(),'%')]")).getText();
                    System.out.println(value + ":" + cpuValue);
                    return cpuValue;
                }
            }

        }
        return null;
//    public void getCellValue() {
////        List<WebElement> rows = driver.findElements(
////                By.xpath("//div[@role='table']//div[@role" +
////                        "='rowgroup'][2]/div[@role='row']"));
////        for (WebElement row : rows){
////            List<WebElement> cells = row.findElements(By.xpath("//span[@role='cell']"));
////        for (WebElement cell : cells) {
////            System.out.println(cell.getText());
////        }
//
//
//        }


//        for (WebElement cellValue : cellValues) {
//            if (cellValue.getText().equalsIgnoreCase(taskName)) {
//                System.out.println(cellValue.getText());
//                cellValue.
//                return cellValue.findElement(By.xpath
//                        ("//following-sibling::span[contains" +
//                                "(text(),'%')]")).getText();
//            }
//        }
//        return null;
    }

//    public void getValue() {
////        int rowCount = getRowCount();
////        int columnCount = getColumnCount();
////        for (int i = 1; i <= rowCount - 1; i++) {
////            for (int j = 1; j <= columnCount - 1; j++) {
////                String value = driver.findElement(By.xpath("//div[@aria-label='Tasks']/div" +
////                        "[@role='rowgroup']/div[\" + i + \"]//span[@role='cell'][\" + j + " +
////                        "\"]//following-sibling::span[contains(text(),'%')]")).getText();
////                System.out.println("Value of i is" + i);
////                System.out.println("Value of j is" + j);
////                System.out.println("Value is" + value);
//////                if (value.equalsIgnoreCase("Chrome")) {
//////                    System.out.print(value + "\t" + value + "\t");
//////                    break;
//////                }
////            }
////            System.out.println();
////        }
//
//    }

}


//  List<WebElement> rowElements = driver.findElements(By.xpath
//                ("//div[@aria-label='Tasks']/div[3]/div"));
//        int rowsCount =
//                driver.findElements(By.xpath("//div[@aria-label='Tasks']/div[@role='rowgroup']/div")).size();
//        System.out.println("The row count is " + rowsCount);
//        List<WebElement> columnElements = driver.findElements(By.xpath
//                ("//div[@aria-label='Tasks']/div[@role='rowgroup']//span[@role='columnheader']"));
//        int columnCount =
//                driver.findElements(By.xpath("//div[@aria-label='Tasks']/div[@role='rowgroup']//span[@role='columnheader']")).size();
//        System.out.println("The column   count is " + columnCount);
//
//        for (int i = 1; i <= rowsCount - 1; i++) {
//            // System.out.println(driver.findElement(By.xpath
//            // ("//div[@aria-label='Tasks']/div[@role='rowgroup']/div[\" + i +\n" +
//            //          "                                                \"]")).getText());
//
//            for (int j = 1; j <= columnCount; j++) {
//                String value =
//                        driver.findElement(
//                                By.xpath
//                                        ("//div[@aria-label='Tasks']/div[@role='rowgroup']/div[" + i + "]//span[@role='cell'][" + j + "]")).getText();
//
//                String cpuValue = driver.findElement(By.xpath("//div[@aria-label='Tasks']/div" +
//                        "[@role='rowgroup']/div[\" + i + \"]//span[@role='cell'][\" + j + " +
//                        "\"]//following-sibling::span[contains(text(),'%')]")).getText();
//                if (value.equalsIgnoreCase("Chrome")) {
//                    System.out.print(value + "\t" + cpuValue + "\t");
//                    break;
//                }
//            }
//            System.out.println();
//        }
//    }



