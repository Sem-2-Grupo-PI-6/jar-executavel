package school.sptech;

import java.util.Date;

public class LogSelic {
    private Integer id;
    private Integer idSelic;
    private String descricao;
    private Date dataHora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSelic() {
        return idSelic;
    }

    public void setIdSelic(Integer idSelic) {
        this.idSelic = idSelic;
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
        return "LogSelic{" +
                "id=" + id +
                ", idSelic=" + idSelic +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }
}
