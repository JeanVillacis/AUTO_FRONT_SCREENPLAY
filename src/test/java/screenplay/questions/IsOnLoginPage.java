package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class IsOnLoginPage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl().contains("/login");
    }

    public static Question<Boolean> value() {
        return new IsOnLoginPage();
    }
}
