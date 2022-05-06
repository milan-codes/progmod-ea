package utils;

import model.Factory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * This class is used to read XML files
 *
 * @param <T> The type of the object to be created from the XML file
 */
public class XMLReader<T> {
    /**
     * List of objects created from the XML file
     */
    private final ArrayList<T> list;
    /**
     * model.Factory interface of the model class T
     */
    private final Factory<T> fact;
    /**
     * Path of the XML file
     */
    private final String filePath;

    /**
     * Constructor
     *
     * @param filePath Path of the XML file
     * @param fact model.Factory interface of the model class T
     */
    public XMLReader(String filePath, Factory<T> fact) {
        this.filePath = filePath;
        this.fact = fact;
        list = new ArrayList<>();
    }

    /**
     * This method is used to invoke the setter method of the model class T
     *
     * @param object The object whose setter method should be invoked
     * @param name   The name of the setter method
     * @param value  The value to be set
     */
    private void invokeSetter(Object object, String name, String value) {
        try {
            Method method = object.getClass().getMethod("set" + name, String.class);
            method.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the list of objects created from the XML file
     *
     * @return The list of objects created from the XML file
     */
    public ArrayList<T> getList() {
        return list;
    }

    /**
     * This method is used to read the XML file and create the objects
     */
    public void read() {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputStream);
            Element root = document.getDocumentElement();

            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList grandChildNodes = node.getChildNodes();
                    T obj = fact.factory();
                    for (int j = 0; j < grandChildNodes.getLength(); j++) {
                        Node grandChildNode = grandChildNodes.item(j);
                        if (grandChildNode.getNodeType() == Node.ELEMENT_NODE) {
                            invokeSetter(obj, UtilFunctions.capitalizeFirstLetter(grandChildNode.getNodeName()), grandChildNode.getTextContent());
                        }
                    }
                    list.add(obj);
                }
            }
        } catch (FileNotFoundException e) {
            T obj = fact.factory();
            System.out.printf("No %s file has been found.", obj.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
