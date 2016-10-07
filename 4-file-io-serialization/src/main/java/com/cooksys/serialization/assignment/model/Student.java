package com.cooksys.serialization.assignment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    private Contact contact;

    public Contact getContact() {
        return contact;
    }
    
    @XmlElement(name="contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
