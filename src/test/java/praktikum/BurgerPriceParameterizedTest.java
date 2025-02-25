package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private Burger burger;
    private Bun bun;
    private List<Ingredient> ingredients;
    private float expectedPrice;

    public BurgerPriceParameterizedTest(Bun bun, List<Ingredient> ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                // {bun, ingredients, expectedPrice}
                { new Bun("Bun A", 50.0f),
                        Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "Sauce A", 10.0f),
                                new Ingredient(IngredientType.FILLING, "Filling A", 40.0f)
                        ),
                        150.0f // (50 * 2) + 10 + 40
                },
                { new Bun("Bun B", 80.0f),
                        Arrays.asList(
                                new Ingredient(IngredientType.FILLING, "Filling B1", 30.0f),
                                new Ingredient(IngredientType.FILLING, "Filling B2", 20.0f)
                        ),
                        210.0f // (80 * 2) + 30 + 20
                },
                { new Bun("Bun C", 60.0f),
                        Arrays.asList(),
                        120.0f // (60 * 2)
                }
        };
    }

    /**
     * Проверяем, что метод getPrice() корректно рассчитывает цену бургера с различными данными.
     */
    @Test
    public void testGetPriceWithDifferentData() {
        burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals("Цена бургера рассчитана неверно",
                expectedPrice, burger.getPrice(), 0.0f);
    }
}
