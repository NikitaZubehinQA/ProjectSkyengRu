package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class SelfStudyPage {

    private final String SELF_STUDY_HEADER = "Самостоятельные занятия английским с ИИ";

    private final SelenideElement pageHeader = $("h1"),
    introFeatureText = $$(".intro-feature-item-text").first();


    public SelfStudyPage openPage() {
        open(baseUrl + "/self-study");
        return this;
    }

    public SelfStudyPage verifyHeader() {
        pageHeader.shouldHave(text(SELF_STUDY_HEADER));
        return this;
    }

    public SelfStudyPage verifyIntroFeatureExists(String featureText) {
        introFeatureText.should(exist);
        $$(".intro-feature-item-text").findBy(text(featureText)).should(exist);
        return this;
    }
}
