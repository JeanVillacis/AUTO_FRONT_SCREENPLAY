package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.CATEGORY_OPTION_BY_NAME;
import static screenplay.ui.TransactionPageUI.CATEGORY_SELECT_TRIGGER;

public class SelectCategory implements Interaction {

    private final String category;

    public SelectCategory(String category) {
        this.category = category;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CATEGORY_SELECT_TRIGGER),
            Click.on(CATEGORY_OPTION_BY_NAME.of(category))
        );
    }

    public static Performable named(String category) {
        return instrumented(SelectCategory.class, category);
    }
}
