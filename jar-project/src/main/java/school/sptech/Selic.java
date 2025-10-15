package school.sptech;

import java.util.Date;

public class Selic {
    private Integer id;
    private Double taxaSelic;
    private Date dataApuracao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTaxaSelic() {
        return taxaSelic;
    }

    public void setTaxaSelic(Double taxaSelic) {
        this.taxaSelic = taxaSelic;
    }

    public Date getDataApuracao() {
        return dataApuracao;
    }

    public void setDataApuracao(Date dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    @Override
    public String toString() {
        return "Selic{" +
                "id=" + id +
                ", taxaSelic=" + taxaSelic +
                ", dataApuracao=" + dataApuracao +
                '}';
    }
}
