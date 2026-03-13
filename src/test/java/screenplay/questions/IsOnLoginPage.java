package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static screenplay.ui.LoginPageUI.LOGIN_SUBMIT_BUTTON;

public class IsOnLoginPage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return LOGIN_SUBMIT_BUTTON.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> value() {
        return new IsOnLoginPage();
    }
}
