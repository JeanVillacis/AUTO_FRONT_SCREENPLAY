package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class TransactionPageUI {

    public static final Target NEW_TRANSACTION_BUTTON = Target.the("boton nueva transaccion")
        .locatedBy("//button[.//span[contains(text(), 'Nueva Transaccion') or contains(text(), 'Nueva Transacción')]]");

    public static final Target NEW_TRANSACTION_MODAL_TITLE = Target.the("titulo del modal nueva transaccion")
        .locatedBy("//div[@data-slot='dialog-content']//h2[contains(text(), 'Nueva Transaccion') or contains(text(), 'Nueva Transacción')]");

    public static final Target TYPE_SELECT_TRIGGER = Target.the("selector tipo de transaccion")
        .locatedBy("//label[normalize-space()='Tipo']/following::button[@role='combobox'][1]");

    public static final Target TYPE_OPTION_EXPENSE = Target.the("opcion egreso")
        .locatedBy("//*[@role='option' and (.='Egreso' or .//*[normalize-space()='Egreso'])]");

    public static final Target TYPE_OPTION_INCOME = Target.the("opcion ingreso")
        .locatedBy("//*[@role='option' and (.='Ingreso' or .//*[normalize-space()='Ingreso'])]");

    public static final Target DESCRIPTION_INPUT = Target.the("campo descripcion")
        .located(By.cssSelector("input[placeholder='Ej: Supermercado']"));

    public static final Target AMOUNT_INPUT = Target.the("campo monto")
        .located(By.cssSelector("input[type='number'][placeholder='0.00']"));

    public static final Target CATEGORY_SELECT_TRIGGER = Target.the("selector categoria")
        .locatedBy("//label[contains(normalize-space(), 'Categor')]/following::button[@role='combobox'][1]");

    public static final Target CATEGORY_OPTION_BY_NAME = Target.the("opcion de categoria")
        .locatedBy("//*[@role='option' and (normalize-space()='{0}' or .//*[normalize-space()='{0}'])]");

    public static final Target DATE_INPUT = Target.the("campo fecha")
        .located(By.cssSelector("input[type='date']"));

    public static final Target CREATE_TRANSACTION_SUBMIT_BUTTON = Target.the("boton crear transaccion")
        .locatedBy("//div[@data-slot='dialog-content']//button[@type='submit' and (contains(., 'Crear Transaccion') or contains(., 'Crear Transacción'))]");

    public static final Target SUCCESS_TOAST = Target.the("toast de transaccion exitosa")
        .locatedBy("//li[@data-sonner-toast]");

    public static final Target TRANSACTION_ROW_BY_DESCRIPTION = Target.the("fila de transaccion por descripcion")
        .locatedBy("//table//td[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{0}')]");

    private TransactionPageUI() {}
}
