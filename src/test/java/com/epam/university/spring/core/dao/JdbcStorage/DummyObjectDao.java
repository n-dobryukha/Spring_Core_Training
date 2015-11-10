package com.epam.university.spring.core.dao.JdbcStorage;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-10
 * Time: 17:40
 */
public class DummyObjectDao extends GenericDaoImpl<DummyObject> {
    public DummyObjectDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, DummyObject.class,
                "INSERT INTO DUMMY_TABLE (NAME) VALUES(?)",
                "SELECT * FROM DUMMY_TABLE",
                "UPDATE DUMMY_TABLE SET NAME = ? WHERE ID = ?",
                "DELETE FROM DUMMY_TABLE"
        );
    }

    private void createTable() {
        String createString = "CREATE TABLE DUMMY_TABLE "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT USER_PK PRIMARY KEY, "
                +  " NAME VARCHAR(50) NOT NULL)";
        getJdbcTemplate().execute(createString);
    }
}
