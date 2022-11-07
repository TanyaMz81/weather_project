import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TanyaMz81Test {

    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(5000);//to see that the browser was opened

        WebElement searchCityField = driver.findElement(By.xpath
                ("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(2000);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropDownMenu = driver.findElement(By.xpath(
                "//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']"));
        parisFRChoiceInDropDownMenu.click();
        Thread.sleep(2000);

        WebElement h2CityCountryHeader = driver.findElement(By.xpath("//div[@id='weather-widget']//h2"));
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//        TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли
// на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы
// OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testTheLinkOfThePageGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String titleExpectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchGuideButton = driver.findElement(By.xpath(
                "//div[@id='desktop-menu']//ul/li/a[@href='/guide']"));
        searchGuideButton.click();

        WebElement pageTitle = driver.findElement(By.xpath("//html/head/title"));
        String titleActualResult = driver.getTitle();
        Assert.assertEquals(titleActualResult, titleExpectedResult);

        String linkActualResult = driver.getCurrentUrl();
        String linkExpectedResult = "https://openweathermap.org/guide";
        Assert.assertEquals(linkActualResult, linkExpectedResult);

        driver.quit();

    }

    //        TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void testTheTemperatureFarenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement imperialButton = driver.findElement(By.xpath(
                "//div[@class = 'switch-container']/div[3]"));
        imperialButton.click();
        Thread.sleep(3000);

        WebElement farSign = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"));
        Thread.sleep(3000);
        // String far = farSign.getText();
        // String actualResult = far.substring((far.length()-2));

        String actualResult = farSign.getText();
        boolean bool1 = actualResult.endsWith("F");
        Assert.assertTrue(bool1);
        driver.quit();
    }


    //         TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies
// which are essential for the site to work. We also use non-essential cookies
// to help us improve our services. Any data collected is anonymised. You can
// allow all cookies or manage them individually.”
//3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
    @Test
    public void testCookiesMessage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);

        WebElement cookiesField = driver.findElement(By.xpath(
                "//div[@class='stick-footer-panel']/div/div/div/p"));
        String actualResult = cookiesField.getText();
        String expectedResult = "We use cookies which are essential for the site to work. We also "
                + "use non-essential cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";
        Assert.assertEquals(actualResult, expectedResult);

        WebElement allowAllButton = driver.findElement(By.xpath("//button[@type = 'button']"));
        String allowAllActualResult = allowAllButton.getText();
        String allowAllExpectedResult = "Allow all";
        Assert.assertEquals(allowAllActualResult, allowAllExpectedResult);
        Thread.sleep(3000);

        WebElement manageCookiesButton = driver.findElement(By.xpath("//a[@href='/cookies-settings']"));
        String manageCookiesActualResult = manageCookiesButton.getText();
        String manageCookiesExpectedResult = "Manage cookies";
        Assert.assertEquals(manageCookiesExpectedResult, manageCookiesActualResult);

        driver.quit();
    }

    //    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “
//    How to start” и “Ask a question”
    @Test
    public void testSubMenuLinks() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportButton = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportButton.click();
        Thread.sleep(3000);

        WebElement supportMenuItem1 = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li"));
        String supportMenuItem1ActualResult = supportMenuItem1.getText();
        String supportMenuItem1ExpectedResult = "FAQ";
        Assert.assertEquals(supportMenuItem1ActualResult, supportMenuItem1ExpectedResult);

        WebElement supportMenuItem2 = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[2]"));
        String supportMenuItem2ActualResult = supportMenuItem2.getText();
        String supportMenuItem2ExpectedResult = "How to start";
        Assert.assertEquals(supportMenuItem2ActualResult, supportMenuItem2ExpectedResult);

        WebElement supportMenuItem3 = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[3]"));
        String supportMenuItem3ActualResult = supportMenuItem3.getText();
        String supportMenuItem3ExpectedResult = "Ask a question";
        Assert.assertEquals(supportMenuItem3ActualResult, supportMenuItem3ExpectedResult);
        driver.quit();
    }

//
//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testAskAQuestionLink() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement supportButton = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportButton.click();

        WebElement askAQuestionLink = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a"));
        askAQuestionLink.click();
        driver.switchTo().window(driver.getWindowHandle());
//        for (String windowHandle : driver.getWindowHandles()) {
//            driver.switchTo().window(windowHandle);
//        }
        driver.get("https://home.openweathermap.org/questions");
        WebElement emailField = driver.findElement(By.id("question_form_email"));
        Thread.sleep(5000);
        emailField.click();
        emailField.sendKeys("tester@gmail.com");
        Thread.sleep(5000);

        WebElement subjectField = driver.findElement(By.xpath("//div/select[@name='question_form[subject]']"));
        subjectField.click();
        Thread.sleep(3000);

        WebElement optionSelected = driver.findElement(By.xpath("//select[@id='question_form_subject']/option[7]"));
        optionSelected.click();
        Thread.sleep(3000);

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        Thread.sleep(3000);
        messageField.sendKeys("Test testing tested");

        WebElement submitButton = driver.findElement(By.name("commit"));
        submitButton.click();

        WebElement captchaMessage = driver.findElement(By.xpath("//form[@id=\"new_question_form\"]/div[9]/div[1]/div"));
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String actualResult = captchaMessage.getText();
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//            4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testEmptyEmailField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportButton = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportButton.click();

        WebElement askAQuestionLink = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a"));
        askAQuestionLink.click();

        driver.switchTo().window(driver.getWindowHandle());

        driver.get("https://home.openweathermap.org/questions");

        WebElement subjectField = driver.findElement(By.xpath("//div/select[@name='question_form[subject]']"));
        subjectField.click();
        Thread.sleep(3000);

        WebElement optionSelected = driver.findElement(By.xpath("//select[@id='question_form_subject']/option[7]"));
        optionSelected.click();
        Thread.sleep(3000);

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        Thread.sleep(3000);
        messageField.sendKeys("Test testing tested");
        Thread.sleep(5000);

//        WebElement captchaCheckbox = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[4]"));
//        captchaCheckbox.click();
//        Thread.sleep(3000);
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='new_question_form']/div[9]/input"));
        submitButton.click();

        WebElement signName = driver.findElement(By.xpath("//div/span[@class='help-block']"));
        String actualResult = signName.getText();
        String expectedResult = "can't be blank";
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testDegreesChange() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement imperialButton = driver.findElement(By.xpath(
                "//div[@class = 'switch-container']/div[3]"));
        imperialButton.click();
        Thread.sleep(3000);

        WebElement farSign = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"));
        Thread.sleep(3000);
        String actualResult = farSign.getText();
        boolean bool1 = actualResult.endsWith("F");
        Assert.assertTrue(bool1);

        WebElement metricButton = driver.findElement(By.xpath(
                "//div[@class = 'switch-container']/div[2]"));
        metricButton.click();
        Thread.sleep(3000);

        WebElement celSign = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"));
        Thread.sleep(3000);
        String actualResult2 = celSign.getText();
        boolean bool2 = actualResult2.endsWith("C");
        Assert.assertTrue(bool2);
        driver.quit();
    }

//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testSiteInitialLink() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement companyLogo = driver.findElement(By.xpath("//nav[@id='nav-website']/ul/li/a/img"));
        companyLogo.click();
        Thread.sleep(5000);
        String actualResult = driver.getCurrentUrl();
        String expectedResult = url;
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testFindField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement weatherInYourCity = driver.findElement(By.xpath("//div[@id='desktop-menu']//input[@type='text']"));
        weatherInYourCity.click();
        Thread.sleep(3000);
        weatherInYourCity.sendKeys("Rome");
        weatherInYourCity.submit();
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        boolean actualResult = currentUrl.contains("Rome");
        Assert.assertTrue(actualResult);

        boolean actualResult2 = currentUrl.contains("find");
        Assert.assertTrue(actualResult2);

        WebElement nameInTheSearchField = driver.findElement(By.xpath("//div[@class='form-group']/input[@id='search_str']"));
        String actualResult3 = nameInTheSearchField.getAttribute("value");
        String expectedResult3 = "Rome";
        Assert.assertEquals(actualResult3, expectedResult3);
        driver.quit();

    }
//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void Find30OrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/tanya/Chromdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement apiMenu = driver.findElement(By.xpath("//div[@id='desktop-menu']//ul/li[2]/a[@href='/api']"));
        apiMenu.click();
        Thread.sleep(3000);

//        List<WebElement> links = driver.findElements(By.cssSelector("[class='btn_block orange round']"));
        List<WebElement> links = driver.findElements(By.xpath("//a[contains(@class, 'orange')]"));
        int actualResult = links.size();
        Assert.assertEquals(actualResult, expectedResult);

        System.out.println("The number of orange links is " + links.size());


        driver.close();
    }

}
//    @Test
//    public void test_name(){
//        System.setProperty("webdriver.chrome.driver", "/home/tanya/Downloads/chromedriver");
//        WebDriver driver = new ChromeDriver();



//    driver.quit();
//    }

