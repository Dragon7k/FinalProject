package by.epam.gamestore.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 32;
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usingConnections;
    private static ConnectionPool instance;

    private ConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usingConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);

        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection connection = new ProxyConnection(ConnectionFactory.getConnection());
                freeConnections.offer(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connection is not create.", e);
        }
        if(freeConnections.isEmpty()){
            throw new RuntimeException("Connection pool is empty.");
        }
    }

    /**
     * Get instance CustomConnectionPool.
     *
     * @return the CustomConnectionPool
     */
    public static ConnectionPool getInstance(){
        if(!instanceCreated.get()){
            lock.lock();
            try{
                if(instance == null){
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Get connection from ConnectionPool.
     *
     * @return the connection
     */
    public Connection takeConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usingConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("There are some problems with get connection", e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection){
        if(!(connection instanceof ProxyConnection)) {
            return false;
        }
        try {
            ProxyConnection proxy = (ProxyConnection) connection;
            usingConnections.remove(proxy);
            freeConnections.put(proxy);
        } catch (InterruptedException e) {
            logger.error("There are some problems with release connection",e);
        }
        return true;
    }

    /**
     * Destroy ConnectionPool.
     */
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException e) {
                logger.error("There are some problems with destroying connection pool", e);
            }
        }
        deregisterDriver();
    }

    /**
     * Deregister driver.
     */
    private void deregisterDriver(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("There are some problems with deregister driver",e);
            }
        });
    }
}
