package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnMySql() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url.mysql"), "app", "pass");
    }

    private static Connection getConnPostgres() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url.postgres"), "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.CreditRequest getLastCreditRequest() {
        var codeSQL = "SELECT id, bank_id, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnMySql();
        var arrayList = QUERY_RUNNER.query(conn, codeSQL, new ArrayListHandler());
        var firstElement = arrayList.get(0);
        if (firstElement == null) {
            return null;
        }

        return new DataHelper.CreditRequest(
                (firstElement[0] != null) ? firstElement[0].toString() : null,
                (firstElement[1] != null) ? firstElement[1].toString() : null,
                (firstElement[2] != null) ? firstElement[2].toString() : null);
    }

    @SneakyThrows
    public static DataHelper.Order getLastOrder() {
        var codeSQL = "SELECT id, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnMySql();
        var arrayList = QUERY_RUNNER.query(conn, codeSQL, new ArrayListHandler());
        var firstElement = arrayList.get(0);
        if (firstElement == null) {
            return null;
        }

        return new DataHelper.Order(
                (firstElement[0] != null) ? firstElement[0].toString() : null,
                (firstElement[1] != null) ? firstElement[1].toString() : null,
                (firstElement[2] != null) ? firstElement[2].toString() : null);
    }

    @SneakyThrows
    public static DataHelper.Payment getLastPayment() {
        var codeSQL = "SELECT id, amount, status, transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnMySql();
        var arrayList = QUERY_RUNNER.query(conn, codeSQL, new ArrayListHandler());
        var firstElement = arrayList.get(0);
        if (firstElement == null) {
            return null;
        }

        return new DataHelper.Payment(
                (firstElement[0] != null) ? firstElement[0].toString() : null,
                (firstElement[1] != null) ? parseInt(firstElement[1].toString()) : 0,
                (firstElement[2] != null) ? firstElement[2].toString() : null,
                (firstElement[3] != null) ? firstElement[3].toString() : null);
    }

    @SneakyThrows
    public static void clearDatabase() {
        var connection = getConnMySql();
        QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM order_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
    }
}
