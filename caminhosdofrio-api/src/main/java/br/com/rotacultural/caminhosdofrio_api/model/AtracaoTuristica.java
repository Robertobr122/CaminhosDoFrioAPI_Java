package br.com.rotacultural.caminhosdofrio_api.model;

import jakarta.persistence.*;

public class AtracaoTuristica {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private String tipo;
    private String imagemUrl;
    
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public AtracaoTuristica() {}

    public AtracaoTuristica(Long id, String nome, String descricao, String tipo, String imagemUrl, Evento evento) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.imagemUrl = imagemUrl;
        this.evento = evento;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEventoId(Evento eventoId) {
        this.evento = evento;
    }

}
