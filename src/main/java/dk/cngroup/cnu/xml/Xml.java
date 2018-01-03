package dk.cngroup.cnu.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Xml {
    private long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 3)
    private String content;


    public Xml(){}

    public Xml(long id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "XML file{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
