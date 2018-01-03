package dk.cngroup.cnu.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class XmlDaoImpl {

    private JdbcTemplate jdbcTemplate;

    private String SQL;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Xml> addXml(Xml xml){
        SQL = "INSERT INTO Xml (id, name, content) VALUES (?, ?, ?)";
        try{
            jdbcTemplate.update( SQL, xml.getId(),xml.getName(), xml.getContent());
        } catch (Exception e){
            System.out.println("An error occurred" + e);   //Exception type?
        } finally {
            return getAll();
        }
    }

    public Xml getXmlById(long id){
        SQL = "SELECT * FROM Xml WHERE id = ?";
        try {
            Xml xml = jdbcTemplate.queryForObject(SQL,
                    new Object[]{id}, new XmlRowMapper());
            return xml;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Xml> getAll(){
        SQL = "SELECT * FROM Xml";
        List <Xml> xmlFiles = jdbcTemplate.query(SQL, new XmlRowMapper());
        return xmlFiles;
    }

    public List<Xml> deleteXml(long id){
        SQL = "DELETE FROM Xml WHERE id = ?";
        try {
            jdbcTemplate.update(SQL, id);
        } catch (Exception e){
            System.out.println("An error occurred" + e);
        } finally {
            return getAll();
        }
    }

    public List<Xml> updateXml(long id, Xml xml){
        SQL = "UPDATE Xml SET name = ?, content = ? WHERE id = ?";
        try{
            jdbcTemplate.update(SQL, xml.getName(), xml.getContent(), id);
        } catch (Exception e) {
            System.out.println("An error occurred" + e);   //Exception type?
        } finally {
            return getAll();
        }
    }
}
