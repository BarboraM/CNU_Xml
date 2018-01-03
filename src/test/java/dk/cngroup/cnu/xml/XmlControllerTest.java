package dk.cngroup.cnu.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XmlControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private XmlService xmlService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        Xml xml1 = new Xml(1, "Xml1", "abc");
        Xml xml2 = new Xml(2, "Xml2", "def");
        Xml xml3 = new Xml(1, "Xml3", "ghi");
        Xml xml4 = new Xml(4, "Xml4", "jkl");

        List<Xml> xmlFiles = Arrays.asList(xml1, xml2);
        when(xmlService.getAllXmlFiles()).thenReturn(xmlFiles);

        when(xmlService.getXmlById(1)).thenReturn(xmlFiles.get(0));
        when(xmlService.getXmlById(2)).thenReturn(xmlFiles.get(1));

        when(xmlService.deleteXmlById(1)).thenReturn(Arrays.asList(xmlFiles.get(1)));
        when(xmlService.deleteXmlById(3)).thenReturn(Arrays.asList(xmlFiles.get(1)));

        when(xmlService.updateXml(1, xml3)).thenReturn(Arrays.asList(xml3, xml2));
        when(xmlService.updateXml(4, xml4)).thenReturn(Arrays.asList(xml3, xml2));

        when(xmlService.addXml(any())).thenReturn(Arrays.asList(xml1, xml2, xml3));

    }

    @Test
    public void testGetAllXmlFiles() throws Exception{
        mockMvc.perform(get("/xml"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Xml1")))
                .andExpect(jsonPath("$[0].content", is("abc")))
                .andExpect(jsonPath("$[1].name", is("Xml2")));
    }

    @Test
    public void testAddXml() throws Exception {
        Xml xml = new Xml(1, "Xml3", "ghi");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(xml);

        mockMvc.perform(post("/xml/new")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].id", is(1)))
                .andExpect(jsonPath("$[2].name", is("Xml3")))
                .andExpect(jsonPath("$[2].content", is("ghi")));

        mockMvc.perform(post("/xml/new")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetXmlById() throws Exception {
        mockMvc.perform(get("/xml/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Xml1")))
                .andExpect(jsonPath("$.content", is("abc")));


        mockMvc.perform(get("/xml/4"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteXmlById() throws Exception {
        mockMvc.perform(delete("/xml/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));


        mockMvc.perform(delete("/xml/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testUpdateXml() throws Exception {
        Xml xml = new Xml(1, "Xml3", "ghi");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(xml);

        mockMvc.perform(put("/xml/update")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Xml3")))
                .andExpect(jsonPath("$[0].content", is("ghi")));
    }
}

