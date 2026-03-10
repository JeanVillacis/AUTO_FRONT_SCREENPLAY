package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import screenplay.interactions.EnterAmount;
import screenplay.interactions.EnterDescription;
import screenplay.interactions.EnterTransactionDate;
import screenplay.interactions.SelectCategory;
import screenplay.interactions.SelectTransactionType;
import screenplay.model.TransactionData;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static screenplay.ui.TransactionPageUI.*;

public class CompleteTransactionForm implements Task {

    private final TransactionData data;

    public CompleteTransactionForm(TransactionData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            SelectTransactionType.openDropdown(TYPE_SELECT_TRIGGER),
            SelectTransactionType.option(transactionTypeOption(data.getType())),
            EnterDescription.withValue(data.getDescription()),
            EnterAmount.withValue(data.getAmount()),
            SelectCategory.openDropdown(CATEGORY_SELECT_TRIGGER),
            SelectCategory.option(CATEGORY_OPTION_BY_NAME.of(data.getCategory())),
            EnterTransactionDate.value(data.getDate())
        );
    }

    public static Performable withData(String type, String description, String amount, String category, String date) {
        return instrumented(CompleteTransactionForm.class,
            new TransactionData(type, description, amount, category, date));
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
