package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.AuditoriumDao;
import com.epam.university.spring.core.domain.Auditorium;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita Dobriukha
 * Date: 15.11.2015.
 */
public class AuditoriumDaoImpl extends GenericDaoImpl<Auditorium> implements AuditoriumDao {
    public AuditoriumDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Auditorium.class,
                "INSERT INTO AUDITORIUMS (NAME, NUMBER_OF_SEATS, VIP_SEATS) VALUES(?,?,?)",
                "SELECT * FROM AUDITORIUMS",
                "UPDATE AUDITORIUMS SET NAME = ?, NUMBER_OF_SEATS = ?, VIP_SEATS = ? WHERE ID = ?",
                "DELETE FROM AUDITORIUMS",
                new RowMapper<Auditorium>() {
                    public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Auditorium auditorium = new Auditorium();
                        auditorium.setId(rs.getLong("ID"));
                        auditorium.setName(rs.getString("NAME"));
                        auditorium.setNumberOfSeats(rs.getInt("NUMBER_OF_SEATS"));
                        String vipSeatsString = rs.getString("VIP_SEATS").replace(" ", "");
                        String[] vipSeatsStringArray = vipSeatsString.substring(1,vipSeatsString.length()-1).split(",");
                        List<Integer> vipSeats = new ArrayList<>();
                        for (int i=0; i<vipSeatsStringArray.length; i++) {
                            vipSeats.add(Integer.parseInt(vipSeatsStringArray[i]));
                        }
                        auditorium.setVipSeats(vipSeats);
                        return auditorium;
                    }
                }
        );
    }

    public void createTable() {
        String createString = "CREATE TABLE AUDITORIUMS "
                +  "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                +  " CONSTRAINT AUDITORIUMS_PK PRIMARY KEY, "
                +  " NAME VARCHAR(50) NOT NULL, "
                +  " NUMBER_OF_SEATS INT NOT NULL, "
                +  " VIP_SEATS VARCHAR(100) NOT NULL)";
        getJdbcTemplate().execute(createString);
        getJdbcTemplate().execute("INSERT INTO AUDITORIUMS "
                +  " (NAME, NUMBER_OF_SEATS, VIP_SEATS) "
                +  " VALUES('RED', 50, '[1,2,3,4,5]')");
        getJdbcTemplate().execute("INSERT INTO AUDITORIUMS "
                +  " (NAME, NUMBER_OF_SEATS, VIP_SEATS) "
                +  " VALUES('BLUE', 100, '[91,92,93,94,95,96,97,98,99,100]')");
    }
}
