package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import utils.TestDataConstants;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTransactionsSection implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(TestDataConstants.TRANSACTIONS_URL));
    }

    public static Performable now() {
        return instrumented(OpenTransactionsSection.class);
    }
}
