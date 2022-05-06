package utils;

import model.Factory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class XMLWriter<T> {
    /**
     * List of objects to be written to XML
     */
    private final ArrayList<T> list;
    /**
     * model.Factory interface of class T
     */
    private final Factory<T> fact;
    /**
     * Path to XML file
     */
    private final String filePath;
    /**
     * Properties of class T
     */
    private final ArrayList<String> properties;

    /**
     * Constructor
     *
     * @param list     ArrayList of objects to be written to XML
     * @param fact     model.Factory interface of class T
     * @param filePath Path of the XML file
     */
    public XMLWriter(String filePath, Factory<T> fact, ArrayList<T> list) {
        this.filePath = filePath;
        this.fact = fact;
        this.list = list;
        this.properties = new ArrayList<>();
    }

    /**
     * Gets properties of an object
     *
     * @param object Object to get properties from
     */
    private void getProperties(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.getName().startsWith("get")) {
                properties.add(method.getName().substring(3));
            }
        }
    }

    /**
     * This method is used to invoke the getter methods of an object
     *
     * @param object Object which getters are to be invoked
     * @param property Method to be invoked
     */
    private String invokeGetter(Object object, String property) {
        try {
            Method method = object.getClass().getMethod("get" + property);
            return method.invoke(object).toString();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is used to write objects to XML
     */
    public void write() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            T obj = fact.factory();
            String objName = obj.getClass().getSimpleName().toLowerCase();
            Element root = doc.createElement(objName + "-root");
            getProperties(obj);

            for (T object : list) {
                Element child = doc.createElement(objName);
                for (String property : properties) {
                    if (!property.equals("Class")) {
                        Element grandChild = doc.createElement(property);
                        grandChild.setTextContent(invokeGetter(object, property));
                        child.appendChild(grandChild);
                    }

                }
                root.appendChild(child);
            }

            doc.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(filePath);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
