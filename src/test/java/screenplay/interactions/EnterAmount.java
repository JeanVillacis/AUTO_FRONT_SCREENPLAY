package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.AMOUNT_INPUT;

public class EnterAmount implements Interaction {

    private final String amount;

    public EnterAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement field = AMOUNT_INPUT.resolveFor(actor);
        field.click();
        field.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(amount);
    }

    public static Performable withValue(String amount) {
        return instrumented(EnterAmount.class, amount);
    }
}
