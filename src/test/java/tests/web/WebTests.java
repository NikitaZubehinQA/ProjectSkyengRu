package tests.web;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@Tag("ui")
@Owner(value = "Zubehin Nikita")
public class WebTests extends TestBase {
    static Stream<List<String>> headerItemsProvider() {
        return Stream.of(List.of("Взрослым", "Тест на уровень",
                "Преподаватели", "Учителям", "Другие курсы"));
    }
    @Test
    @DisplayName("В верхнем меню 5 разделов")
    @Severity(SeverityLevel.CRITICAL)
    public void checkHeaderMenuSectionsTest() {

        step("Перейти на главную страницу", () ->
                mainPage.openPage());
        step("Проверить, что верхнее меню состоит из 5 разделов", () ->
                mainPage.verifyHeaderMenuSize());
    }

    @ParameterizedTest(name = "В верхнем меню отображаются кнопки {0}")
    @MethodSource("headerItemsProvider")
    @Severity(SeverityLevel.CRITICAL)
    public void checkHeaderMenuButtonsTest(List<String> buttons) {

        step("Перейти на главную страницу", () ->
                mainPage.openPage());
        step("Проверить, что в верхнем меню есть кнопки {0}", () ->
                mainPage.verifyHeaderMenuItems(buttons));
    }

    @ParameterizedTest(name = "В разделе {0} есть формат {1}")
    @CsvSource(value = {"Взрослым, Самостоятельное обучение"})
    @Severity(SeverityLevel.NORMAL)
    public void checkProductFormForMenuItemTest(String menuItem, String productForm) {

        step("Перейти на главную страницу", () ->
                mainPage.openPage());
        step("Навести курсор на раздел", () ->
                mainPage.hoverMenuItem(menuItem));
        step("Проверить, что в каталоге есть нужный формат", () ->
                mainPage.verifyProduct(productForm));
    }

    @Test
    @Disabled("Нестабильный UI/редиректы в CI (Jenkins + Selenoid).")
    @DisplayName("По пункту 'Тест на уровень' осуществляется переход к тесту на подбор курса")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSwitchingToCoursesTestThruLink() {

        step("Перейти на главную страницу", () ->
                mainPage.openPage());
        step("Кликнуть Тест на уровень", () ->
                mainPage.clickPassTest());
        step("Проверить, что тест отобразился", () ->
                mainPage.verifyQuizAppears());
    }

    @Disabled("Нестабильный UI/редиректы в CI (Jenkins + Selenoid).")
    @ParameterizedTest(name = "После выбора цели обучения {0} и уровня языка {1} отображаются подходящие курсы")
    @CsvSource(value = {"Для работы, Средний", "Для заграничных поездок, Начинающий"})
    @Severity(SeverityLevel.NORMAL)
    public void checkCoursesResultDependsOnUserPurposeAndLevel(String purpose, String level) {

        step("Перейти на главную страницу", () ->
                mainPage.openPage());
        step("Выбрать цель обучения и уровень владения языком", () ->
                mainPage.setPurposeAndLevel(purpose, level));
        step("Проверить, что отобразился прогресс бар", () ->
                mainPage.verifyProgressBarAppears());
        step("Проверить, что отобразились подходящие курсы", () ->
                mainPage.verifySuitableCourses());
    }

    @ParameterizedTest(name = "В блоке Self-Study должна быть фича {0}")
    @CsvSource(value = {"Говорите без стресса и отрабатывайте грамматику и лексику."})
    @Severity(SeverityLevel.NORMAL)
    public void checkIntroFeatureExistsOnSelfStudyPageTest(String featureText) {

        step("Перейти на страницу Self-Study", () ->
                selfStudyPage.openPage());
        step("Проверить заголовок страницы", () ->
                selfStudyPage.verifyHeader());
        step("Проверить, что на странице есть нужная фича", () ->
                selfStudyPage.verifyIntroFeatureExists(featureText));
    }

    @ParameterizedTest(name = "На странице {0} отображается {1}")
    @CsvSource(value = {"/clubs, Расписание на ближайшие 7 дней"})
    @Severity(SeverityLevel.NORMAL)
    public void checkFeatureExistsOnPage(String page, String feature) {

        step("Перейти на страницу {0}", () ->
                clubsPage.openPage(page));
        step("Проверить, что на странице есть {1}", () ->
                clubsPage.verifyFeature(feature));
    }
}
