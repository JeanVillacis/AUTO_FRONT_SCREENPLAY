package screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectTransactionType implements Interaction {

    private final Target target;

    public SelectTransactionType(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} clicks transaction type target")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(target));
    }

    public static Performable openDropdown(Target trigger) {
        return instrumented(SelectTransactionType.class, trigger);
    }

    public static Performable option(Target option) {
        return instrumented(SelectTransactionType.class, option);
    }
}
