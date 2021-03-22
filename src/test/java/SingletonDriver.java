import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SingletonDriver {
    private static WebDriver driver;

    public static WebDriver getDriverInstance() throws Exception {
        String type = getData("browserType");
        if (driver == null && type.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\Alex\\AppData\\Local\\Google\\Chrome\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(type.equals("FF")) {
            System.setProperty("webdriver.firefox.driver", "C:\\Users\\Alex\\AppData\\Local\\Mozilla\\Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private static String getData(String keyName) throws Exception {
        ClassLoader classLoader = SingletonDriver.class.getClassLoader();
        String xmlFilePath = String.valueOf(new File(classLoader.getResource("data.xml").getFile()));
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
}
