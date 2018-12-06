package com.example.pegados.appdesafios.data;

import java.io.Serializable;

public class Desafio implements Serializable {

    private int id;
    private int idUsuario;
    private String titulo;
    private String questao;
    private String resposta;
    private double valorTentativa;
    private double valorPremio;


    public Desafio(int id, int idUsuario, String titulo, String questao, String resposta, double valorTentativa, double valorPremio) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.questao = questao;
        this.resposta = resposta;
        this.valorTentativa = valorTentativa;
        this.valorPremio = valorPremio;
    }

    public Desafio(String titulo, String questao, String resposta, double valorTentativa, double valorPremio) {
        this.titulo = titulo;
        this.questao = questao;
        this.resposta = resposta;
        this.valorTentativa = valorTentativa;
        this.valorPremio = valorPremio;
    }

    public Desafio(int idUsuario, String titulo, String questao, String resposta, double valorTentativa, double valorPremio) {
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.questao = questao;
        this.resposta = resposta;
        this.valorTentativa = valorTentativa;
        this.valorPremio = valorPremio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void setValorTentativa(double valorTentativa) {
        this.valorTentativa = valorTentativa;
    }

    public void setValorPremio(double valorPremio) {
        this.valorPremio = valorPremio;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getQuestao() {
        return questao;
    }

    public String getResposta() {
        return resposta;
    }

    public double getValorTentativa() {
        return valorTentativa;
    }

    public double getValorPremio() {
        return valorPremio;
    }
}
