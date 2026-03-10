package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.model.RegistrationData;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.RegisterPageUI.CONFIRM_PASSWORD_INPUT;
import static screenplay.ui.RegisterPageUI.DISPLAY_NAME_INPUT;
import static screenplay.ui.RegisterPageUI.EMAIL_INPUT;
import static screenplay.ui.RegisterPageUI.PASSWORD_INPUT;

public class EnterRegistrationData implements Interaction {

    private final RegistrationData data;

    public EnterRegistrationData(RegistrationData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(data.getDisplayName()).into(DISPLAY_NAME_INPUT),
            Enter.theValue(data.getEmail()).into(EMAIL_INPUT),
            Enter.theValue(data.getPassword()).into(PASSWORD_INPUT),
            Enter.theValue(data.getConfirmPassword()).into(CONFIRM_PASSWORD_INPUT)
        );
    }

    public static Performable using(RegistrationData data) {
        return instrumented(EnterRegistrationData.class, data);
    }
}
