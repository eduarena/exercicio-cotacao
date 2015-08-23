package br.com.example.fiap.mob8.exerciciocotacao.model;

/**
 * Created by eduardo on 8/22/15.
 */
public class Cotacao {

    private int id;
    private String bovespa;
    private String bovespaCotacao;
    private String bovespaVariacao;
    private String dolar;
    private String dolarCotacao;
    private String dolarVariacao;
    private String euro;
    private String euroCotacao;
    private String euroVariacao;
    private String atualizacao;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getBovespa() {
        return bovespa;
    }

    public void setBovespa(String bovespa) {
        this.bovespa = bovespa;
    }

    public String getBovespaCotacao() {
        return bovespaCotacao;
    }

    public void setBovespaCotacao(String bovespaCotacao) {
        this.bovespaCotacao = bovespaCotacao;
    }

    public String getBovespaVariacao() {
        return bovespaVariacao;
    }

    public void setBovespaVariacao(String bovespaVariacao) {
        this.bovespaVariacao = bovespaVariacao;
    }

    public String getDolar() {
        return dolar;
    }

    public void setDolar(String dolar) {
        this.dolar = dolar;
    }

    public String getDolarCotacao() {
        return dolarCotacao;
    }

    public void setDolarCotacao(String dolarCotacao) {
        this.dolarCotacao = dolarCotacao;
    }

    public String getDolarVariacao() {
        return dolarVariacao;
    }

    public void setDolarVariacao(String dolarVariacao) {
        this.dolarVariacao = dolarVariacao;
    }

    public String getEuro() {
        return euro;
    }

    public void setEuro(String euro) {
        this.euro = euro;
    }

    public String getEuroCotacao() {
        return euroCotacao;
    }

    public void setEuroCotacao(String euroCotacao) {
        this.euroCotacao = euroCotacao;
    }

    public String getEuroVariacao() {
        return euroVariacao;
    }

    public void setEuroVariacao(String euroVariacao) {
        this.euroVariacao = euroVariacao;
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao) {
        this.atualizacao = atualizacao;
    }
}
