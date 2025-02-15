package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

/**
 * Тестовый класс для проверки функционала класса Burger с использованием моков.
 */
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();

        // Настраиваем поведение моков для Bun
        when(bun.getName()).thenReturn("Mocked Bun");
        when(bun.getPrice()).thenReturn(100.0f);

        // Настраиваем поведение моков для Ingredient (соус)
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Mocked Sauce");
        when(sauce.getPrice()).thenReturn(20.0f);

        // Настраиваем поведение моков для Ingredient (начинка)
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("Mocked Filling");
        when(filling.getPrice()).thenReturn(80.0f);
    }

    /**
     * Проверяем, что метод setBuns() корректно устанавливает булочку.
     */
    @Test
    public void testSetBunsSetsBunCorrectly() {
        burger.setBuns(bun);
        assertEquals("Булочка не установилась корректно",
                bun, burger.bun);
    }

    /**
     * Проверяем, что метод addIngredient() корректно добавляет ингредиент.
     */
    @Test
    public void testAddIngredientAddsIngredientCorrectly() {
        burger.addIngredient(sauce);
        assertEquals("Ингредиент не был добавлен в список",
                1, burger.ingredients.size());
        assertEquals("Добавленный ингредиент не соответствует ожидаемому",
                sauce, burger.ingredients.get(0));
    }

    /**
     * Проверяем, что метод removeIngredient() корректно удаляет ингредиент по индексу.
     */
    @Test
    public void testRemoveIngredientRemovesIngredientCorrectly() {
        // Добавляем ингредиенты
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        // Удаляем первый ингредиент
        burger.removeIngredient(0);
        assertEquals("Ингредиент не был удален из списка",
                1, burger.ingredients.size());
        assertEquals("Оставшийся ингредиент не соответствует ожидаемому",
                filling, burger.ingredients.get(0));
    }

    /**
     * Проверяем, что метод moveIngredient() корректно перемещает ингредиент на новый индекс.
     */
    @Test
    public void testMoveIngredientMovesIngredientCorrectly() {
        // Создаем дополнительные моки ингредиентов
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        // Добавляем ингредиенты
        burger.addIngredient(ingredient1); // Index 0
        burger.addIngredient(ingredient2); // Index 1
        burger.addIngredient(ingredient3); // Index 2

        // Перемещаем ингредиент с индекса 2 на индекс 0
        burger.moveIngredient(2, 0);

        assertEquals("Ингредиент не переместился на нужный индекс",
                ingredient3, burger.ingredients.get(0));
        assertEquals("Размер списка после перемещения ингредиента изменился",
                3, burger.ingredients.size());
    }

    /**
     * Проверяем, что метод getPrice() корректно рассчитывает цену бургера.
     */
    @Test
    public void testGetPriceCalculatesPriceCorrectly() {
        burger.setBuns(bun); // Цена булочки 100.0f
        burger.addIngredient(sauce);    // Цена соуса 20.0f
        burger.addIngredient(filling);  // Цена начинки 80.0f

        // Ожидаемая цена: (100 * 2) + 20 + 80 = 300.0f
        float expectedPrice = 300.0f;
        assertEquals("Цена бургера рассчитана неверно",
                expectedPrice, burger.getPrice(), 0.0f);
    }

    /**
     * Проверяем, что метод getPrice() корректно работает без ингредиентов.
     */
    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns(bun); // Только булочка
        // Ожидаемая цена: (100 * 2) = 200.0f
        float expectedPrice = 200.0f;
        assertEquals("Цена бургера без ингредиентов рассчитана неверно",
                expectedPrice, burger.getPrice(), 0.0f);
    }

    /**
     * Проверяем, что метод getPrice() выбрасывает NullPointerException, если булочка не установлена.
     */
    @Test(expected = NullPointerException.class)
    public void testGetPriceWithoutBunThrowsException() {
        // Не устанавливаем булочку
        burger.addIngredient(sauce);
        burger.getPrice();
    }

    /**
     * Проверяем, что метод getReceipt() возвращает корректный чек.
     */
    @Test
    public void testGetReceiptReturnsCorrectReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("= %s %s =%n", sauce.getType().toString().toLowerCase(), sauce.getName()));
        receipt.append(String.format("= %s %s =%n", filling.getType().toString().toLowerCase(), filling.getName()));
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        String expectedReceipt = receipt.toString();

        assertEquals("Чек бургера сформирован неверно",
                expectedReceipt, burger.getReceipt());
    }

    /**
     * Проверяем, что метод getReceipt() корректно обрабатывает ситуацию без ингредиентов.
     */
    @Test
    public void testGetReceiptWithNoIngredients() {
        burger.setBuns(bun); // Только булочка

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        String expectedReceipt = receipt.toString();

        assertEquals("Чек бургера без ингредиентов сформирован неверно",
                expectedReceipt, burger.getReceipt());
    }

    /**
     * Проверяем, что метод removeIngredient() выбрасывает IndexOutOfBoundsException при некорректном индексе.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndexThrowsException() {
        burger.removeIngredient(0); // Пустой список ингредиентов
    }

    /**
     * Проверяем, что метод moveIngredient() выбрасывает IndexOutOfBoundsException при некорректных индексах.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidIndicesThrowsException() {
        burger.addIngredient(sauce);
        burger.moveIngredient(1, 0); // Индекс 1 вне диапазона
    }
}
