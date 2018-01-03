package dk.cngroup.cnu.xml;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XmlServiceTest {

    @Autowired
    private XmlService xmlService;

    @MockBean
    private XmlDaoImpl xmlDao;

    @Test
    public void testAddXml(){

        xmlService.addXml(new Xml(1, "Xml1", "abcPHPabc"));

        verify(xmlDao, times(0)).addXml(Matchers.any());

        xmlService.addXml(new Xml(1, "Xml1", "abc"));

        verify(xmlDao, times(1)).addXml(Matchers.any());
    }
}
