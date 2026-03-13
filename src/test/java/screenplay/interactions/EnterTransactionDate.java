package screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.DATE_INPUT;

public class EnterTransactionDate implements Interaction {

    private final String date;

    public EnterTransactionDate(String date) {
        this.date = date;
    }

    @Override
    @Step("{0} enters transaction date #date")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript(
            "arguments[0].value = arguments[1];"
                + "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
                + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            DATE_INPUT.resolveFor(actor),
            date
        );
    }

    public static Performable value(String date) {
        return instrumented(EnterTransactionDate.class, date);
    }
}
