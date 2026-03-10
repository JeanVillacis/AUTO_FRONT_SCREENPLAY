package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.NEW_TRANSACTION_BUTTON;

public class OpenNewTransactionModal implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(NEW_TRANSACTION_BUTTON));
    }

    public static Performable now() {
        return instrumented(OpenNewTransactionModal.class);
    }
}
