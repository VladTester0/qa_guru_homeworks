package quru.qa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FillInForm extends BaseTest {
    String name = "Nick";
    String lastName = "Perry";
    String userEmail = "perry@gmail.com";
    String userNumber = "8765329870";
    String gender = "Male";
    String filename = "Agent_P.jpg";
    String subject = "Computer Science";
    String hobbies = "Reading";
    String currentAddress = "Uglovaya, St. 8";
    String day = "13";
    String month = "August";
    String year = "1993";
    String state = "NCR";
    String city = "Delhi";

    @Test
    void fillInFormCheck() {
        open("https://demoqa.com/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        //first_name
        $("#userName-wrapper");
        $("#firstName").setValue(name);

        //last_name
        $("#userName-wrapper");
        $("#lastName").setValue(lastName);

        //user_email
        $("#userEmail-wrapper");
        $("#userEmail").setValue(userEmail);

        //checkbox_gender
        $(byText("Male")).click();

        //user_number
        $("#userNumber-wrapper");
        $("#userNumber").setValue(userNumber);

        //date_of_birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").findElement(By.xpath("//*[@id='dateOfBirth']//select/option[8]")).click();
        $(".react-datepicker__year-select").findElement(By.xpath("//*[@id='dateOfBirth']//select/option[94]")).click();
        $(".react-datepicker__week").findElement(By.xpath("//*[@id='dateOfBirth']//div[contains(text(),'13')]")).click();

        //subject
        $("#subjectsInput").setValue("Comp");
        $("#subjectsInput").pressEnter();

        //hobbies
        $(byText("Reading")).click();

        //file
        $("#uploadPicture").uploadFromClasspath(filename);

        //current_address
        $("#currentAddress").click();
        $("#currentAddress").setValue(currentAddress);

        //state_and_city
        $("#state").scrollIntoView(true).click();
        $(".css-26l3qy-menu").shouldBe(visible, Duration.ofSeconds(3));
        $("#react-select-3-option-0").click();
        $("#city").click();
        $(byClassName("css-26l3qy-menu")).shouldBe(visible, Duration.ofSeconds(3));
        $("#react-select-4-option-0").click();

        //submit_button
        $("#submit").scrollIntoView(true).click();
        //checks
        $$(".table-responsive").filterBy(text("Student name")).shouldHave(texts(name));
        $$(".table-responsive").filterBy(text("Student Email")).shouldHave(texts(userEmail));
        $$(".table-responsive").filterBy(text("Gender")).shouldHave(texts(gender));
        $$(".table-responsive").filterBy(text("Mobile")).shouldHave(texts(userNumber));
        $$(".table-responsive").filterBy(text("Date of Birth")).shouldHave(texts(day + " " + month + "," + year));
        $$(".table-responsive").filterBy(text("Subjects")).shouldHave(texts(subject));
        $$(".table-responsive").filterBy(text("Hobbies")).shouldHave(texts(hobbies));
        $$(".table-responsive").filterBy(text("Picture")).shouldHave(texts(filename));
        $$(".table-responsive").filterBy(text("Address")).shouldHave(texts(currentAddress));
        $$(".table-responsive").filterBy(text("State and City")).shouldHave(texts(state + " " + city));
        //close_button
        $("#closeLargeModal").click();
        $(".modal-content").shouldBe(disappear);
    }
}


