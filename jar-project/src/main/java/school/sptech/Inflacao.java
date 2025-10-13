package school.sptech;

import javax.xml.crypto.Data;

public class Inflacao {
    private String municipio;
    private Double inflacao;

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Double getInflacao() {
        return inflacao;
    }

    public void setInflacao(Double inflcao) {
        this.inflacao = inflcao;
    }

    @Override
    public String toString() {
        return "Inflacao{" +
                "municipio='" + municipio + '\'' +
                ", inflcao=" + inflacao +
                '}';
    }
}
