package com.clv.order;

public class HotDrinkOrder extends Order {

    private final SugarQuantity sugarQuantity;
    private final boolean isExtraHot;


    public HotDrinkOrder(HotDrinkType drinkType, SugarQuantity sugarQuantity, boolean isExtraHot, int money) {
        super(drinkType, money);
        this.sugarQuantity = sugarQuantity != null ? sugarQuantity : SugarQuantity.ZERO;
        this.isExtraHot = isExtraHot;
    }

    @Override
    public SugarQuantity getSugarQuantity() {
        return sugarQuantity;
    }

    @Override
    public boolean isExtraHot() {
        return isExtraHot;
    }
}
