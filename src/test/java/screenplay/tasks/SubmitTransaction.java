package screenplay.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.CREATE_TRANSACTION_SUBMIT_BUTTON;

public class SubmitTransaction implements Task {

    @Override
    @Step("{0} submits the transaction form")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CREATE_TRANSACTION_SUBMIT_BUTTON));
    }

    public static Performable form() {
        return instrumented(SubmitTransaction.class);
    }
}
