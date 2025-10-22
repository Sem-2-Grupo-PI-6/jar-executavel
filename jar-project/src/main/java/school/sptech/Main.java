package school.sptech;

import software.amazon.awssdk.regions.Region;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> arquivosXlsx = List.of(
                "inflacao.xlsx",
                "populacao.xlsx",
                "ipeaData_PIB_ConstrucaoCivil.xlsx",
                "selic.xlsx"
        );

        LerPersistirDados inflacao = new LerPersistirDados();
        inflacao.inserirDadosInflacao(arquivosXlsx.getFirst());





    }
}
