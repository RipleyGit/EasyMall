package me.seaOf.utils.DbUtils;

import java.sql.ResultSet;

/**
 * 轻量级DbUtils实现
 * @param <T>
 */
public interface ResultSetHandler <T> {
    T handle(ResultSet rs) throws Exception;
}
