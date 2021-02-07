package com.clv;

import com.clv.checker.BeverageQuantityChecker;
import com.clv.email.EmailNotifier;
import com.clv.order.Drink;
import com.clv.order.DrinkFactory;
import com.clv.order.DrinkType;
import com.clv.order.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.clv.protocolmessage.ProtocolMessageBuilder;
import com.clv.repository.Purchase;
import com.clv.repository.PurchaseRepository;

import static com.clv.order.DrinkType.TEA;
import static com.clv.order.SugarQuantity.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoffeeMachineProcessorTest {

    private final DrinkFactory drinkFactory = new DrinkFactory();

    @Mock
    private ProtocolMessageBuilder protocolMessageBuilder;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;

    @InjectMocks
    private CoffeeMachineProcessor coffeeMachineProcessor;

    @Test
    public void givenEnoughMoneyOrderAndEnoughBeverage_whenProcess_thenReturnExpectedOrderMessageAndPurchaseWasSavedAndNotificationNotSent() {

        Drink drink = drinkFactory.createDrink(TEA, ONE, false);
        Order order = new Order(drink, TEA.getPrice());

        String expectedMessage = "T:1:0";
        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(false);
        when(protocolMessageBuilder.buildOrderMessage(order.getDrink()))
                .thenReturn(expectedMessage);

        String actualMessage = coffeeMachineProcessor.processOrder(order);
        assertEquals(expectedMessage, actualMessage);
        verify(purchaseRepository).save(any(Purchase.class));
        verify(emailNotifier, never()).notifyMissingDrink(anyString());

    }

    @Test
    public void givenNotEnoughMoneyOrderAndEnoughBeverage_whenProcess_thenReturnExpectedInfoMessageAndPurchaseWasNotSavedAndNotificationsNotSent() {

        Drink drink = drinkFactory.createDrink(TEA, ONE, false);
        Order order = new Order(drink, TEA.getPrice() - 1);
        String missingMoneyMessage = String.format(CoffeeMachineProcessor.MISSING_MONEY_MESSAGE_TEMPLATE, 1);
        String expectedMessage = "M:"+missingMoneyMessage;

        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(false);
        when(protocolMessageBuilder.buildInfoMessage(missingMoneyMessage))
                .thenReturn(expectedMessage);

        String actualMessage = coffeeMachineProcessor.processOrder(order);
        assertEquals(expectedMessage, actualMessage);
        verify(purchaseRepository, never()).save(any(Purchase.class));
        verify(emailNotifier, never()).notifyMissingDrink(anyString());

    }

    @Test
    public void givenEnoughMoneyOrderAndNotEnoughBeverage_whenProcess_thenReturnExpectedInfoMessageAndPurchaseWasNotSavedAndNotificationSent() {

        Drink drink = drinkFactory.createDrink(TEA, ONE, false);
        Order order = new Order(drink, TEA.getPrice());
        String expectedMessage = "M:"+ CoffeeMachineProcessor.SHORTAGE_MESSAGE;

        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(true);
        when(protocolMessageBuilder.buildInfoMessage(CoffeeMachineProcessor.SHORTAGE_MESSAGE))
                .thenReturn(expectedMessage);

        String actualMessage = coffeeMachineProcessor.processOrder(order);

        assertEquals(expectedMessage, actualMessage);
        verify(purchaseRepository, never()).save(any(Purchase.class));
        verify(emailNotifier).notifyMissingDrink(TEA.toString());

    }

    @Test
    public void givenNotEnoughMoneyOrderAndNotEnoughBeverage_whenProcess_thenReturnExpectedInfoMessageAndPurchaseWasNotSavedAndNotificationSent() {

        Drink drink = drinkFactory.createDrink(TEA, ONE, false);

        Order order = new Order(drink, TEA.getPrice() - 1);
        String expectedMessage = "M:"+ CoffeeMachineProcessor.SHORTAGE_MESSAGE;

        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(true);
        when(protocolMessageBuilder.buildInfoMessage(CoffeeMachineProcessor.SHORTAGE_MESSAGE))
                .thenReturn(expectedMessage);

        String actualMessage = coffeeMachineProcessor.processOrder(order);

        assertEquals(expectedMessage, actualMessage);
        verify(purchaseRepository, never()).save(any(Purchase.class));
        verify(emailNotifier).notifyMissingDrink(TEA.toString());
    }

}
