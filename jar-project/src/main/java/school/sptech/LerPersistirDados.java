package school.sptech;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LerPersistirDados {
    Conexao conexao = new Conexao();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(conexao.getConexao());


    public void gravarDados(String caminho) {
        try (FileInputStream fis = new FileInputStream(caminho);
             Workbook workbook = WorkbookFactory.create(fis)) {
            System.out.println(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                String mun = row.getCell(0).getStringCellValue();
                Double num = row.getCell(1).getNumericCellValue();

                Inflacao inflacao = new Inflacao();
                inflacao.setMunicipio(mun);
                inflacao.setInflacao(num);

                jdbcTemplate.update(
                        "INSERT INTO inflacao (municipio, inflacao) VALUES (?, ?)",
                        inflacao.getMunicipio(),
                        inflacao.getInflacao()
                );
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
