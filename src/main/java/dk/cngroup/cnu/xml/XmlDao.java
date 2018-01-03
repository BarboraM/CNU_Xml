package dk.cngroup.cnu.xml;

import java.util.List;

public interface XmlDao {
    public void createTable();
    public void addXml(Xml xml);
    public Xml getXmlById(long id);
    public List<Xml> getAll();
    public void deleteXml(long id);
    public void updateXml(long id, Xml xml);

}
