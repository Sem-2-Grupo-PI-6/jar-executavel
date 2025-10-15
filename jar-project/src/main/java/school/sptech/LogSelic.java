package school.sptech;

import java.util.Date;

public class LogSelic {
    private Integter id;
    private Integter idSelic;
    private String descricao;
    private Date dataHora;

    public Integter getId() {
        return id;
    }

    public void setId(Integter id) {
        this.id = id;
    }

    public Integter getIdSelic() {
        return idSelic;
    }

    public void setIdSelic(Integter idSelic) {
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
