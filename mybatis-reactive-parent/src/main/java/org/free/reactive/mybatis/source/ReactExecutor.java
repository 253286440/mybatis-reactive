package org.free.reactive.mybatis.source;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

public interface ReactExecutor {

    Mono<Integer> update(MappedStatement ms, Mono<Object> parameter) throws SQLException;

    <E> Mono<List<E>> query(MappedStatement ms, Mono<Object> parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;

    <E> Mono<List<E>> query(MappedStatement ms, Mono<Object> parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    <E> Mono<Cursor<E>> queryCursor(MappedStatement ms, Mono<Object> parameter, RowBounds rowBounds) throws SQLException;

    Mono<List<BatchResult>> flushStatements() throws SQLException;

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    Mono<CacheKey> createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    boolean isCached(MappedStatement ms, CacheKey key);

    void clearLocalCache();

    void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);

    Mono<Transaction> getTransaction();

    void close(boolean forceRollback);

    boolean isClosed();

    void setExecutorWrapper(Executor executor);
}
