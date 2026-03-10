package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static screenplay.ui.DashboardPageUI.DASHBOARD_SIDEBAR;

public class IsDashboardVisible implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return DASHBOARD_SIDEBAR.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> value() {
        return new IsDashboardVisible();
    }
}
