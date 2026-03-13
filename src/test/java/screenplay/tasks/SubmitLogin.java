package screenplay.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.LoginPageUI.LOGIN_SUBMIT_BUTTON;

public class SubmitLogin implements Task {

    @Override
    @Step("{0} submits the login form")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(LOGIN_SUBMIT_BUTTON));
    }

    public static Performable form() {
        return instrumented(SubmitLogin.class);
    }
}
