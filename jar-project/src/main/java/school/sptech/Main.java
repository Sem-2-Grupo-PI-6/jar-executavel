package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.JDBCType;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(conexao.getConexao());

        LerPersistirDados lerPersistirDados = new LerPersistirDados();

        lerPersistirDados.gravarDados("src/main/java/school/sptech/inflacao.xlsx");

    }
}
