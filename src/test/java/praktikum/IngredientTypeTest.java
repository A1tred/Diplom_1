package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки перечисления IngredientType.
 */
public class IngredientTypeTest {

    /**
     * Проверяем, что перечисление IngredientType содержит значение SAUCE.
     */
    @Test
    public void testIngredientTypeContainsSauce() {
        IngredientType type = IngredientType.valueOf("SAUCE");
        assertEquals("Перечисление IngredientType должно содержать SAUCE",
                IngredientType.SAUCE, type);
    }

    /**
     * Проверяем, что перечисление IngredientType содержит значение FILLING.
     */
    @Test
    public void testIngredientTypeContainsFilling() {
        IngredientType type = IngredientType.valueOf("FILLING");
        assertEquals("Перечисление IngredientType должно содержать FILLING",
                IngredientType.FILLING, type);
    }

    /**
     * Проверяем количество значений в перечислении IngredientType.
     */
    @Test
    public void testIngredientTypeValuesCount() {
        IngredientType[] values = IngredientType.values();
        assertEquals("Количество значений в IngredientType должно быть 2", 2, values.length);
    }
}
