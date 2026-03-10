package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import screenplay.interactions.EnterRegistrationData;
import screenplay.model.RegistrationData;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompleteRegistrationForm implements Task {

    private final RegistrationData data;

    public CompleteRegistrationForm(RegistrationData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(EnterRegistrationData.using(data));
    }

    public static Performable withValidData() {
        return instrumented(CompleteRegistrationForm.class, RegistrationData.valid());
    }
}
