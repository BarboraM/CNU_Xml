package dk.cngroup.cnu.xml;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class XmlRowMapper implements RowMapper<Xml> {

    @Override
    public Xml mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String content = rs.getString("content");
            return new Xml(id, name, content);
    }
}
