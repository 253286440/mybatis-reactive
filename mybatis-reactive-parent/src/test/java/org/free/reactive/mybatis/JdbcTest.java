package org.free.reactive.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBuilder;
import org.springframework.boot.autoconfigure.r2dbc.EmbeddedDatabaseConnection;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.sql.SQLException;

@SpringBootTest
public class JdbcTest {

    @Autowired
    private R2dbcProperties r2dbcProperties;

    @Test
    public void testConnection () throws SQLException, InterruptedException {
        var connectionFactory = ConnectionFactoryBuilder.of(r2dbcProperties, () -> EmbeddedDatabaseConnection.NONE)
                .build();
        Mono.from(connectionFactory.create())
                .map(connection ->  connection.createStatement("select * from t_patron_user limit 1"))
                .flatMap(s -> Mono.from(s.execute()))
                .flatMap(result -> Mono.from(result.map((r,rm)-> r.get(0))))
                .subscribe(s -> System.out.println(s));
        Thread.sleep(100000);
    }

}
