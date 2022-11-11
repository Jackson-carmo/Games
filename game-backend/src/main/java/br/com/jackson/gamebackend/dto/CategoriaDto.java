package br.com.jackson.gamebackend.dto;

import br.com.jackson.gamebackend.models.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O campo nome e obrigatorio!")
    @Length(min = 3,max = 45, message = "O campo nome deve conter entre 3 a 45 caracteres.")
    private String nome;

    @NotEmpty(message = "O campo descricao e obrigatorio!")
    @Length(min = 10,max = 5000, message = "O campo descricao deve conter entre 10 a 5000 caracteres.")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
