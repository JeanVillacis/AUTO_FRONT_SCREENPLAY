package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static screenplay.ui.TransactionPageUI.SUCCESS_TOAST;

public class IsSuccessToastVisible implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return SUCCESS_TOAST.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> value() {
        return new IsSuccessToastVisible();
    }
}
