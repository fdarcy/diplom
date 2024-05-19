package ru.netology.sql.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPagePaymentInCreditTest {
    MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        mainPage = open("http://localhost:8080", MainPage.class);
        mainPage.openPaymentInCreditForm();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should show success notification with approved card")
    void shouldShowSuccessNotificationWithApprovedCard() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();
        mainPage.fillForm(approvedCardInfo);
        mainPage.submitForm();
        mainPage.verifyApprovedNotificationShown();

        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "APPROVED");
    }

    @Test
    @DisplayName("Should show error notification with declined card")
    void shouldShowErrorNotificationWithDeclinedCard() {
        var declinedCardInfo = DataHelper.getDeclinedCardInfo();
        mainPage.fillForm(declinedCardInfo);
        mainPage.submitForm();
        mainPage.verifyErrorNotificationShown();

        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "DECLINED");
    }

    @Test
    @DisplayName("form should not be submitted with empty card number")
    void formShouldNotBeSubmittedWithEmptyCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithEmptyCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero card number")
    void formShouldNotBeSubmittedWithZeroCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithZerosCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letter card number")
    void formShouldNotBeSubmittedWithLetterCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithLettersCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol card number")
    void formShouldNotBeSubmittedWithSymbolCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithSymbolsCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with fewer numbers card number")
    void formShouldNotBeSubmittedWithFewerNumbersCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithFewerCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one numbers card number")
    void formShouldNotBeSubmittedWithOneNumbersCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithOneNumberCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two numbers card number")
    void formShouldNotBeSubmittedWithTwoNumbersCardNumber() {
        mainPage.fillForm(DataHelper.getCardWithTwoNumberCardNumber());
        mainPage.submitForm();
        mainPage.verifyCardNumberHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty month")
    void formShouldNotBeSubmittedWithEmptyMonth() {
        mainPage.fillForm(DataHelper.getCardWithEmptyMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letter month")
    void formShouldNotBeSubmittedWithLetterMonth() {
        mainPage.fillForm(DataHelper.getCardWithLetterMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol month")
    void formShouldNotBeSubmittedWithSymbolMonth() {
        mainPage.fillForm(DataHelper.getCardWithSymbolMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero month")
    void formShouldNotBeSubmittedWithZeroMonth() {
        mainPage.fillForm(DataHelper.getCardWithTwoZeroMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with invalid month")
    void formShouldNotBeSubmittedWithInvalidMonth() {
        mainPage.fillForm(DataHelper.getCardWithInvalidMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty year")
    void formShouldNotBeSubmittedWithEmptyYear() {
        mainPage.fillForm(DataHelper.getCardWithEmptyYear());
        mainPage.submitForm();
        mainPage.verifyYearHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letters in year")
    void formShouldNotBeSubmittedWithLettersInYear() {
        mainPage.fillForm(DataHelper.getCardWithLettersYear());
        mainPage.submitForm();
        mainPage.verifyYearHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol in year")
    void formShouldNotBeSubmittedWithSymbolInYear() {
        mainPage.fillForm(DataHelper.getCardWithSymbolsYear());
        mainPage.submitForm();
        mainPage.verifyYearHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with old year in year")
    void formShouldNotBeSubmittedWithOldYearInYear() {
        mainPage.fillForm(DataHelper.getCardWithOldYear());
        mainPage.submitForm();
        mainPage.verifyYearHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should be submitted with next month and current year")
    void formShouldBeSubmittedWithNextMonthAndCurrentYear() {
        mainPage.fillForm(DataHelper.getCardWithNextMonth());
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
        mainPage.verifyApprovedNotificationShown();
        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "APPROVED");
    }

    @Test
    @DisplayName("form should be submitted with current month and year")
    void formShouldBeSubmittedWithCurrentMonthAndYear() {
        mainPage.fillForm(DataHelper.getCardWithCurrentMonthAndYear());
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
        mainPage.verifyApprovedNotificationShown();
        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "APPROVED");
    }

    @Test
    @DisplayName("form should not be submitted with past month and current year")
    void formShouldNotBeSubmittedWithPastMonthAndCurrentYear() {
        mainPage.fillForm(DataHelper.getCardWithPastMonth());
        mainPage.submitForm();
        mainPage.verifyMonthHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should be submitted for card with long expiration")
    void formShouldBeSubmittedForCardWithLongExpiration() {
        mainPage.fillForm(DataHelper.getCardWithLongExpiration());
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
        mainPage.verifyApprovedNotificationShown();
        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "APPROVED");
    }

    @Test
    @DisplayName("form should not be submitted for card that expires in past")
    void formShouldNotBeSubmittedForCardThatExpiresInPast() {
        mainPage.fillForm(DataHelper.getExpiredCard());
        mainPage.submitForm();
        mainPage.verifyYearHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty owner")
    void formShouldNotBeSubmittedWithEmptyOwner() {
        mainPage.fillForm(DataHelper.getCardWithEmptyOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with number owner")
    void formShouldNotBeSubmittedWithNumberOwner() {
        mainPage.fillForm(DataHelper.getCardWithNumberOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol owner")
    void formShouldNotBeSubmittedWithSymbolOwner() {
        mainPage.fillForm(DataHelper.getCardWithSymbolOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with сyrillic owner")
    void formShouldNotBeSubmittedWithCyrillicOwner() {
        mainPage.fillForm(DataHelper.getCardWithCyrillicOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted without space owner")
    void formShouldNotBeSubmittedWithWithoutSpaceOwner() {
        mainPage.fillForm(DataHelper.getCardWithoutSpaceOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with hyphen owner")
    void formShouldNotBeSubmittedWithWithHyphenOwner() {
        mainPage.fillForm(DataHelper.getCardWithHyphenOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one symbol owner")
    void formShouldNotBeSubmittedWithWithOneSymbolOwner() {
        mainPage.fillForm(DataHelper.getCardWithOneSymbolOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two symbol owner")
    void formShouldNotBeSubmittedWithWithTwoSymbolOwner() {
        mainPage.fillForm(DataHelper.getCardWithTwoSymbolOwner());
        mainPage.submitForm();
        mainPage.verifyOwnerHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty cvc")
    void formShouldNotBeSubmittedWithEmptyCvc() {
        mainPage.fillForm(DataHelper.getCardWithEmptyCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol cvc")
    void formShouldNotBeSubmittedWithSymbolCvc() {
        mainPage.fillForm(DataHelper.getCardWithSymbolCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letter cvc")
    void formShouldNotBeSubmittedWithLetterCvc() {
        mainPage.fillForm(DataHelper.getCardWithLetterCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero cvc")
    void formShouldNotBeSubmittedWithZeroCvc() {
        mainPage.fillForm(DataHelper.getCardWithZeroCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two numbers cvc")
    void formShouldNotBeSubmittedWithTwoNumbersCvc() {
        mainPage.fillForm(DataHelper.getCardWithTwoNumberCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one numbers cvc")
    void formShouldNotBeSubmittedWithOneNumbersCvc() {
        mainPage.fillForm(DataHelper.getCardWithOneNumberCvc());
        mainPage.submitForm();
        mainPage.verifyCvcHasValidation();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("order should be created after card submitted")
    void orderShouldBeCreatedAfterCardSubmitted() {
        clearDatabaseAndSubmitFormWithApprovedCard();

        var createdOrder = SQLHelper.getLastOrder();

        Assertions.assertNotNull(createdOrder);
    }

    @Test
    @DisplayName("credit request should be created after card submitted")
    void creditRequestShouldBeCreatedAfterCardSubmitted() {
        clearDatabaseAndSubmitFormWithApprovedCard();

        var createdCreditRequest = SQLHelper.getLastCreditRequest();

        Assertions.assertNotNull(createdCreditRequest);
    }

    @Test
    @DisplayName("credit request and order should be linked correctly after card submitted")
    void creditRequestAndOrderShouldBeLinkedCorrectlyAfterCardSubmitted() {
        clearDatabaseAndSubmitFormWithApprovedCard();

        var createdOrder = SQLHelper.getLastOrder();
        var createdCreditRequest = SQLHelper.getLastCreditRequest();

        Assertions.assertEquals(createdOrder.getCreditId(), createdCreditRequest.getId());
    }

    private void clearDatabaseAndSubmitFormWithApprovedCard() {
        SQLHelper.clearDatabase();

        var approvedCardInfo = DataHelper.getApprovedCardInfo();
        mainPage.fillForm(approvedCardInfo);
        mainPage.submitForm();
        mainPage.verifyAnyNotificationShown();
    }
}
