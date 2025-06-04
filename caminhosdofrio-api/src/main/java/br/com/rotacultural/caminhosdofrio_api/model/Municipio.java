package br.com.rotacultural.caminhosdofrio_api.model;

import jakarta.persistence.*;

@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar ID com autoincremento
    private Long id;

    private String nome;
    private String descricao; 
    private String estado;
    private String imagemUrl;

    public Municipio() {}

    public Municipio(Long id, String nome, String descricao, String estado, String imagemUrl){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estado = estado;
        this.imagemUrl = imagemUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
