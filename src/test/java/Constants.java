import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants {
    static String WEBSITE_URL = "https://buyme.co.il/";
    static final String PATHWAY_TO_AUXILIARY_FILES = "\\Auxiliary Test Files";
    static WebDriverWait WAIT;
    static String BROWSER_TYPE = "chrome";
    //C:\Users\Alex\Desktop\java\SecondProject\BuyMe-web-sanity-automation-master\Auxiliary Test Files

    static final By ENTRY_LINK = By.partialLinkText("כניסה");
    static final By REGISTRATION_LINK = By.cssSelector("span[class='header-link bold']");
    static final By NAME_FIELD = By.cssSelector("input[placeholder='שם פרטי']");
    static final By EMAIL_FIELD = By.cssSelector("input[placeholder='מייל']");
    static final By PASSWORD_FIELD = By.cssSelector("input[placeholder='סיסמה']");
    static final By PASSWORD_VALIDATION_FIELD = By.cssSelector("input[placeholder='אימות סיסמה']");
    static final By AGREEMENT_RADIO_BUTTON = By.cssSelector("label[for='register-consent']");
    static final By SPAM_RADIO_BUTTON = By.cssSelector("label[for='register-mailing']");
    static final By REGISTRATION_BLOCK_ID = By.cssSelector("form[class='ember-view']");
}
