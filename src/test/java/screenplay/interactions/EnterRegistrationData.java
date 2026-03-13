package screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EnterRegistrationData implements Interaction {

    private final String value;
    private final Target field;

    public EnterRegistrationData(String value, Target field) {
        this.value = value;
        this.field = field;
    }

    @Override
    @Step("{0} enters #value into registration field")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(value).into(field));
    }

    public static Performable intoField(String value, Target field) {
        return instrumented(EnterRegistrationData.class, value, field);
    }
}
