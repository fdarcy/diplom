package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement paymentButton = $("#root > div > button:nth-child(3)");
    private final SelenideElement paymentInCreditButton = $("#root > div > button.button.button_view_extra.button_size_m.button_theme_alfa-on-white");
    private final SelenideElement cardNumberInput = $("#root > div > form > fieldset > div:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement monthInput = $("#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement yearInput = $("#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__box > input");
    private final SelenideElement ownerInput = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement cvcInput = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__box > input");
    private final SelenideElement submitButton = $("#root > div > form > fieldset > div:nth-child(4) > button");
    private final SelenideElement anyNotification = $("#root > div > div.notification.notification_visible");
    private final SelenideElement successNotification = $("#root > div > div.notification.notification_visible.notification_status_ok.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white");
    private final SelenideElement errorNotification = $("#root > div > div.notification.notification_visible.notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white");

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

    public void refillForm(DataHelper.CardInfo info) {
        cardNumberInput.clear();
        monthInput.clear();
        yearInput.clear();
        ownerInput.clear();
        cvcInput.clear();

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
