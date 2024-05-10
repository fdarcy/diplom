package ru.netology.sql.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));
    private static DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    private static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
    private static String correctCvcFormat = "%03d";

    private DataHelper() {
    }

    public static CardInfo getCardWithOneNumberCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "1"
        );
    }

    public static CardInfo getCardWithTwoNumberCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "12"
        );
    }

    public static CardInfo getCardWithZeroCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "000"
        );
    }

    public static CardInfo getCardWithLetterCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "asd"
        );
    }

    public static CardInfo getCardWithSymbolCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                "№;%"
        );
    }

    public static CardInfo getCardWithEmptyCvc() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                ""
        );
    }

    public static CardInfo getCardWithTwoSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Iv",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithOneSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "I",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithHyphenOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan-Ivanov",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithoutSpaceOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "IvanIvanov",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithCyrillicOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Иван Иванов",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithSymbolOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan@#",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithNumberOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "Ivan123",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithEmptyOwner() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                "",
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getExpiredCard() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = LocalDate.now();
        var tenYearsBeforeNow = now.plusYears(-10);

        return new CardInfo(
                getApprovedCardNumber(),
                tenYearsBeforeNow.format(monthFormatter),
                tenYearsBeforeNow.format(yearFormatter),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithLongExpiration() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = LocalDate.now();
        var tenYearsInFuture = now.plusYears(10);

        return new CardInfo(
                getApprovedCardNumber(),
                tenYearsInFuture.format(monthFormatter),
                tenYearsInFuture.format(yearFormatter),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithPastMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = LocalDate.now();
        var previousMonth = now.plusMonths(-1);

        return new CardInfo(
                getApprovedCardNumber(),
                previousMonth.format(monthFormatter),
                previousMonth.format(yearFormatter),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithCurrentMonthAndYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = LocalDate.now();

        return new CardInfo(
                getApprovedCardNumber(),
                now.format(monthFormatter),
                now.format(yearFormatter),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithNextMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        var now = LocalDate.now();
        var nextMonth = now.plusMonths(1);

        return new CardInfo(
                getApprovedCardNumber(),
                nextMonth.format(monthFormatter),
                nextMonth.format(yearFormatter),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithOldYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                "23",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithSymbolsYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                "№;",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithLettersYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                "year 2024",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithEmptyYear() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                approvedCardInfo.getMonth(),
                "",
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithInvalidMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                "13",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithTwoZeroMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                "00",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithSymbolMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                "№;",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithLetterMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                "п5",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithEmptyMonth() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                getApprovedCardNumber(),
                "",
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithTwoNumberCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "44",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithOneNumberCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "4",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithFewerCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "4444 4444 4444 444",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithSymbolsCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "4444 #$^% (&%^ 1111",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithLettersCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                FAKER.name().name(),
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithZerosCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "0000 0000 0000 0000",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getCardWithEmptyCardNumber() {
        var approvedCardInfo = DataHelper.getApprovedCardInfo();

        return new CardInfo(
                "",
                approvedCardInfo.getMonth(),
                approvedCardInfo.getYear(),
                approvedCardInfo.getOwner(),
                approvedCardInfo.getCvc()
        );
    }

    public static CardInfo getApprovedCardInfo() {
        var nextYearDate = getNextYearDate();

        var firstName = FAKER.name().firstName();
        var lastName = FAKER.name().lastName();
        var owner = firstName + " " + lastName;

        var cvc = FAKER.number().numberBetween(1, 999);

        return new CardInfo(
                getApprovedCardNumber(),
                nextYearDate.format(monthFormatter),
                nextYearDate.format(yearFormatter),
                owner,
                String.format(correctCvcFormat, cvc));
    }

    public static CardInfo getDeclinedCardInfo() {
        var nextYearDate = getNextYearDate();

        var firstName = FAKER.name().firstName();
        var lastName = FAKER.name().lastName();
        var owner = firstName + " " + lastName;

        var cvc = FAKER.number().numberBetween(1, 999);

        return new CardInfo(
                getDeclinedCardNumber(),
                nextYearDate.format(monthFormatter),
                nextYearDate.format(yearFormatter),
                owner,
                String.format(correctCvcFormat, cvc));
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    private static String getInvalidCardNumber() {
        return "4444 4444 4444 4443";
    }

    private static LocalDate getPreviousYearDate() {
        return LocalDate.now().plusYears(-1);
    }

    private static LocalDate getNextYearDate() {
        return LocalDate.now().plusYears(1);
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    @Data
    @NoArgsConstructor
    public static class CreditRequest {
        String id;
        String bankId;
        String status;
    }

    @Data
    @NoArgsConstructor
    public static class Order {
        String id;
        String creditId;
        String paymentId;
    }

    @Data
    @NoArgsConstructor
    public static class Payment {
        String id;
        int amount;
        String status;
        String transactionId;
    }
}
