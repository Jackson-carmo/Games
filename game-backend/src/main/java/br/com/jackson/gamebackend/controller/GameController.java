package br.com.jackson.gamebackend.controller;

import br.com.jackson.gamebackend.dto.GameDto;
import br.com.jackson.gamebackend.models.Game;
import br.com.jackson.gamebackend.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Game> findById(@PathVariable Integer id) {
        Game game = gameService.findById(id);
        return ResponseEntity.ok().body(game);
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> findAll(@RequestParam(value = "categoria", defaultValue = "0")Integer id_cat) {
        List<Game> list =gameService.findAll(id_cat);
        List<GameDto> dtoList = list.stream().map(obj -> new GameDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody Game game) {
        Game game1 = gameService.update(id, game);
        return ResponseEntity.ok().body(game1);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Game> udatepatch(@PathVariable Integer id, @Valid @RequestBody Game game) {
        Game game1 = gameService.update(id, game);
        return ResponseEntity.ok().body(game1);
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat, @Valid  @RequestBody Game game){
        Game game1 = gameService.create(id_cat, game);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(game1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
