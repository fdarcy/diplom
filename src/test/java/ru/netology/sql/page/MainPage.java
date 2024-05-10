package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement paymentButton = $(byXpath("//button[normalize-space()='Купить']"));
    private final SelenideElement paymentInCreditButton = $(byXpath("//button[normalize-space()='Купить в кредит']"));
    private final SelenideElement cardNumberInput = $(byXpath("//span[normalize-space()='Номер карты']/span/input"));
    private final SelenideElement monthInput = $(byXpath("//span[normalize-space()='Месяц']/span/input"));
    private final SelenideElement yearInput = $(byXpath("//span[normalize-space()='Год']/span/input"));
    private final SelenideElement ownerInput = $(byXpath("//span[normalize-space()='Владелец']/span/input"));
    private final SelenideElement cvcInput = $(byXpath("//span[normalize-space()='CVC/CVV']/span/input"));
    private final SelenideElement submitButton = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[4]/button"));
    private final SelenideElement anyNotification = $(byClassName("notification_visible"));
    private final SelenideElement successNotification = $(byClassName("notification_status_ok"));
    private final SelenideElement errorNotification = $(byClassName("notification_status_error"));

    public void openPaymentForm() {
        paymentButton.click();
    }

    public void openPaymentInCreditForm() {
        paymentInCreditButton.click();
    }

    public void verifyApprovedNotificationShown() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void verifyErrorNotificationShown() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void verifyAnyNotificationShown() {
        anyNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void fillForm(DataHelper.CardInfo info) {
        cardNumberInput.setValue(info.getCardNumber());
        monthInput.setValue(info.getMonth());
        yearInput.setValue(info.getYear());
        ownerInput.setValue(info.getOwner());
        cvcInput.setValue(info.getCvc());
    }

    public void submitForm() {
        submitButton.click();
    }

    public void verifySubmitNotStarted() {
        submitButton.shouldBe(Condition.enabled, Duration.ofSeconds(1));
    }

    public void verifySubmitStarted() {
        submitButton.shouldBe(Condition.disabled, Duration.ofSeconds(1));
    }
}
