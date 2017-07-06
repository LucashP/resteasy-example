package pl.lucash.resteasy.infrastructure;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {
    private String message;
    private String clazz;
    private String test = "Custom Error";

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

    public String getTest() {
        return test;
    }
}
