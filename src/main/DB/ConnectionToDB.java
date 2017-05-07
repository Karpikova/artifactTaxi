package main.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import main.Exception.TaxiException;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class ConnectionToDB{

    public static DataSource dataSource;
    private Connection connection;

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/taxi");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("79");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(500);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    private static final org.apache.log4j.Logger logger = Logger.getLogger(ConnectionToDB.class);

    public Connection toConnect() throws TaxiException, ExecutionException, InterruptedException, SQLException {

        Future<Connection> future = dataSource.getConnectionAsync();
        int numb_of_try = 0;
        while (!future.isDone() && numb_of_try < 100) {
            numb_of_try++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();
            }
        }
        if (!future.isDone()) {
            System.out.println("Connection is not yet available. We are sorry. Try again later.");
            throw new SQLException();
        }
        connection = future.get();
        return connection;
    }

    public static void execute(String qText) throws TaxiException, SQLException, ExecutionException, InterruptedException {
        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.toConnect();
        Statement st = connection.createStatement();
        st.execute(qText);
    }


}