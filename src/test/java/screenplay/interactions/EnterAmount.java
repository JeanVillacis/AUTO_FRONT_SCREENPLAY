package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.AMOUNT_INPUT;

public class EnterAmount implements Interaction {

    private final String amount;

    public EnterAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(amount).into(AMOUNT_INPUT));
    }

    public static Performable withValue(String amount) {
        return instrumented(EnterAmount.class, amount);
    }
}
