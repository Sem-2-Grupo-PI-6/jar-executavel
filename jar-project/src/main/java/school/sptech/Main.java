package school.sptech;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        try (Connection conn = new Conexao().getConexao().getConnection()) {
            System.out.println("Conexão OK: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> arquivosXlsx = List.of(
                "inflacao.xlsx",
                "populacao.xlsx",
                "ipeaData_PIB_ConstrucaoCivil.xlsx",
                "selic.xlsx"
        );

        LerPersistirDados inflacao = new LerPersistirDados();
        inflacao.inserirDadosInflacao("inflacao.csv");

    }
}
