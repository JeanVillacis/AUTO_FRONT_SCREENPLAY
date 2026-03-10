package screenplay.model;

import utils.TestDataConstants;

public class TransactionData {

    private final String type;
    private final String description;
    private final String amount;
    private final String category;
    private final String date;

    public TransactionData(String type, String description, String amount, String category, String date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public static TransactionData validExpense() {
        return new TransactionData(
            TestDataConstants.TX_TYPE,
            TestDataConstants.TX_DESCRIPTION,
            TestDataConstants.TX_AMOUNT,
            TestDataConstants.TX_CATEGORY,
            TestDataConstants.TX_DATE
        );
    }
}
