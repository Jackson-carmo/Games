package br.com.jackson.gamebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo titulo e obrigatorio!")
    @Length(min = 3, max = 45, message = "O campo titulo deve conter de 3 a 45 caracteres.")
    private String titulo;

    @NotEmpty(message = "O campo imagem e obrigatorio!")
    @Length(min = 3, max = 200, message = "O campo imagem deve conter de 3 a 200 caracteres.")
    private String imagem;

    @NotEmpty(message = "O campo plataforma e obrigatorio!")
    @Length(min = 3, max = 200, message = "O campo plataforma deve conter de 3 a 200 caracteres.")
    private String plataforma;

    @NotEmpty(message = "O campo desenvolvedor e obrigatorio!")
    @Length(min = 3, max = 45, message = "O campo desenvolvedor deve conter de 3 a 45 caracteres.")
    private String desenvolvedor;

    @NotEmpty(message = "O campo modos e obrigatorio!")
    @Length(min = 3, max = 45, message = "O campo modos deve conter de 3 a 45 caracteres.")
    private String modos;

    @NotEmpty(message = "O campo sinopse e obrigatorio!")
    @Length(min = 10, max = 5000, message = "O campo sinopse deve conter de 10 a 5000 caracteres.")
    private String sinopse;

    private LocalDate dataLancamento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Game() {
    }

    public Game(Integer id, String titulo, String imagem, String plataforma, String desenvolvedor, String modos, String sinopse, LocalDate dataLancamento, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.imagem = imagem;
        this.plataforma = plataforma;
        this.desenvolvedor = desenvolvedor;
        this.modos = modos;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public String getModos() {
        return modos;
    }

    public void setModos(String modos) {
        this.modos = modos;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        return Objects.equals(id, other.id);
    }
}
