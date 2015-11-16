package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.GenericDao;
import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;
import com.epam.university.spring.core.domain.EventShowing;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-09
 * Time: 17:46
 */
public abstract class GenericDaoImpl<T extends Storable<Long>> implements GenericDao<Long, T> {

    private JdbcTemplate jdbcTemplate;
    private final String SQL_INSERT_STRING;
    private final String SQL_SELECT_STRING;
    private final String SQL_UPDATE_STRING;
    private final String SQL_DELETE_STRING;
    private Class<T> type;
    private RowMapper<T> rowMapper;

    public GenericDaoImpl(JdbcTemplate jdbcTemplate, Class<T> type,
                          String sqlCreateString, String sqlSelectString,
                          String sqlUpdateString, String sqlDeleteString) {
        this.jdbcTemplate = jdbcTemplate;
        this.type = type;
        this.SQL_INSERT_STRING = sqlCreateString;
        this.SQL_SELECT_STRING = sqlSelectString;
        this.SQL_UPDATE_STRING = sqlUpdateString;
        this.SQL_DELETE_STRING = sqlDeleteString;
        this.rowMapper = new BeanPropertyRowMapper<T>(type);
    }

    public GenericDaoImpl(JdbcTemplate jdbcTemplate, Class<T> type,
                          String sqlCreateString, String sqlSelectString,
                          String sqlUpdateString, String sqlDeleteString,
                          RowMapper<T> rowMapper) {
        this(jdbcTemplate, type, sqlCreateString, sqlSelectString, sqlUpdateString, sqlDeleteString);
        this.rowMapper = rowMapper;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public RowMapper<T> getRowMapper() { return this.rowMapper; }

    private void fillStatement(PreparedStatement stmt, Object... params)
            throws SQLException {

        ParameterMetaData pmd = stmt.getParameterMetaData();
        int stmtCount = pmd.getParameterCount();
        int paramsCount = params == null ? 0 : params.length;
        if (stmtCount != paramsCount) {
            throw new SQLException("Wrong number of parameters: expected "
                + stmtCount + ", was given " + paramsCount);
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                int sqlType = Types.VARCHAR;
                try {
                    sqlType = pmd.getParameterType(i + 1);
                } catch (SQLException e) {
                }
                stmt.setNull(i + 1, sqlType);
            }
        }
    }

    @Override
    public Long create(T object) {
        try {
            PreparedStatement stmt = DataSourceUtils.getConnection(jdbcTemplate.getDataSource())
                    .prepareStatement(SQL_INSERT_STRING, new String[]{"ID"});
            fillStatement(stmt, ((RetreiveFieldsValues) object).getFieldsValues());
            int res = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                object.setId(rs.getLong(1));
                return object.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    @Override
    public T get(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STRING + " WHERE ID = ?", new Object[] {id},
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<T> get() {
        return jdbcTemplate.query(SQL_SELECT_STRING, new Object[] {}, rowMapper);
    }

    @Override
    public void update(T object) {
        Object[] fieldsValues = ((RetreiveFieldsValues) object).getFieldsValues();
        fieldsValues = Arrays.copyOf(fieldsValues, fieldsValues.length + 1);
        fieldsValues[fieldsValues.length - 1] = object.getId();
        jdbcTemplate.update(SQL_UPDATE_STRING, fieldsValues);
    }

    @Override
    public void delete(T object) {
        jdbcTemplate.update(SQL_DELETE_STRING + " WHERE ID = ?", object.getId());
    }

    public void delete() {
        jdbcTemplate.update(SQL_DELETE_STRING);
    }
}
