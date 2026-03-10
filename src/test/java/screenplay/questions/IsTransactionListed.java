package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Locale;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static screenplay.ui.TransactionPageUI.TRANSACTION_ROW_BY_DESCRIPTION;

public class IsTransactionListed implements Question<Boolean> {

    private final String description;

    public IsTransactionListed(String description) {
        this.description = description;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String normalizedDescription = description.toLowerCase(Locale.ROOT);
        try {
            actor.attemptsTo(
                WaitUntil.the(TRANSACTION_ROW_BY_DESCRIPTION.of(normalizedDescription), isVisible())
                    .forNoMoreThan(20).seconds()
            );
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static Question<Boolean> withDescription(String description) {
        return new IsTransactionListed(description);
    }
}
