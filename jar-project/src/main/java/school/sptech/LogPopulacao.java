package school.sptech;

import java.util.Date;

public class LogPopulacao {
    private Integer id;
    private Integer idPopulacao;
    private String descricao;
    private Date dataHora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPopulacao() {
        return idPopulacao;
    }

    public void setIdPopulacao(Integer idPopulacao) {
        this.idPopulacao = idPopulacao;
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
        return "LogPopulacao{" +
                "id=" + id +
                ", idPopulacao=" + idPopulacao +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }
}
