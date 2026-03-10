package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.TYPE_OPTION_EXPENSE;
import static screenplay.ui.TransactionPageUI.TYPE_OPTION_INCOME;
import static screenplay.ui.TransactionPageUI.TYPE_SELECT_TRIGGER;

public class SelectTransactionType implements Interaction {

    private final String type;

    public SelectTransactionType(String type) {
        this.type = type;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(TYPE_SELECT_TRIGGER),
            Click.on(transactionTypeOption(type))
        );
    }

    public static Performable value(String type) {
        return instrumented(SelectTransactionType.class, type);
    }

    private static net.serenitybdd.screenplay.targets.Target transactionTypeOption(String type) {
        if ("EXPENSE".equalsIgnoreCase(type)) {
            return TYPE_OPTION_EXPENSE;
        }
        if ("INCOME".equalsIgnoreCase(type)) {
            return TYPE_OPTION_INCOME;
        }
        throw new IllegalArgumentException("Tipo de transaccion no soportado: " + type);
    }
}
