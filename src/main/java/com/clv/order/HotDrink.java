package com.clv.order;

class HotDrink extends Drink {

    private final SugarQuantity sugarQuantity;
    private final boolean isExtraHot;


    HotDrink(DrinkType drinkType, SugarQuantity sugarQuantity, boolean isExtraHot) {
        super(drinkType);
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
