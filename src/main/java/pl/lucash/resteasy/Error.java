package pl.lucash.resteasy;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {
    private String message;
    private String clazz;

    Error(String message, String clazz) {
        this.message = message;
        this.clazz = clazz;
    }

    public String getMessage() {
        return message;
    }

    public String getClazz() {
        return clazz;
    }
}
