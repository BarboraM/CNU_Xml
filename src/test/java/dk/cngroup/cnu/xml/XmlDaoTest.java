package dk.cngroup.cnu.xml;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XmlDaoTest {

    private XmlDaoImpl xmlDao;

    private Xml xml1;
    private Xml xml2;
    private Xml xml3;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        xmlDao = new XmlDaoImpl();
        xmlDao.setJdbcTemplate(jdbcTemplate);

        xml1 = new Xml(1, "xml1", "abc");
        xml2 = new Xml(2, "xml2", "def");
        xml3 = new Xml(2, "xml3", "ghi");
    }

    @Test
    public void testAddXml() {

        xmlDao.addXml(xml1);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Xml"), 1);

        xmlDao.addXml(xml2);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Xml"), 2);

        xmlDao.addXml(xml3);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Xml"), 2);
    }


    @Test
    public void testGetXmlById(){
        Xml xml;

        xmlDao.addXml(xml1);
        xmlDao.addXml(xml2);

        xml = xmlDao.getXmlById(1);
        assertEquals(xml.toString(), xml1.toString());

        xml = xmlDao.getXmlById(3);
        assertNull(xml);
    }

    @Test
    public void testGetAll(){
        List<Xml>  allXml;

        allXml = xmlDao.addXml(xml1);
        assertThat(allXml, hasSize(1));

        allXml = xmlDao.addXml(xml2);
        assertThat(allXml, hasSize(2));

        allXml = xmlDao.addXml(xml3);
        assertThat(allXml, hasSize(2));
    }

    @Test
    public void testDeleteXml(){
        xmlDao.addXml(xml1);
        xmlDao.addXml(xml2);

        xmlDao.deleteXml(1);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Xml"), 1);

        xmlDao.deleteXml(3);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Xml"), 1);

    }

    @Test
    public void testUpdateXml(){
        List<Xml>  allXml;
        Xml xml;

        xmlDao.addXml(xml1);
        allXml = xmlDao.updateXml(1, xml2);

        xml = allXml.get(0);
        assertEquals(xml.getName(), xml2.getName());
    }
}
