package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTransactionsSection implements Task {

    private static final String BASE_URL = System.getProperty("webdriver.base.url", "http://localhost:3000");

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(BASE_URL + "/transactions"));
    }

    public static Performable now() {
        return instrumented(OpenTransactionsSection.class);
    }
}
