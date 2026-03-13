package screenplay.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTransactionsSection implements Task {

    @Override
    @Step("{0} navigates to the transactions section")
    public <T extends Actor> void performAs(T actor) {
        EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
        actor.attemptsTo(Open.url(env.getProperty("webdriver.base.url") + "/transactions"));
    }

    public static Performable now() {
        return instrumented(OpenTransactionsSection.class);
    }
}
