package school.sptech;

import java.util.Date;

public class LogCadastro {
    private Integer id;
    private Integer idCadastro;
    private String descricao;
    private Date dataHora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "LogCadastro{" +
                "id=" + id +
                ", idCadastro=" + idCadastro +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }
}
