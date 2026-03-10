package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.LoginPageUI.PASSWORD_INPUT;

public class EnterLoginPassword implements Interaction {

    private final String password;

    public EnterLoginPassword(String password) {
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(password).into(PASSWORD_INPUT));
    }

    public static Performable withPassword(String password) {
        return instrumented(EnterLoginPassword.class, password);
    }
}
