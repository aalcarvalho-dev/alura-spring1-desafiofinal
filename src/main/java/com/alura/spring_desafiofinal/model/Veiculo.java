package com.alura.spring_desafiofinal.model;

public class Veiculo {
    public String nome;
    public String ano;
    public Double valor;
    public String combustivel;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getCombustivel() {
        return combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    @Override
    public String toString() {
        return nome + " ano:" + ano + ", valor:" + valor + ", combustivel:" + combustivel;
    }
    
}
