package com.clv.order;

import org.junit.jupiter.api.Test;

import static com.clv.order.DrinkType.*;
import static com.clv.order.SugarQuantity.*;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkFactoryTest {

    private final DrinkFactory drinkFactory = new DrinkFactory();

    @Test
    public void givenCoffee_whenCreateDrink_theReturnHotDrinkWithoutSugarAndNotExtraHot() {
        Drink drink = drinkFactory.createDrink(COFFEE);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(COFFEE, drink.getDrinkType());
        assertEquals(ZERO, drink.getSugarQuantity());
        assertFalse(drink.isExtraHot());
    }

    @Test
    public void givenTea_whenCreateDrink_theReturnHotDrinkWithoutSugarAndNotExtraHot() {
        Drink drink = drinkFactory.createDrink(TEA);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(TEA, drink.getDrinkType());
        assertEquals(ZERO, drink.getSugarQuantity());
        assertFalse(drink.isExtraHot());
    }

    @Test
    public void givenChocolate_whenCreateDrink_theReturnHotDrinkWithoutSugarAndNotExtraHot() {
        Drink drink = drinkFactory.createDrink(CHOCOLATE);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(CHOCOLATE, drink.getDrinkType());
        assertEquals(ZERO, drink.getSugarQuantity());
        assertFalse(drink.isExtraHot());
    }

    @Test
    public void givenOrangeJuice_whenCreateDrink_theReturnCoolDrink() {
        Drink drink = drinkFactory.createDrink(ORANGE_JUICE);
        assertEquals(CoolDrink.class, drink.getClass());
        assertEquals(ORANGE_JUICE, drink.getDrinkType());
    }

    @Test
    public void givenExtraHotCoffeeWithOneSugar_whenCreateDrink_theReturnHotDrinkWithOneSugarAndExtraHot() {
        Drink drink = drinkFactory.createDrink(COFFEE, ONE, true);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(COFFEE, drink.getDrinkType());
        assertEquals(ONE, drink.getSugarQuantity());
        assertTrue(drink.isExtraHot());
    }

    @Test
    public void givenExtraHotTeaWithoutSugar_whenCreateDrink_theReturnHotDrinkWithoutSugarAndExtraHot() {
        Drink drink = drinkFactory.createDrink(TEA, ZERO, true);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(TEA, drink.getDrinkType());
        assertEquals(ZERO, drink.getSugarQuantity());
        assertTrue(drink.isExtraHot());
    }

    @Test
    public void givenChocolateWithTwoSugar_whenCreateDrink_theReturnHotDrinkWithoutTwoSugarAndNotExtraHot() {
        Drink drink = drinkFactory.createDrink(CHOCOLATE, TWO, false);
        assertEquals(HotDrink.class, drink.getClass());
        assertEquals(CHOCOLATE, drink.getDrinkType());
        assertEquals(TWO, drink.getSugarQuantity());
        assertFalse(drink.isExtraHot());
    }

    @Test
    public void givenExtraHotOrangeJuiceWithTwoSugar_whenCreateDrink_theReturnCoolDrink() {
        Drink drink = drinkFactory.createDrink(ORANGE_JUICE, TWO, true);
        assertEquals(CoolDrink.class, drink.getClass());
        assertEquals(ORANGE_JUICE, drink.getDrinkType());
    }
}