package br.com.jackson.gamebackend.dto;

import br.com.jackson.gamebackend.models.Game;

import java.io.Serializable;

public class GameDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String titulo;

    public GameDto() {
    }

    public GameDto(Game obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
