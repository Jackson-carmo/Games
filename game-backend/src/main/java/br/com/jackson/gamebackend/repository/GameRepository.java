package br.com.jackson.gamebackend.repository;

import br.com.jackson.gamebackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("SELECT obj FROM Game obj WHERE obj.categoria.id = :id_cat ORDER BY titulo")
    List<Game> findAllByCategoria(@Param(value = "id_cat") Integer id_cat);
}
