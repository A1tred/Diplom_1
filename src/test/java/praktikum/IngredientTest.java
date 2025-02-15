package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки функционала класса Ingredient.
 */
@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private final Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getIngredientData() {
        return new Object[][] {
                // {type, name, price}
                { IngredientType.SAUCE, "Hot Sauce", 100.0f },
                { IngredientType.FILLING, "Cutlet", 200.0f },
                { IngredientType.FILLING, "", 0.0f },                    // Пустое название, нулевая цена
                { IngredientType.SAUCE, "Negative Price", -10.0f },      // Отрицательная цена
                { IngredientType.FILLING, null, 100.0f },                // null в названии
                { null, "No Type", 100.0f },                             // null в типе
                { IngredientType.SAUCE, "Big Price", Float.MAX_VALUE },  // Максимально возможная цена
                { IngredientType.SAUCE, "Tiny Price", Float.MIN_VALUE }, // Минимально положительная цена
        };
    }

    /**
     * Проверяем, что метод getType() возвращает правильный тип ингредиента.
     */
    @Test
    public void testGetTypeReturnsCorrectType() {
        assertEquals("Метод getType() возвращает неверный тип ингредиента",
                type, ingredient.getType());
    }

    /**
     * Проверяем, что метод getName() возвращает правильное название ингредиента.
     */
    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("Метод getName() возвращает неверное название ингредиента",
                name, ingredient.getName());
    }

    /**
     * Проверяем, что метод getPrice() возвращает правильную цену ингредиента.
     */
    @Test
    public void testGetPriceReturnsCorrectPrice() {
        assertEquals("Метод getPrice() возвращает неверную цену ингредиента",
                price, ingredient.getPrice(), 0.0f);
    }
}
