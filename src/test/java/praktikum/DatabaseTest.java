package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки функционала класса Database.
 */
public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    /**
     * Проверяем, что метод availableBuns() возвращает список с правильным количеством булочек.
     */
    @Test
    public void testAvailableBunsSize() {
        List<Bun> buns = database.availableBuns();
        assertEquals("Количество булочек в базе данных неверно", 3, buns.size());
    }

    /**
     * Проверяем, что метод availableIngredients() возвращает список с правильным количеством ингредиентов.
     */
    @Test
    public void testAvailableIngredientsSize() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals("Количество ингредиентов в базе данных неверно", 6, ingredients.size());
    }

    /**
     * Проверяем, что метод availableBuns() возвращает список, содержащий ожидаемые булочки.
     */
    @Test
    public void testAvailableBunsContents() {
        List<Bun> buns = database.availableBuns();

        assertTrue("Список булочек не содержит 'black bun'",
                buns.stream().anyMatch(bun -> bun.getName().equals("black bun") && bun.getPrice() == 100));

        assertTrue("Список булочек не содержит 'white bun'",
                buns.stream().anyMatch(bun -> bun.getName().equals("white bun") && bun.getPrice() == 200));

        assertTrue("Список булочек не содержит 'red bun'",
                buns.stream().anyMatch(bun -> bun.getName().equals("red bun") && bun.getPrice() == 300));
    }

    /**
     * Проверяем, что метод availableIngredients() возвращает список, содержащий ожидаемые ингредиенты.
     */
    @Test
    public void testAvailableIngredientsContents() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertTrue("Список ингредиентов не содержит 'hot sauce'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.SAUCE &&
                                ingredient.getName().equals("hot sauce") &&
                                ingredient.getPrice() == 100));

        assertTrue("Список ингредиентов не содержит 'sour cream'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.SAUCE &&
                                ingredient.getName().equals("sour cream") &&
                                ingredient.getPrice() == 200));

        assertTrue("Список ингредиентов не содержит 'chili sauce'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.SAUCE &&
                                ingredient.getName().equals("chili sauce") &&
                                ingredient.getPrice() == 300));

        assertTrue("Список ингредиентов не содержит 'cutlet'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.FILLING &&
                                ingredient.getName().equals("cutlet") &&
                                ingredient.getPrice() == 100));

        assertTrue("Список ингредиентов не содержит 'dinosaur'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.FILLING &&
                                ingredient.getName().equals("dinosaur") &&
                                ingredient.getPrice() == 200));

        assertTrue("Список ингредиентов не содержит 'sausage'",
                ingredients.stream().anyMatch(ingredient ->
                        ingredient.getType() == IngredientType.FILLING &&
                                ingredient.getName().equals("sausage") &&
                                ingredient.getPrice() == 300));
    }

    /**
     * Проверяем, что список булочек не содержит дубликатов.
     */
    @Test
    public void testAvailableBunsNoDuplicates() {
        List<Bun> buns = database.availableBuns();
        long uniqueCount = buns.stream().map(Bun::getName).distinct().count();
        assertEquals("Список булочек содержит дубликаты", buns.size(), uniqueCount);
    }

    /**
     * Проверяем, что список ингредиентов не содержит дубликатов.
     */
    @Test
    public void testAvailableIngredientsNoDuplicates() {
        List<Ingredient> ingredients = database.availableIngredients();
        long uniqueCount = ingredients.stream().map(Ingredient::getName).distinct().count();
        assertEquals("Список ингредиентов содержит дубликаты", ingredients.size(), uniqueCount);
    }
}
