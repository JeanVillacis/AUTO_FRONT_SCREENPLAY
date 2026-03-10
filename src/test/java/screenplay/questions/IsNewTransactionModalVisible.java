package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static screenplay.ui.TransactionPageUI.NEW_TRANSACTION_MODAL_TITLE;

public class IsNewTransactionModalVisible implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return NEW_TRANSACTION_MODAL_TITLE.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> value() {
        return new IsNewTransactionModalVisible();
    }
}
