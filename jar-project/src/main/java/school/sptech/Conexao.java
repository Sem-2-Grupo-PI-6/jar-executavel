package school.sptech;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class Conexao {

    private DataSource conexao;

    public Conexao(){
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setUsername("root");
        driver.setPassword("sptech");
        driver.setUrl("jdbc:mysql://192.168.15.117:3306/sixtech");
        driver.setDriverClassName("com.mysql.cj.jdbc.Driver");

        this.conexao = driver;
    }

    public DataSource getConexao(){
        return conexao;
    }

}
