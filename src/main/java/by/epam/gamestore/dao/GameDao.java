package by.epam.gamestore.dao;

import by.epam.gamestore.entity.Game;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;

import java.util.List;
import java.util.Optional;

public interface GameDao {
    List<Game> findAllGame() throws DaoException;
    Optional<Game> findGameById(long id) throws DaoException;
}
