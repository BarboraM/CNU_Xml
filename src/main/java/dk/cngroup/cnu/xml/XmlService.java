package dk.cngroup.cnu.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XmlService {

    @Autowired
    private XmlDaoImpl xmlDao;

    public XmlService(XmlDaoImpl xmlDao){
        this.xmlDao = xmlDao;
    }

    public List<Xml> addXml(Xml xml){
        if(checkDocument(xml)) {
            return xmlDao.addXml(xml);
        }
        else{
            return xmlDao.getAll();
        }
    }

    public Xml getXmlById(long id){
        return xmlDao.getXmlById(id);
    }

    public List<Xml> getAllXmlFiles(){

        return xmlDao.getAll();
    }

    public List<Xml> deleteXmlById(long id){

        return xmlDao.deleteXml(id);
    }

    public List<Xml> updateXml(long id, Xml xml){

        if(checkDocument(xml)) {
            return xmlDao.updateXml(id, xml);
        }
        else{
            return xmlDao.getAll();
        }
    }

    private boolean checkDocument(Xml xml) {
        return (xml.getContent().length() < 10000) && ( !xml.getContent().contains("PHP"));
    }
}
