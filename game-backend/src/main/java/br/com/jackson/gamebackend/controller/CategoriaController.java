package br.com.jackson.gamebackend.controller;

import br.com.jackson.gamebackend.dto.CategoriaDto;
import br.com.jackson.gamebackend.models.Categoria;
import br.com.jackson.gamebackend.services.CategoriaServce;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private final CategoriaServce categoriaServce;

    public CategoriaController(CategoriaServce categoriaServce) {
        this.categoriaServce = categoriaServce;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaServce.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list =categoriaServce.findAll();
        List<CategoriaDto> dtoList = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria) {
        categoria = categoriaServce.create(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> update(@Valid @PathVariable Integer id, @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = categoriaServce.update(id, categoriaDto);
        return ResponseEntity.ok().body(new CategoriaDto(categoria));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaServce.delete(id);
        return ResponseEntity.noContent().build();
    }

    }


