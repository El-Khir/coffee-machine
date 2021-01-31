package com.clv.protocolmessage;

import com.clv.order.*;
import org.junit.jupiter.api.Test;

import static com.clv.order.CoolDrinkType.ORANGE_JUICE;
import static com.clv.order.HotDrinkType.*;
import static com.clv.order.HotDrinkType.TEA;
import static com.clv.order.SugarQuantity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleProtocolMessageBuilderTest {

    private final ProtocolMessageBuilder spmb = new SimpleProtocolMessageBuilder();

    @Test
    public void givenTeaWithOneSugar_whenBuildOrderMessage_thenReturnTeaCode_delimiter_oneSugar_delimiter_stickCode() {

        DrinkType tea = TEA;
        SugarQuantity oneSugar = ONE;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                tea.getCode(),
                String.valueOf(oneSugar.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);

        String protocolMessage = spmb.buildOrderMessage(tea, oneSugar, false);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenChocolateWithoutSugar_whenBuildOrderMessage_thenReturnChocolateCode_delimiter_delimiter() {

        DrinkType chocolate = CHOCOLATE;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                chocolate.getCode(),
                SimpleProtocolMessageBuilder.DELIMITER);

        String protocolMessage = spmb.buildOrderMessage(chocolate, ZERO, false);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenCoffeeWithTwoSugar_whenBuildOrderMessage_thenReturnCoffeeCode_delimiter_twoSugar_delimiter_stickCode() {

        DrinkType coffee = COFFEE;
        SugarQuantity twoSugar = TWO;

        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                coffee.getCode(),
                String.valueOf(twoSugar.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);
        String protocolMessage = spmb.buildOrderMessage(coffee, twoSugar, false);
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

        DrinkType coffee = COFFEE;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                coffee.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                SimpleProtocolMessageBuilder.DELIMITER);

        String protocolMessage = spmb.buildOrderMessage(coffee, ZERO, true);

        assertEquals(expectedMessage, protocolMessage);
    }

    @Test
    public void givenExtraHotChocolateWithOneSugar_whenBuildOrderMessage_thenReturnChocolateCode_extraHotCode_delimiter_oneSugar_delimiter_stickCode() {

        DrinkType chocolate = CHOCOLATE;
        SugarQuantity one = ONE;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                chocolate.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                String.valueOf(one.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);

        String protocolMessage = spmb.buildOrderMessage(chocolate, one, true);
        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenExtraHotTeaWithTwoSugarOrderAndEnoughMoney_whenBuildOrderMessage_thenReturnTeaCode_extraHotCode_delimiter_twoSugar_delimiter_stickCode() {

        HotDrinkType tea = TEA;
        SugarQuantity two = TWO;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                tea.getCode() + SimpleProtocolMessageBuilder.EXTRA_HOT_CODE,
                String.valueOf(two.getIntValue()),
                SimpleProtocolMessageBuilder.STICK_CODE);
        String protocolMessage = spmb.buildOrderMessage(tea, two, true);
        assertEquals(expectedMessage, protocolMessage);

    }

    @Test
    public void givenOrangeJuiceOrder_whenBuildOrderMessage_thenReturnTeaCode_extraHotCode_delimiter_twoSugar_delimiter_stickCode() {

        CoolDrinkType orangeJuice = ORANGE_JUICE;
        String expectedMessage = String.join(SimpleProtocolMessageBuilder.DELIMITER,
                orangeJuice.getCode(),
                SimpleProtocolMessageBuilder.DELIMITER);
        String protocolMessage = spmb.buildOrderMessage(orangeJuice, ZERO, false);
        assertEquals(expectedMessage, protocolMessage);

    }

}
