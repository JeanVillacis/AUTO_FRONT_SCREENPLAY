package screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.AMOUNT_INPUT;

public class EnterAmount implements Interaction {

    private final String amount;

    public EnterAmount(String amount) {
        this.amount = amount;
    }

    @Override
    @Step("{0} enters the amount #amount")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(AMOUNT_INPUT),
            Enter.theValue(Keys.chord(Keys.COMMAND, "a")).into(AMOUNT_INPUT),
            Hit.the(Keys.BACK_SPACE).into(AMOUNT_INPUT),
            Enter.theValue(amount).into(AMOUNT_INPUT)
        );
    }

    public static Performable withValue(String amount) {
        return instrumented(EnterAmount.class, amount);
    }
}
