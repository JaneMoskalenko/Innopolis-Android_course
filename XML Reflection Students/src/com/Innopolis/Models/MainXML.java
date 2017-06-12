package com.Innopolis.Models;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by admin on 12.06.2017.
 */
public class MainXML {

    public static void main(String[] args) throws Exception {
        Student s = new Student("Connor", "John", "Reese", new Date(), 0l);
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootClass = doc.createElement("object");
        doc.appendChild(rootClass);

        Attr attr = doc.createAttribute("type");
        attr.setValue(s.getClass().getSimpleName());
        rootClass.setAttributeNode(attr);

        for (Field f : s.getClass().getDeclaredFields()) {
            Element field = doc.createElement("field");
            rootClass.appendChild(field);
            f.setAccessible(true);

            attr = doc.createAttribute("type");
            attr.setValue(f.getType().getSimpleName());
            field.setAttributeNode(attr);
            attr = doc.createAttribute("id");
            attr.setValue(f.getName());
            field.setAttributeNode(attr);
            attr = doc.createAttribute("value");
            attr.setValue(f.get(s).toString());
            field.setAttributeNode(attr);

        }

        for (Method m : s.getClass().getDeclaredMethods()) {
            Element method = doc.createElement("method");
            rootClass.appendChild(method);

            attr = doc.createAttribute("id");
            attr.setValue(m.getName());
            method.setAttributeNode(attr);
            attr = doc.createAttribute("return");
            attr.setValue(m.getReturnType().toString());
            method.setAttributeNode(attr);
            if (m.getParameterTypes().length>0) {
                Element argument = doc.createElement("arg");
                method.appendChild(argument);
                for (int i=0; i<m.getParameterTypes().length; i++) {
                    attr = doc.createAttribute("id");
                    attr.setValue("arg"+(i+1));
                    argument.setAttributeNode(attr);
                    attr = doc.createAttribute("type");
                    attr.setValue(m.getParameterTypes()[i].getSimpleName());
                    argument.setAttributeNode(attr);
                   // System.out.println(m.getParameterTypes()[0].getName());
                   // System.out.println(m.getParameterTypes());
                }
            }

           /* System.out.println(m.getName() + " "
                    + m.getReturnType().toString() + " " +
                    m.getParameterTypes().length);*/
        }


        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Student.xml"));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        System.out.println("File saved!");

    } catch (ParserConfigurationException pce) {
        pce.printStackTrace();
    } catch (TransformerException tfe) {
        tfe.printStackTrace();
    }
}
}
