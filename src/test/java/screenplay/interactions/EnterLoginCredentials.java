package screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.LoginPageUI.EMAIL_INPUT;

public class EnterLoginCredentials implements Interaction {

    private final String email;

    public EnterLoginCredentials(String email) {
        this.email = email;
    }

    @Override
    @Step("{0} enters email #email")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(email).into(EMAIL_INPUT));
    }

    public static Performable withEmail(String email) {
        return instrumented(EnterLoginCredentials.class, email);
    }
}
