
package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;


public class SearchTests extends TestBase {
    @Test
    void androidSuccessfulSearchTest() {
        step("Вписать в поиск 'Appium'", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверить поиск статей", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void androidUnsuccessfulOpenArticleTest() {
        step("Напечатать в поиске слово 'Aeroflot'", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Aeroflot");
        });
        step("Открыть первую статью", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click();
        });

        step("Проверить текст сообщения об ошибке", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("An error occurred"));
        });
    }
}