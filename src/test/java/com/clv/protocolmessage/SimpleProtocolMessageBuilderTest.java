package com.clv.protocolmessage;

import com.clv.order.Drink;
import com.clv.order.DrinkFactory;
import com.clv.order.DrinkType;
import com.clv.order.SugarQuantity;
import org.junit.jupiter.api.Test;

import static com.clv.order.DrinkType.*;
import static com.clv.order.SugarQuantity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleProtocolMessageBuilderTest {

    private final ProtocolMessageBuilder spmb = new SimpleProtocolMessageBuilder();
    private final DrinkFactory drinkFactory = new DrinkFactory();

    @Test
    public void givenTeaWithOneSugar_whenBuildOrderMessage_thenReturnTeaCode_delimiter_oneSugar_delimiter_stickCode() {

        DrinkType drinkType = TEA;
        SugarQuantity sugarQuantity = ONE;
        Drink drink = drinkFactory.createDrink(drinkType, sugarQuantity, false);
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode(),
                String.valueOf(sugarQuantity.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);

        String protocolMessage = spmb.buildOrderMessage(drink);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenChocolateWithoutSugar_whenBuildOrderMessage_thenReturnChocolateCode_delimiter_delimiter() {

        DrinkType drinkType = CHOCOLATE;
        Drink drink = drinkFactory.createDrink(drinkType);

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode(),
                SimpleProtocolMessageBuilder.DELIMITER);

        String protocolMessage = spmb.buildOrderMessage(drink);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenCoffeeWithTwoSugar_whenBuildOrderMessage_thenReturnCoffeeCode_delimiter_twoSugar_delimiter_stickCode() {

        DrinkType drinkType = COFFEE;
        SugarQuantity sugarQuantity = TWO;
        Drink drink = drinkFactory.createDrink(drinkType, sugarQuantity, false);

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode(),
                String.valueOf(sugarQuantity.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);
        String protocolMessage = spmb.buildOrderMessage(drink);
        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenMessage_whenBuildInfoMessage_thenReturnMessageCode_delimiter_message() {

       String message = "information message";
       String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                SimpleProtocolMessageBuilder.INFO_MESSAGE_CODE,
                message);

        String protocolMessage = spmb.buildInfoMessage(message);

        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenExtraHotCoffeeWithoutSugar_whenBuildOrderMessage_thenReturnCoffeeCode_extraHotCode_delimiter_delimiter() {

        DrinkType drinkType = COFFEE;
        SugarQuantity sugarQuantity = ZERO;
        Drink drink = drinkFactory.createDrink(drinkType, sugarQuantity, true);

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                SimpleProtocolMessageBuilder.DELIMITER);

        String protocolMessage = spmb.buildOrderMessage(drink);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenExtraHotChocolateWithOneSugar_whenBuildOrderMessage_thenReturnChocolateCode_extraHotCode_delimiter_oneSugar_delimiter_stickCode() {

        DrinkType drinkType = CHOCOLATE;
        SugarQuantity sugarQuantity = ONE;
        Drink drink = drinkFactory.createDrink(drinkType, sugarQuantity, true);

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                String.valueOf(sugarQuantity.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);

        String protocolMessage = spmb.buildOrderMessage(drink);
        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenExtraHotTeaWithTwoSugarOrder_whenBuildOrderMessage_thenReturnTeaCode_extraHotCode_delimiter_twoSugar_delimiter_stickCode() {


        DrinkType drinkType = TEA;
        SugarQuantity sugarQuantity = TWO;
        Drink drink = drinkFactory.createDrink(drinkType, sugarQuantity, true);

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                String.valueOf(sugarQuantity.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);

        String protocolMessage = spmb.buildOrderMessage(drink);
        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenOrangeJuiceOrder_whenBuildOrderMessage_thenReturnTeaCode_extraHotCode_delimiter_twoSugar_delimiter_stickCode() {

        DrinkType drinkType = ORANGE_JUICE;
        Drink drink = drinkFactory.createDrink(drinkType);
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                drinkType.getCode(),
                SimpleProtocolMessageBuilder.DELIMITER);
        String protocolMessage = spmb.buildOrderMessage(drink);
        assertEquals(expectedMessage, protocolMessage);

    }

}
