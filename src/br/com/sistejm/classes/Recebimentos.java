/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

/**
 *
 * @author Michel
 */
public class Recebimentos {
    
    private int idRecebimento;
    private String descricao;
    private String dataRecebimento;
    private String observacao;
    private int codUsuario;

    public int getIdRecebimento() {
        return idRecebimento;
    }

    public void setIdRecebimento(int idRecebimento) {
        this.idRecebimento = idRecebimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    
    
}
