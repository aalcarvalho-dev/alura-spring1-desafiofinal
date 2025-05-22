package com.alura.spring_desafiofinal.model;

public class Veiculo {
    public String marca;
    public String modelo;
    public Double valor;
    public String combustivel;
    public String getMarca() {
        return marca;
    }
    public void setMarca(String nome) {
        this.marca = nome;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String ano) {
        this.modelo = ano;
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
        return marca + " modelo:" + modelo + ", valor:" + valor + ", combustivel:" + combustivel;
    }
    
}
