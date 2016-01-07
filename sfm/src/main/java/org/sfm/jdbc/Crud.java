package org.sfm.jdbc;

import org.sfm.utils.RowHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public interface Crud<T, K> {
    /**
     * insert value into the db through the specified connection.
     *
     * @param connection the connection
     * @param value      the value
     * @throws SQLException if an error occurs
     */
    void create(Connection connection, T value) throws SQLException;

    void create(Connection connection, Collection<T> values) throws SQLException;

    /**
     * insert value into the db through the specified connection.
     * Callback keyConsumer with the generated key if one was.
     *
     * @param connection  the connection
     * @param value       the value
     * @param keyConsumer the key consumer
     * @param <RH>        the type of keyConsumer
     * @return the keyConsumer
     * @throws SQLException
     */
    <RH extends RowHandler<? super K>> RH create(Connection connection, T value, RH keyConsumer) throws SQLException;

    <RH extends RowHandler<? super K>> RH create(Connection connection, Collection<T> values, RH keyConsumer) throws SQLException;

    /**
     * retrieve the object with the specified key.
     *
     * @param connection the connection
     * @param key        the key
     * @return the object or null if not found
     * @throws SQLException if an error occurs
     */
    T read(Connection connection, K key) throws SQLException;

    <RH extends RowHandler<? super T>> RH read(Connection connection, Collection<K> keys, RH rowHandler) throws SQLException;

    /**
     * update the object.
     *
     * @param connection the connection
     * @param value      the object
     * @throws SQLException if an error occurs
     */
    void update(Connection connection, T value) throws SQLException;

    void update(Connection connection, Collection<T> values) throws SQLException;

    /**
     * delete the object with the specified key.
     *
     * @param connection the connection
     * @param key        the key
     * @throws SQLException if an error occurs
     */
    void delete(Connection connection, K key) throws SQLException;

    void delete(Connection connection, List<K> keys) throws SQLException;

    /**
     * UPSERT only supported on Mysql
     * @param connection the connection
     * @param value the value
     * @throws SQLException
     * @throws UnsupportedOperationException
     */
    void createOrUpdate(Connection connection, T value) throws SQLException;

    /**
     * UPSERT only supported on Mysql
     * @param connection the connection
     * @param values the values to upsert
     * @throws SQLException
     * @throws UnsupportedOperationException
     */
    void createOrUpdate(Connection connection, Collection<T> values) throws SQLException;

    /**
     * UPSERT only supported on Mysql.
     * Used the callback with caution has Mysql will return an incremented id event for when no insert actually occurred.
     * @param connection the connection
     * @param value the value to upsert
     * @param keyConsumer generated key consumer
     * @param <RH> the keyConsumer type
     * @return the keyConsumer
     * @throws SQLException
     */
    <RH extends RowHandler<? super K>> RH createOrUpdate(Connection connection, T value, RH keyConsumer) throws SQLException;


    /**
     * UPSERT only supported on Mysql.
     * Used the callback with caution has Mysql will return an incremented id event for when no insert actually occurred.
     * @param connection the connection
     * @param values the values to insert
     * @param keyConsumer generated key consumer
     * @param <RH> the keyConsumer type
     * @return the keyConsumer
     * @throws SQLException
     */
    <RH extends RowHandler<? super K>> RH createOrUpdate(Connection connection, Collection<T> values, RH keyConsumer) throws SQLException;
}
