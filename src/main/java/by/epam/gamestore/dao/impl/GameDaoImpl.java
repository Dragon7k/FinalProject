package by.epam.gamestore.dao.impl;

import by.epam.gamestore.connection.ConnectionPool;
import by.epam.gamestore.dao.GameDao;
import by.epam.gamestore.entity.Game;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameDaoImpl implements GameDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_ALL_GAMES = "select *from game";
    private static final String SQL_FIND_GAME_BY_ID = "select *from game where game_id =?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static GameDaoImpl instance;

    private GameDaoImpl() {
    }



    public static GameDaoImpl getInstance() {
        if (instance == null) {
            instance = new GameDaoImpl();
        }
        return instance;
    }


    @Override
    public List<Game> findAllGame() throws DaoException {
        List<Game> gameList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_GAMES);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Game game = buildGame(resultSet);
                    gameList.add(game);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "can't select all games");
            throw new DaoException("can't select all games");
        }
        return gameList;
    }

    private Game buildGame(ResultSet resultSet) throws SQLException {
        return (new Game.GameBuilder()
                .setId(resultSet.getLong(1))
                .setName(resultSet.getString(2))
                .setPrice(resultSet.getLong(3))
                .setDescription(resultSet.getString(4))
                .setDeveloper(resultSet.getString(5))
                .buildGame());
    }

    @Override
    public Optional<Game> findGameById(long id) throws DaoException {
        Optional<Game> optionalGame = Optional.empty();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_GAME_BY_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    optionalGame = Optional.of(buildGame(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }

        return optionalGame;
    }
}
