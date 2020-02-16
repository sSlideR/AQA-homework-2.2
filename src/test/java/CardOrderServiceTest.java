import com.codeborne.selenide.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardOrderServiceTest {

    String today = LocalDate.now(ZoneId.ofOffset("UTC", "+0300")).toString();

    @AfterEach
    void closeBrowser() {
        close();
    }

    @Test
    void ShouldSubmitForm() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("input[placeholder='Город'").setValue("Саранск");
        form.$("input.input__control[name=phone").setValue("+79210001234");
        form.$("span.checkbox__box").click();
        form.$("button").click();
        System.out.println($(".Success_successBlock__2L3Cw span").getText());
        $(".Success_successBlock__2L3Cw p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

}
