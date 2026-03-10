package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.model.LoginCredentials;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.LoginPageUI.EMAIL_INPUT;
import static screenplay.ui.LoginPageUI.PASSWORD_INPUT;

public class EnterLoginCredentials implements Interaction {

    private final LoginCredentials credentials;

    public EnterLoginCredentials(LoginCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(credentials.getEmail()).into(EMAIL_INPUT),
            Enter.theValue(credentials.getPassword()).into(PASSWORD_INPUT)
        );
    }

    public static Performable using(LoginCredentials credentials) {
        return instrumented(EnterLoginCredentials.class, credentials);
    }
}
