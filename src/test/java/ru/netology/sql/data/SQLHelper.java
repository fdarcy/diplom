package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        var dbUrl = System.getProperty("db.url");
        return DriverManager.getConnection(dbUrl, "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.CreditRequest getLastCreditRequest() {
        var beanListHandler = new BeanListHandler<>(DataHelper.CreditRequest.class);
        var codeSQL = "SELECT id, bank_id, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var data = QUERY_RUNNER.query(conn, codeSQL, beanListHandler);
        return data.isEmpty() ? null : data.get(0);
    }

    @SneakyThrows
    public static DataHelper.Order getLastOrder() {
        var beanListHandler = new BeanListHandler<>(DataHelper.Order.class);
        var codeSQL = "SELECT id, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var data = QUERY_RUNNER.query(conn, codeSQL, beanListHandler);
        return data.isEmpty() ? null : data.get(0);
    }

    @SneakyThrows
    public static DataHelper.Payment getLastPayment() {
        var beanListHandler = new BeanListHandler<>(DataHelper.Payment.class);
        var codeSQL = "SELECT id, amount, status, transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var data = QUERY_RUNNER.query(conn, codeSQL, beanListHandler);
        return data.isEmpty() ? null : data.get(0);
    }

    @SneakyThrows
    public static void clearDatabase() {
        var connection = getConn();
        QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM order_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
    }
}
