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
public class Livros {
    
    private int idLivro;
    private String titulo;
    private String editora;
    private String ano;
    private byte codigo;
    private String dataPublicidade;

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public byte getCodigo() {
        return codigo;
    }

    public void setCodigo(byte codigo) {
        this.codigo = codigo;
    }

    public String getDataPublicidade() {
        return dataPublicidade;
    }

    public void setDataPublicidade(String dataPublicidade) {
        this.dataPublicidade = dataPublicidade;
    }
    
    
    
    
}
