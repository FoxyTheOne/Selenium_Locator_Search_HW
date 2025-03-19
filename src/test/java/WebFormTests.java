import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для тестирования страницы bonigarcia.dev/selenium-webdriver-java/web-form.html
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebFormTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @BeforeAll
    void setUpAll() {
        if (System.getProperty("local").equals("true")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        }
        // У нас здесь достаточно простые тесты, нет необходимости очищать и открывать новый браузер перед каждым из них
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    void tearDown() {
        driver.getPageSource();
        driver.quit();
    }

    @BeforeEach
    void setup() {
        driver.get(BASE_URL);
    }

    /**
     * Тест для проверки заголовка - тот ли сайт мы открыли
     */
    @Test
    void openHomePageTest() {
        String actualTitle = driver.getTitle();

        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    }

    /**
     * Просто найдем все локаторы на этой странице
     */
    // Лучше каждый локатор содержать в отдельном тесте, потому что если он сломан, упадёт весь тест
    @Test
    void locatorSearch() {
        // Best practise - Либо id либо name и тд
        // Варианты: .display-4 , h1.display-4 , //h1[@class='display-4'] . Лучше h1.display-4
        WebElement actualMainTitle = driver.findElement(By.className("display-4"));
        WebElement actualMainSubtitle = driver.findElement(By.tagName("h5"));
        // Варианты: .img-fluid , //img[@class='img-fluid']
        WebElement titlePicture = driver.findElement(By.className("img-fluid")); // Здесь лучше привязаться не так, а к src. Есть вероятность, что этот класс переиспользуется и там поставят другую картинку (не обязательно искать по полному названию файла)

        // Dividing line: .col.col-12 , //[@class='col col-12')] . Лучше //div[contains(@class, 'col-12')]
        WebElement greyLine = driver.findElement(By.className("my-4"));

        // display-6 , h1.display-6
        WebElement actualPageTitle = driver.findElement(By.className("display-6"));

        // #my-text-id , //[@id='my-text-id']
        WebElement textInputText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(1) label:nth-child(1)"));
        WebElement textInputForm = driver.findElement(By.id("my-text-id"));
        // [name='my-password'] , //[@name='my-password']
        WebElement passwordText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(1) label:nth-child(2)"));
        WebElement passwordForm = driver.findElement(By.name("my-password"));
        WebElement textareaText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(1) label:nth-child(3)"));
        WebElement textareaForm = driver.findElement(By.name("my-textarea"));
        WebElement disabledInputText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(1) label:nth-child(4)"));
        WebElement disabledInputForm = driver.findElement(By.name("my-disabled"));
        WebElement readonlyInputText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(1) label:nth-child(5)"));
        WebElement readonlyInputForm = driver.findElement(By.name("my-readonly"));
        // [href='./index.html'] , a[href='./index.html'] , //a[contains(@href, 'index')]
        WebElement returnToIndexUrl = driver.findElement(By.cssSelector("[href$='index.html']"));

        // Функции text() и string: $x("string(//select[@name='my-select']/ancestor::label)") . Попробовать text()[1] . Пример: //@label[@class='form-label w-100' and normalize-space(text()[1])='Date picker']
        WebElement dropdownSelectText = driver.findElement(By.xpath("//select[@name='my-select']/ancestor::label"));
        WebElement dropdownSelectForm = driver.findElement(By.name("my-select"));
        WebElement dropdownDataListText = driver.findElement(By.xpath("//input[@name='my-datalist']/ancestor::label"));
        WebElement dropdownDataListForm = driver.findElement(By.name("my-datalist"));
        WebElement fileInputText = driver.findElement(By.xpath("//input[@name='my-file']/ancestor::label"));
        WebElement fileInputForm = driver.findElement(By.name("my-file"));
        WebElement checkedCheckboxText = driver.findElement(By.xpath("//input[@id='my-check-1']/ancestor::label"));
        WebElement checkedCheckboxForm = driver.findElement(By.id("my-check-1"));
        WebElement defaultCheckboxText = driver.findElement(By.xpath("//input[@id='my-check-2']/ancestor::label"));
        WebElement defaultCheckboxForm = driver.findElement(By.id("my-check-2"));
        WebElement checkedRadioText = driver.findElement(By.xpath("//input[@id='my-radio-1']/ancestor::label"));
        WebElement checkedRadioForm = driver.findElement(By.id("my-radio-1"));
        WebElement defaultRadioText = driver.findElement(By.xpath("//input[@id='my-radio-2']/ancestor::label"));
        WebElement defaultRadioForm = driver.findElement(By.id("my-radio-2"));
        WebElement buttonSubmit = driver.findElement(By.cssSelector(".btn.btn-outline-primary.mt-3"));

        WebElement colorPickerText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(3) label:nth-child(1)"));
        WebElement colorPickerForm = driver.findElement(By.name("my-colors"));
        WebElement datePickerText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(3) label:nth-child(2)"));
        WebElement datePickerForm = driver.findElement(By.name("my-date"));
        WebElement exampleRangeText = driver.findElement(By.cssSelector("form div.col-md-4.py-2:nth-child(3) label:nth-child(3)"));
        WebElement exampleRangeForm = driver.findElement(By.name("my-range"));

//        List<WebElement> webFormElements = driver.findElements(By.cssSelector(".form-label.w-100"));
//        System.out.println(webFormElements.size());
//        for (WebElement  webFormElement : webFormElements){
//            System.out.println(webFormElement);
//        }

        WebElement copyrightText = driver.findElement(By.className("text-muted"));
        WebElement bonigarsiaUrl = driver.findElement(By.cssSelector("span.text-muted a"));

        assertAll(
                () -> assertEquals("Hands-On Selenium WebDriver with Java", actualMainTitle.getText()),
                () -> assertEquals("Practice site", actualMainSubtitle.getText()),
                () -> assertEquals("img", titlePicture.getTagName()),

                () -> assertEquals("hr", greyLine.getTagName()),

                () -> assertEquals("Web form", actualPageTitle.getText()),

                () -> assertEquals("Text input", textInputText.getText()),
                () -> assertEquals("input", textInputForm.getTagName()),
                () -> assertEquals("Password", passwordText.getText()),
                () -> assertEquals("input", passwordForm.getTagName()),
                () -> assertEquals("Textarea", textareaText.getText()),
                () -> assertEquals("textarea", textareaForm.getTagName()),
                () -> assertEquals("Disabled input", disabledInputText.getText()),
                () -> assertEquals("input", disabledInputForm.getTagName()),
                () -> assertEquals("Readonly input", readonlyInputText.getText()),
                () -> assertEquals("input", readonlyInputForm.getTagName()),
                () -> assertEquals("Return to index", returnToIndexUrl.getText()),

                () -> assertEquals("Dropdown (select)\n" +
                        "                Open this select menu\n" +
                        "                One\n" +
                        "                Two\n" +
                        "                Three\n" +
                        "              ", dropdownSelectText.getText()),
                () -> assertEquals("select", dropdownSelectForm.getTagName()),
                () -> assertEquals("Dropdown (datalist)", dropdownDataListText.getText()),
                () -> assertEquals("input", dropdownDataListForm.getTagName()),
                () -> assertEquals("File input", fileInputText.getText()),
                () -> assertEquals("input", fileInputForm.getTagName()),
                () -> assertEquals("Checked checkbox", checkedCheckboxText.getText()),
                () -> assertEquals("input", checkedCheckboxForm.getTagName()),
                () -> assertEquals("Default checkbox", defaultCheckboxText.getText()),
                () -> assertEquals("input", defaultCheckboxForm.getTagName()),
                () -> assertEquals("Checked radio", checkedRadioText.getText()),
                () -> assertEquals("input", checkedRadioForm.getTagName()),
                () -> assertEquals("Default radio", defaultRadioText.getText()),
                () -> assertEquals("input", defaultRadioForm.getTagName()),
                () -> assertEquals("Submit", buttonSubmit.getText()),

                () -> assertEquals("Color picker", colorPickerText.getText()),
                () -> assertEquals("input", colorPickerForm.getTagName()),
                () -> assertEquals("Date picker", datePickerText.getText()),
                () -> assertEquals("input", datePickerForm.getTagName()),
                () -> assertEquals("Example range", exampleRangeText.getText()),
                () -> assertEquals("input", exampleRangeForm.getTagName()),
                () -> assertEquals("Copyright © 2021-2025 Boni García", copyrightText.getText()),
                () -> assertEquals("Boni García", bonigarsiaUrl.getText())
        );
    }

    // Пример теста на проверку chapters
    // Если не работает driver.navigate().back(); то нам нужно новую ссылку открывать не в том же окне, а в новом. Чтобы проверить новую открытую вкладку и закрыть её, а первое главное окно висело без изменений и мы могли к нему возвращаться, а у элементов не менялись id
    @Test
    void openAllLinksTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        int qtyLinks = 0;
        List<WebElement> chapters = driver.findElements(By.cssSelector("h5.card-title"));
        for (WebElement chapter : chapters) {
            List<WebElement> links = chapter.findElements(By.xpath("./../a"));
            qtyLinks += links.size();
            System.out.println(chapter.getText());
            for (WebElement link : links) {
                System.out.println(link.getText());
                link.click();
//                Thread.sleep(1000);
                driver.navigate().back();
            }
        }
        assertEquals(6, chapters.size());
        assertEquals(27, qtyLinks);
    }
}
