package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.DESCRIPTION_INPUT;

public class EnterDescription implements Interaction {

    private final String description;

    public EnterDescription(String description) {
        this.description = description;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(description).into(DESCRIPTION_INPUT));
    }

    public static Performable withValue(String description) {
        return instrumented(EnterDescription.class, description);
    }
}
