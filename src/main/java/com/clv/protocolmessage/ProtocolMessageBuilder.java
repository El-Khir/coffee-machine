package com.clv.protocolmessage;

import com.clv.order.Drink;

public interface ProtocolMessageBuilder {

    String buildOrderMessage(Drink drink);
    String buildInfoMessage(String message);
}
