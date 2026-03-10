package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import screenplay.interactions.EnterRegistrationData;
import screenplay.model.RegistrationData;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.RegisterPageUI.*;

public class CompleteRegistrationForm implements Task {

    private final RegistrationData data;

    public CompleteRegistrationForm(RegistrationData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            EnterRegistrationData.intoField(data.getDisplayName(), DISPLAY_NAME_INPUT),
            EnterRegistrationData.intoField(data.getEmail(), EMAIL_INPUT),
            EnterRegistrationData.intoField(data.getPassword(), PASSWORD_INPUT),
            EnterRegistrationData.intoField(data.getConfirmPassword(), CONFIRM_PASSWORD_INPUT)
        );
    }

    public static Performable withValidData() {
        return instrumented(CompleteRegistrationForm.class, RegistrationData.valid());
    }
}
