package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Locale;

import static screenplay.ui.TransactionPageUI.TRANSACTION_ROW_BY_DESCRIPTION;

public class IsTransactionListed implements Question<Boolean> {

    private final String description;

    public IsTransactionListed(String description) {
        this.description = description;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String normalizedDescription = description.toLowerCase(Locale.ROOT);
        return TRANSACTION_ROW_BY_DESCRIPTION.of(normalizedDescription)
            .resolveFor(actor)
            .isVisible();
    }

    public static Question<Boolean> withDescription(String description) {
        return new IsTransactionListed(description);
    }
}
