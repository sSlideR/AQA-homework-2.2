import com.codeborne.selenide.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardOrderServiceTest {


    public static String getDate(int adj) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, adj);
        Date currentDate = date.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(currentDate);
    }


        String currentDateOffset5Days = getDate(5);

    @AfterEach
    void closeBrowser() {
        close();
    }

    @Test
    void ShouldSubmitForm() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("form");

        form.$("input[placeholder='Город'").setValue("Саранск");

        form.$("input.input__control[formnovalidate]").click();
        form.$("input.input__control[formnovalidate]").sendKeys("\b\b\b\b\b\b\b\b");
        form.$("input.input__control[formnovalidate]").sendKeys(currentDateOffset5Days);

        form.$("input[name='name']").setValue("Степанов Петр");

        form.$("input[name='phone']").setValue("+79991230000");

        form.$("span.checkbox__box").click();

        form.$(withText("Забронировать")).click();

        SelenideElement notification = $("div.notification");
        notification.$(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
        notification.$(withText(currentDateOffset5Days)).waitUntil(visible, 15000);


    }

}
