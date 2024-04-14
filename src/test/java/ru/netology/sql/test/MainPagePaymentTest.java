package ru.netology.sql.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = open("http://localhost:8080", MainPage.class);
    }

    @Test
    @DisplayName("Should show success notification with approved card")
    void shouldShowSuccessNotificationWithApprovedCard() {
        mainPage.openBuyForm();
        var approvedCardInfo = DataHelper.getApprovedCardInfo();
        mainPage.submitForm(approvedCardInfo);
        mainPage.verifyApprovedNotificationShown();
    }
}
