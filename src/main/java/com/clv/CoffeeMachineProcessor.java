package com.clv;

import com.clv.checker.BeverageQuantityChecker;
import com.clv.email.EmailNotifier;
import com.clv.order.DrinkType;
import com.clv.order.Order;
import com.clv.protocolmessage.ProtocolMessageBuilder;
import com.clv.reporting.Report;
import com.clv.reporting.ReportGenerator;
import com.clv.repository.Purchase;
import com.clv.repository.PurchaseRepository;

public class CoffeeMachineProcessor {

    public static final String MISSING_MONEY_MESSAGE_TEMPLATE = "Not enough inserted money. Missing %s cents";
    public static final String SHORTAGE_MESSAGE = "Drink shortage. Notification to refill the machine has been sent";

    private final ProtocolMessageBuilder protocolMessageBuilder;
    private final PurchaseRepository repository;
    private final EmailNotifier emailNotifier;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final ReportGenerator reportGenerator;

    public CoffeeMachineProcessor(ProtocolMessageBuilder protocolMessageBuilder,
                                  PurchaseRepository repository,
                                  EmailNotifier emailNotifier,
                                  BeverageQuantityChecker beverageQuantityChecker,
                                  ReportGenerator reportGenerator) {
        this.protocolMessageBuilder = protocolMessageBuilder;
        this.repository = repository;
        this.emailNotifier = emailNotifier;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.reportGenerator = reportGenerator;
    }

    public String processOrder(Order order) {

        if(isMissingDrink(order.getDrinkType())){
            notifyMissingDrink(order.getDrinkType());
            return protocolMessageBuilder.buildInfoMessage(SHORTAGE_MESSAGE);
        }

        if(isMissingMoney(order)) {
            return protocolMessageBuilder.buildInfoMessage(buildMissingMoneyMessage(order));
        }

        saveSoldOrder(order);

        return protocolMessageBuilder.buildOrderMessage(order.getDrink());
    }

    public Report generateReport() {
        return reportGenerator.generate(repository.getAll());
    }

    private boolean isMissingMoney(Order order) {
        return order.getDrinkType().getPrice() > order.getMoney();
    }

    private boolean isMissingDrink(DrinkType drink) {
        return beverageQuantityChecker.isEmpty(drink.toString());
    }

    private String buildMissingMoneyMessage(Order order) {
        int missingMoney = order.getDrinkType().getPrice() - order.getMoney();
        return String.format(MISSING_MONEY_MESSAGE_TEMPLATE, missingMoney);
    }

    private void notifyMissingDrink(DrinkType drink) {
        emailNotifier.notifyMissingDrink(drink.toString());
    }

    private void saveSoldOrder(Order order) {
        repository.save(new Purchase(order.getDrinkType()));
    }



}
