package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки функционала класса Bun.
 */
@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private final Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
        this.bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][] {
                // {name, price}
                { "Black Bun", 100.0f },
                { "Gluten Free Bun", 250.0f },
                { "", 0.0f },                         // Пустое название, нулевая цена
                { "Negative Price Bun", -50.0f },     // Отрицательная цена
                { null, 100.0f },                     // null в названии
                { "Big Price Bun", Float.MAX_VALUE }, // Максимально возможная цена
                { "Tiny Price Bun", Float.MIN_VALUE } // Минимально положительная цена
        };
    }

    /**
     * Проверяем, что метод getName() возвращает правильное название булочки.
     */
    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("Метод getName() возвращает неверное название булочки",
                name, bun.getName());
    }

    /**
     * Проверяем, что метод getPrice() возвращает правильную цену булочки.
     */
    @Test
    public void testGetPriceReturnsCorrectPrice() {
        assertEquals("Метод getPrice() возвращает неверную цену булочки",
                price, bun.getPrice(), 0.0f);
    }
}
