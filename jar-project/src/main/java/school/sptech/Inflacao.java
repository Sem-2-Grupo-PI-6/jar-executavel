package school.sptech;

import java.util.Date;

public class Inflacao {
    private Date dataApuracao;
    private Double taxaInflacao;

    public Inflacao(Date dataApuracao, Double taxaInflacao) {
        this.dataApuracao = dataApuracao;
        this.taxaInflacao = taxaInflacao;
    }

    public Date getDataApuracao() {
        return dataApuracao;
    }

    public void setDataApuracao(Date dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    public Double getTaxaInflacao() {
        return taxaInflacao;
    }

    public void setTaxaInflacao(Double taxaInflacao) {
        this.taxaInflacao = taxaInflacao;
    }

    @Override
    public String toString() {
        return "Inflacao{" +
                "dataApuracao=" + dataApuracao +
                ", taxaInflacao=" + taxaInflacao +
                '}';
    }
}
