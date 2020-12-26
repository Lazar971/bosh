/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.load;

import com.duke.maki.bosh.constants.CategoryProperties;
import com.duke.maki.bosh.constants.MapKeyNames;
import com.duke.maki.bosh.constants.ProductProperties;
import com.duke.maki.bosh.domain.Category;
import com.duke.maki.bosh.domain.Product;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lazar Milosavljević
 */
public class LoadXMLService implements LoadService {

    private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    @Override
    public Map<String,Object> load(String source) throws Exception {
        Map<String,Object> resultMap=new HashMap<>();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new BufferedInputStream(new FileInputStream(source)));
        Element root = document.getDocumentElement();
        Node productTag = root.getElementsByTagName("items").item(0);
       
        Node categoryTag = root.getElementsByTagName("categories").item(0);
        List<Product> productList = new LinkedList<>();
        List<Category> categoryList = new LinkedList<>();
        NodeList products = productTag.getChildNodes();
        
        NodeList categories = categoryTag.getChildNodes();
        for (int i = 1; i < products.getLength(); i+=2) {
            Node productNode = products.item(i);
          
            
            Product p = new Product();
            NodeList properties = productNode.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
               // System.out.println(properties.item(j).getTextContent());
                Node property =properties.item(j);
                NamedNodeMap attrMap=property.getAttributes();
                if(attrMap!=null){
                    String propertyName=attrMap.getNamedItem("propertyName").getNodeValue();
                    String propertyValue=property.getChildNodes().item(1).getTextContent();
                    if(propertyName.equals(ProductProperties.PRODUCT_ID)){
                        p.setId(propertyValue);
                        continue;
                    }
                    if(propertyName.equals(ProductProperties.PRODUCT_NAME)){
                        p.setName(propertyValue);
                        continue;
                    }
                    if(propertyName.equals(ProductProperties.PRODUCT_DESCRIPTION)){
                        p.setDescription(propertyValue);
                        
                    }
                    
                }
            }
            productList.add(p);
        }
        for (int i = 0; i < categories.getLength(); i++) {
            Node categoryNode = categories.item(i);
            
            
            Category cat = new Category();
            NodeList properties = categoryNode.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
               // System.out.println(properties.item(j).getTextContent());
                Node property =properties.item(j);
                NamedNodeMap attrMap=property.getAttributes();
                if(attrMap!=null){
                    String propertyName=attrMap.getNamedItem("propertyName").getNodeValue();
                    String propertyValue=property.getChildNodes().item(1).getTextContent();
                    if(propertyName.equals(CategoryProperties.CATEGORY_ID)){
                        cat.setId(propertyValue);
                        continue;
                    }
                    if(propertyName.equals(CategoryProperties.CATEGORY_NAME)){
                        cat.setName(propertyValue);
                        continue;
                    }
                    if(propertyName.equals(CategoryProperties.CATEGORY_PRODUCTS)){
                        NodeList productLinks=property.getChildNodes().item(1).getChildNodes();
                        
                        for (int k = 0; k < productLinks.getLength(); k++) {
                            NamedNodeMap linksMap=productLinks.item(k).getAttributes();
                            if(linksMap!=null){
                                String productLinkId=linksMap.getNamedItem("id").getNodeValue();
                                cat.getProducts().add(productLinkId);
                            }
                        }
                        
                    }
                    categoryList.add(cat);
                }
            }
            
        }
        resultMap.put(MapKeyNames.PRODUCTS, productList);
        resultMap.put(MapKeyNames.CATEGORIES, categoryList);
       return resultMap;
    }

}
