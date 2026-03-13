package screenplay.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.RegisterPageUI.REGISTER_SUBMIT_BUTTON;

public class SubmitRegistration implements Task {

    @Override
    @Step("{0} submits the registration form")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(REGISTER_SUBMIT_BUTTON));
    }

    public static Performable form() {
        return instrumented(SubmitRegistration.class);
    }
}
