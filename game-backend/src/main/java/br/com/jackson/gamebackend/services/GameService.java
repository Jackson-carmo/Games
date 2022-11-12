package br.com.jackson.gamebackend.services;

import br.com.jackson.gamebackend.models.Categoria;
import br.com.jackson.gamebackend.models.Game;
import br.com.jackson.gamebackend.repository.GameRepository;
import br.com.jackson.gamebackend.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final CategoriaServce categoriaServce;

    public GameService(GameRepository gameRepository, CategoriaServce categoriaServce) {
        this.gameRepository = gameRepository;
        this.categoriaServce = categoriaServce;
    }

    public Game findById(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(
                () -> new ObjectNotFoundException(
                        "Game não encontrado! Id: " + id + ", Tipo " + Game.class.getName()
                )
        );
    }

    public List<Game> findAll(Integer id_cat) {
        categoriaServce.findById(id_cat);
        return gameRepository.findAllByCategoria(id_cat);
    }

    public Game update(Integer id, Game game) {
        Game game1 = findById(id);
        updateData(game1, game);
        return gameRepository.save(game1);
    }

    private void updateData(Game game1, Game game) {
        game1.setTitulo(game.getTitulo());
        game1.setImagem(game.getImagem());
        game1.setDesenvolvedor(game.getDesenvolvedor());
        game1.setModos(game.getModos());
        game1.setPlataforma(game.getPlataforma());
        game1.setSinopse(game.getSinopse());
        game1.setDataLancamento(game.getDataLancamento());
    }

    public Game create(Integer id_cat, Game game) {
        game.setId(null);
        Categoria categoria = categoriaServce.findById(id_cat);
        game.setCategoria(categoria);
        return gameRepository.save(game);
    }

    public  void delete(Integer id) {
        Game game = findById(id);
        gameRepository.delete(game);
    }
}
