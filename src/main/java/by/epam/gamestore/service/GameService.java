package by.epam.gamestore.service;

import by.epam.gamestore.entity.Game;
import by.epam.gamestore.exception.ServiceException;

import java.util.List;

public interface GameService {
    List<Game> findAll() throws ServiceException;
}
