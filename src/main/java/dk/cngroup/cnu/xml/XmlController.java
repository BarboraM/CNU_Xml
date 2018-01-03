package dk.cngroup.cnu.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
public class XmlController {

    @Autowired
    private XmlService xmlService;

    @RequestMapping(value = "/xml",method = RequestMethod.GET)
    public Collection<Xml> getAllXmlFiles(){

        return xmlService.getAllXmlFiles();
    }

    @RequestMapping(value = "/xml/{id}", method = RequestMethod.GET)
    public Xml getXmlById(@PathVariable("id") long id){
        return xmlService.getXmlById(id);

    }

    @RequestMapping(value = "/xml/{id}", method = RequestMethod.DELETE)
    public Collection<Xml> deleteXmlById(@PathVariable("id") int id){

        return xmlService.deleteXmlById(id);
    }

    @RequestMapping(value = "/xml/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Xml> updateXmlById(@Valid @RequestBody Xml xml){

        return xmlService.updateXml(xml.getId(), xml);
    }

    @RequestMapping(value = "/xml/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Xml> addXml(@Valid @RequestBody  Xml xml){

        return xmlService.addXml(xml);
    }
}
