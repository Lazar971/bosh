/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.save;

import com.duke.maki.bosh.constants.MapKeyNames;
import com.duke.maki.bosh.domain.Category;
import com.duke.maki.bosh.domain.Product;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lazar Milosavljević
 */
public class SaveXMLService implements SaveService {

    @Override
    public void save(Map<String, Object> input, String targetLocation) throws Exception {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetLocation + ".xml")));
        List<Product> products = (List<Product>) input.get(MapKeyNames.PRODUCTS);
        List<Category> categories = (List<Category>) input.get(MapKeyNames.CATEGORIES);
        String [] productProperties=new String[]{"id","name","description"};
        String [] categoryBaseProperties=new String[]{"id","name"};
        writer.println("<?xml version=\"1.0\"?>");
        writer.println("<root>");
        writer.println("\t<items>");
        for (Product product : products) {
             String [] productPropertiesValues=new String[]{product.getId(),product.getName(),product.getDescription()};
            writer.println("\t\t<item>");
            for (int i=0;i<productProperties.length;i++) {
                writer.println("\t\t\t<property propertyName=\""+productProperties[i]+"\" >");
                writer.println("\t\t\t\t<value>"+productPropertiesValues[i]+"</value>");
                writer.println("\t\t\t</property>");
                
            }
            writer.println("\t\t</item>");
        }
        writer.println("\t</items>");
        writer.println("\t<categories>");
        categories.stream().map((category) -> {
            String [] categoryBasePropertiesValues=new String[]{category.getId(),category.getName()};
            writer.println("\t\t<category>");
            for (int i=0;i<categoryBaseProperties.length;i++) {
                writer.println("\t\t\t<property propertyName=\""+categoryBaseProperties[i]+"\" >");
                writer.println("\t\t\t\t<value>"+categoryBasePropertiesValues[i]+"</value>");
                writer.println("\t\t\t</property>");
                
            }
            writer.println("\t\t\t<property propertyName=\"products\">");
            writer.println("\t\t\t\t<productLinks>");
            category.getProducts().forEach((product) -> {
                writer.println("\t\t\t\t\t<productLink id=\""+product+"\"/>");
            });
            return category;            
        }).map((_item) -> {
            writer.println("\t\t\t\t</productLinks>");
            return _item;
        }).map((_item) -> {
            writer.println("\t\t\t</property>");
            return _item;
        }).forEachOrdered((_item) -> {
            writer.println("\t\t</category>");
        });
        writer.println("\t</categories>");
        writer.println("</root>");
        writer.close();
    }

}
