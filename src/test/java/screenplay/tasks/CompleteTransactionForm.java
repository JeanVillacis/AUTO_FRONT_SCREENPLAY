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

public class CompleteTransactionForm implements Task {

    private final TransactionData data;

    public CompleteTransactionForm(TransactionData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            SelectTransactionType.value(data.getType()),
            EnterDescription.withValue(data.getDescription()),
            EnterAmount.withValue(data.getAmount()),
            SelectCategory.named(data.getCategory()),
            EnterTransactionDate.value(data.getDate())
        );
    }

    public static Performable withValidData() {
        return instrumented(CompleteTransactionForm.class, TransactionData.validExpense());
    }
}
