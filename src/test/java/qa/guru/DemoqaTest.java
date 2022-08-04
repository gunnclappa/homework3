package qa.guru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaTest {

    @BeforeAll
    static void configure() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        // Name
        $("#firstName").setValue("Kayrat");
        $("#lastName").setValue("Nurekenov");

        // Email
        $("#userEmail").setValue("Test@Test.com");

        // Gender
        $(byText("Male")).click();

        // Mobile
        $("#userNumber").setValue("8800800800");

        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        $(".react-datepicker__day--021").click();

        // Subjects
        $("#subjectsInput").setValue("Maths").pressEnter();

        // Hobbies
        $(byText("Music")).click();

        // Picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/cat.jpg"));

        // Current Address
        $("#currentAddress").setValue("Abbey Road: London, England");

        // State and City
        $(By.xpath("//*[contains(text(),'Select State')]")).scrollTo().click();
        $(byText("Haryana")).click();
        $(By.xpath("//*[contains(text(),'Select City')]")).click();
        $(byText("Karnal")).click();

        // Submit
        executeJavaScript("return document.getElementsByTagName('footer')[0].remove()");
        $("#submit").pressEnter();

        // Assertions
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Kayrat Nurekenov"),
                text("Test@Test.com"),
                text("Male"),
                text("8800800800"),
                text("21 April,1999"),
                text("Maths"),
                text("Music"),
                text("cat.jpg"),
                text("Abbey Road: London, England"),
                text("Haryana Karnal")
        );
    }
}
