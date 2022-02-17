package by.epam.gamestore.service.impl;

import by.epam.gamestore.dao.GameDao;
import by.epam.gamestore.dao.impl.GameDaoImpl;
import by.epam.gamestore.entity.Game;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.GameService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {
    private static final Logger logger = LogManager.getLogger();
    private GameDao gameDao = GameDaoImpl.getInstance();
    private static GameServiceImpl instance;

    private GameServiceImpl() {
    }


    @Override
    public List<Game> findAll() throws ServiceException {
        List<Game> games = new ArrayList<>();
        try {
            games = gameDao.findAllGame();
        } catch (DaoException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new ServiceException();
        }
        return games;
    }





    public static GameServiceImpl getInstance() {
        if (instance == null) {
            instance = new GameServiceImpl();
        }
        return instance;
    }

}
