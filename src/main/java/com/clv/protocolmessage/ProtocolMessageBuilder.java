package com.clv.protocolmessage;

import com.clv.order.DrinkType;
import com.clv.order.SugarQuantity;

public interface ProtocolMessageBuilder {

    String buildOrderMessage(DrinkType drink, SugarQuantity sugarQuantity, boolean isExtraHot);
    String buildInfoMessage(String message);
}
