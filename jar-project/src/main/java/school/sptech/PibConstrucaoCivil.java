package school.sptech;

import java.util.Date;

public class PibConstrucaoCivil {
    private Integer id;
    private Double valorPib;
    private Date dataApuracao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorPib() {
        return valorPib;
    }

    public void setValorPib(Double valorPib) {
        this.valorPib = valorPib;
    }

    public Date getDataApuracao() {
        return dataApuracao;
    }

    public void setDataApuracao(Date dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    @Override
    public String toString() {
        return "PibConstrucaoCivil{" +
                "id=" + id +
                ", valorPib=" + valorPib +
                ", dataApuracao=" + dataApuracao +
                '}';
    }
}
