package ru.netology.sql.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.MainPage;

import java.util.Date;

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
        mainPage.refillForm(approvedCardInfo);
        mainPage.submitForm();
        mainPage.verifyApprovedNotificationShown();

        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "APPROVED");
    }

    @Test
    @DisplayName("Should show error notification with declined card")
    void shouldShowErrorNotificationWithDeclinedCard() {
        var declinedCardInfo = DataHelper.getDeclinedCardInfo();
        mainPage.refillForm(declinedCardInfo);
        mainPage.submitForm();
        mainPage.verifyErrorNotificationShown();

        var createdCreditRequest = SQLHelper.getLastCreditRequest();
        Assertions.assertEquals(createdCreditRequest.getStatus(), "DECLINED");
    }


    @Test
    @DisplayName("form should not be submitted with invalid card")
    void formShouldNotBeSubmittedWithInvalidCard() {
        var invalidCardInfo = DataHelper.getInvalidCardInfo();
        mainPage.refillForm(invalidCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }


    @Test
    @DisplayName("form should not be submitted with empty card number")
    void formShouldNotBeSubmittedWithEmptyCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero card number")
    void formShouldNotBeSubmittedWithZeroCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "0000 0000 0000 0000",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letter card number")
    void formShouldNotBeSubmittedWithLetterCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "ftgujkhhj",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol card number")
    void formShouldNotBeSubmittedWithSymbolCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "4444 #$^% (&%^ 1111",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with fewer numbers card number")
    void formShouldNotBeSubmittedWithFewerNumbersCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "4444 4444 4444 444",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one numbers card number")
    void formShouldNotBeSubmittedWithOneNumbersCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "4",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two numbers card number")
    void formShouldNotBeSubmittedWithTwoNumbersCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                "44",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty month")
    void formShouldNotBeSubmittedWithEmptyMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                "",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letter month")
    void formShouldNotBeSubmittedWithLetterMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                "п5",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol month")
    void formShouldNotBeSubmittedWithSymbolMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                "№;",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero month")
    void formShouldNotBeSubmittedWithZeroMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                "00",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with invalid month")
    void formShouldNotBeSubmittedWithInvalidMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                "13",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }



    @Test
    @DisplayName("form should not be submitted with empty year")
    void formShouldNotBeSubmittedWithEmptyYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                "",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with letters in year")
    void formShouldNotBeSubmittedWithLettersInYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                "year 2024",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol in year")
    void formShouldNotBeSubmittedWithSymbolInYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                "№;",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with old year in year")
    void formShouldNotBeSubmittedWithOldYearInYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                "23",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }


    @Test
    @DisplayName("form should be submitted with next month and current year")
    void formShouldBeSubmittedWithNextMonthAndCurrentYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = new Date();

        var monthIndex = now.getMonth();
        var year = now.getYear() + 1900;
        var yearString = String.valueOf(year);

        var trimmedYear = yearString.length() > 2 ? yearString.substring(yearString.length() - 2) : yearString;

        var month = monthIndex + 2;
        var monthStringFormatted = String.format("%02d", month);

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                monthStringFormatted,
                trimmedYear,
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
    }

    @Test
    @DisplayName("form should be submitted with current month and year")
    void formShouldBeSubmittedWithCurrentMonthAndYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = new Date();

        var monthIndex = now.getMonth();
        var year = now.getYear() + 1900;
        var yearString = String.valueOf(year);

        var trimmedYear = yearString.length() > 2 ? yearString.substring(yearString.length() - 2) : yearString;

        var month = monthIndex + 1;
        var monthStringFormatted = String.format("%02d", month);

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                monthStringFormatted,
                trimmedYear,
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
    }

    @Test
    @DisplayName("form should not be submitted with past month and current year")
    void formShouldNotBeSubmittedWithPastMonthAndCurrentYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = new Date();

        var monthIndex = now.getMonth();
        var year = now.getYear() + 1900;
        var yearString = String.valueOf(year);

        var trimmedYear = yearString.length() > 2 ? yearString.substring(yearString.length() - 2) : yearString;

        var month = monthIndex;
        var monthStringFormatted = String.format("%02d", month);

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                monthStringFormatted,
                trimmedYear,
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }


    @Test
    @DisplayName("form should be submitted for card with long expiration")
    void formShouldBeSubmittedForCardWithLongExpiration() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = new Date();

        var monthIndex = now.getMonth();
        var year = now.getYear() + 1900 + 10;
        var yearString = String.valueOf(year);

        var trimmedYear = yearString.length() > 2 ? yearString.substring(yearString.length() - 2) : yearString;

        var month = monthIndex + 1;
        var monthStringFormatted = String.format("%02d", month);

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                monthStringFormatted,
                trimmedYear,
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitStarted();
    }

    @Test
    @DisplayName("form should not be submitted for card that expires in past")
    void formShouldNotBeSubmittedForCardThatExpiresInPast() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = new Date();

        var monthIndex = now.getMonth();
        var year = now.getYear() + 1900 - 10;
        var yearString = String.valueOf(year);

        var trimmedYear = yearString.length() > 2 ? yearString.substring(yearString.length() - 2) : yearString;

        var month = monthIndex + 1;
        var monthStringFormatted = String.format("%02d", month);

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                monthStringFormatted,
                trimmedYear,
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty owner")
    void formShouldNotBeSubmittedWithEmptyOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with number owner")
    void formShouldNotBeSubmittedWithENumberOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan123",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol owner")
    void formShouldNotBeSubmittedWithSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan@#",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with сyrillic owner")
    void formShouldNotBeSubmittedWithCyrillicOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Иван Иванов",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted without space owner")
    void formShouldNotBeSubmittedWithWithoutSpaceOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "IvanIvanov",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with hyphen owner")
    void formShouldNotBeSubmittedWithWithHyphenOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan-Ivanov",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one symbol owner")
    void formShouldNotBeSubmittedWithWithOneSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "I",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two symbol owner")
    void formShouldNotBeSubmittedWithWithTwoSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Iv",
                approvedCardInfo.getCvc()
        );

        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with empty cvc")
    void formShouldNotBeSubmittedWithEmptyCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                ""
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with symbol cvc")
    void formShouldNotBeSubmittedWithSymbolCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "№;%"
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }


    @Test
    @DisplayName("form should not be submitted with letter cvc")
    void formShouldNotBeSubmittedWithLetterCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "asd"
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with zero cvc")
    void formShouldNotBeSubmittedWithZeroCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "000"
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with two numbers cvc")
    void formShouldNotBeSubmittedWithTwoNumbersCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "12"
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
        mainPage.verifySubmitNotStarted();
    }

    @Test
    @DisplayName("form should not be submitted with one numbers cvc")
    void formShouldNotBeSubmittedWithOneNumbersCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var changedCardInfo = new DataHelper.CardInfo(
                approvedCardInfo.getCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "1"
        );
        mainPage.refillForm(changedCardInfo);
        mainPage.submitForm();
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
        mainPage.refillForm(approvedCardInfo);
        mainPage.submitForm();
        mainPage.verifyAnyNotificationShown();
    }

    private void clearDatabaseAndSubmitFormWithDeclinedCard() {
        SQLHelper.clearDatabase();

        var declinedCardInfo = DataHelper.getDeclinedCardInfo();
        mainPage.refillForm(declinedCardInfo);
        mainPage.submitForm();
        mainPage.verifyAnyNotificationShown();
    }
}
