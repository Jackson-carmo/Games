package br.com.jackson.gamebackend.services;

import br.com.jackson.gamebackend.dto.CategoriaDto;
import br.com.jackson.gamebackend.models.Categoria;
import br.com.jackson.gamebackend.repository.CategoriaRepository;
import br.com.jackson.gamebackend.services.exceptions.DataIntegrityViolationException;
import br.com.jackson.gamebackend.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServce {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServce(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow((() -> new ObjectNotFoundException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()
        )));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria create(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Integer id, CategoriaDto categoriaDto) {
        Categoria categoria = findById(id);
        categoria.setNome(categoriaDto.getNome());
        categoria.setDescricao(categoria.getDescricao());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new br.com.jackson.gamebackend.services.exceptions.DataIntegrityViolationException(
                    "Categoria não pode ser removida! Possui games associados"
            );
        }
    }
}
