package com.supperarrow.directory.mysql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class PooledConnection implements Connection {

    private Connection coreConnection;
    private ConnectionPool connectionPool;

    public PooledConnection(Connection coreConnection, ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.coreConnection = coreConnection;
    }

   
    public void close() throws SQLException {
        connectionPool.surrenderConnection(this);
    }

    /* ****************************************************************
     *                          Proxy Methods
     * ****************************************************************/

   
    public Statement createStatement() throws SQLException {
        return coreConnection.createStatement();
    }

    
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return coreConnection.prepareStatement(sql);
    }

    
    public CallableStatement prepareCall(String sql) throws SQLException {
        return coreConnection.prepareCall(sql);
    }

    
    public String nativeSQL(String sql) throws SQLException {
        return coreConnection.nativeSQL(sql);
    }

    
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        coreConnection.setAutoCommit(autoCommit);
    }

    
    public boolean getAutoCommit() throws SQLException {
        return coreConnection.getAutoCommit();
    }

    
    public void commit() throws SQLException {
        coreConnection.commit();
    }

    
    public void rollback() throws SQLException {
        coreConnection.rollback();
    }

    
    public boolean isClosed() throws SQLException {
        return coreConnection.isClosed();
    }

    
    public DatabaseMetaData getMetaData() throws SQLException {
        return coreConnection.getMetaData();
    }

    
    public void setReadOnly(boolean readOnly) throws SQLException {
        coreConnection.setReadOnly(readOnly);
    }

    
    public boolean isReadOnly() throws SQLException {
        return coreConnection.isReadOnly();
    }

    
    public void setCatalog(String catalog) throws SQLException {
        coreConnection.setCatalog(catalog);
    }

    
    public String getCatalog() throws SQLException {
        return coreConnection.getCatalog();
    }

    
    public void setTransactionIsolation(int level) throws SQLException {
        coreConnection.setTransactionIsolation(level);
    }

    
    public int getTransactionIsolation() throws SQLException {
        return coreConnection.getTransactionIsolation();
    }

    
    public SQLWarning getWarnings() throws SQLException {
        return coreConnection.getWarnings();
    }

    
    public void clearWarnings() throws SQLException {
        coreConnection.clearWarnings();
    }

    
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return coreConnection.createStatement(resultSetType, resultSetConcurrency);
    }

    
    public PreparedStatement prepareStatement(String sql, int resultSetType,
                                              int resultSetConcurrency) throws SQLException {
        return coreConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return coreConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return coreConnection.getTypeMap();
    }

    
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        coreConnection.setTypeMap(map);
    }

    
    public void setHoldability(int holdability) throws SQLException {
        coreConnection.setHoldability(holdability);
    }

    
    public int getHoldability() throws SQLException {
        return coreConnection.getHoldability();
    }

    
    public Savepoint setSavepoint() throws SQLException {
        return coreConnection.setSavepoint();
    }

    
    public Savepoint setSavepoint(String name) throws SQLException {
        return coreConnection.setSavepoint(name);
    }

    
    public void rollback(Savepoint savepoint) throws SQLException {
        coreConnection.rollback();
    }

    
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        coreConnection.releaseSavepoint(savepoint);
    }

    
    public Statement createStatement(int resultSetType, int resultSetConcurrency,
                                     int resultSetHoldability) throws SQLException {
        return coreConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                              int resultSetHoldability) throws SQLException {
        return coreConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                         int resultSetHoldability) throws SQLException {
        return coreConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return coreConnection.prepareStatement(sql, autoGeneratedKeys);
    }

    
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return coreConnection.prepareStatement(sql, columnIndexes);
    }

    
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return coreConnection.prepareStatement(sql, columnNames);
    }

    
    public Clob createClob() throws SQLException {
        return coreConnection.createClob();
    }

    
    public Blob createBlob() throws SQLException {
        return coreConnection.createBlob();
    }

    
    public NClob createNClob() throws SQLException {
        return coreConnection.createNClob();
    }

    
    public SQLXML createSQLXML() throws SQLException {
        return coreConnection.createSQLXML();
    }

    
    public boolean isValid(int timeout) throws SQLException {
        return coreConnection.isValid(timeout);
    }

    
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        coreConnection.setClientInfo(name, value);
    }

    
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        coreConnection.setClientInfo(properties);
    }

    
    public String getClientInfo(String name) throws SQLException {
        return coreConnection.getClientInfo(name);
    }

    
    public Properties getClientInfo() throws SQLException {
        return coreConnection.getClientInfo();
    }

    
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return coreConnection.createArrayOf(typeName, elements);
    }

    
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return coreConnection.createStruct(typeName, attributes);
    }

    
    public void setSchema(String schema) throws SQLException {
        coreConnection.setSchema(schema);
    }

    
    public String getSchema() throws SQLException {
        return coreConnection.getSchema();
    }

    
    public void abort(Executor executor) throws SQLException {
        coreConnection.abort(executor);
    }

    
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        coreConnection.setNetworkTimeout(executor, milliseconds);
    }

    
    public int getNetworkTimeout() throws SQLException {
        return coreConnection.getNetworkTimeout();
    }

    
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return coreConnection.unwrap(iface);
    }

    
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return coreConnection.isWrapperFor(iface);
    }
}