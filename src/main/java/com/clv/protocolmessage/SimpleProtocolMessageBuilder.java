package com.clv.protocolmessage;

import com.clv.order.DrinkType;
import com.clv.order.SugarQuantity;

import static com.clv.order.SugarQuantity.ZERO;

public class SimpleProtocolMessageBuilder implements ProtocolMessageBuilder {

    public static final String INFO_MESSAGE_CODE = "M";
    public static final String STICK_CODE = "0";
    public static final String DELIMITER = ":";
    public static final String EXTRA_HOT_CODE = "h";
    public static final String ABSENT_CODE = "";

    @Override
    public String buildOrderMessage(DrinkType drink, SugarQuantity sugarQuantity, boolean isExtraHot ) {
        return buildMessage(buildDrinkCode(drink, isExtraHot),
                buildSugarCode(sugarQuantity),
                buildStickCode(sugarQuantity));
    }

    @Override
    public String buildInfoMessage(String message) {
        return buildMessage(
                INFO_MESSAGE_CODE,
                message);
    }

    private String buildDrinkCode(DrinkType drink, boolean extraHot) {
        return drink.getCode() + buildExtraHotCode(extraHot);
    }

    private String buildExtraHotCode(boolean extraHot) {
        return extraHot ? EXTRA_HOT_CODE : ABSENT_CODE;
    }

    private String buildSugarCode(SugarQuantity sugarQuantity) {
        return sugarQuantity != ZERO ? sugarQuantity.getStringValue() : ABSENT_CODE;
    }

    private String buildStickCode(SugarQuantity sugarQuantity) {
        return sugarQuantity != ZERO ? STICK_CODE : ABSENT_CODE;
    }

    private String buildMessage(String...params) {
        return String.join(DELIMITER, params);
    }
}
