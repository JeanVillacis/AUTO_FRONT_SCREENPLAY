package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class DashboardPageUI {

    public static final Target DASHBOARD_SIDEBAR = Target.the("sidebar del dashboard")
        .located(By.cssSelector("[data-slot='sidebar']"));

    private DashboardPageUI() {}
}
