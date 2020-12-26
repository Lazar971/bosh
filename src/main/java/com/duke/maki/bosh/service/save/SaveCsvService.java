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
public class SaveCsvService implements SaveService{

    @Override
    public void save(Map<String, Object> input, String targetLocation) throws Exception {
        
        PrintWriter writer=new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetLocation+".csv")));
        List<Product> products=(List<Product>)input.get(MapKeyNames.PRODUCTS);
        List<Category> categories=(List<Category>)input.get(MapKeyNames.CATEGORIES);
        writer.println("##products\n\nINSERT_UPDATE Product;id;name;description;");
        products.forEach((product) -> {
            writer.println(";"+product.getId()+";"+product.getName()+";"+product.getDescription()+";");
        });
        writer.println("");
        
        writer.println("##categories\n\nINSERT_UPDATE Category;id;name;products;");
        categories.stream().map((category) -> {
            writer.print(";"+category.getId()+";"+category.getName()+";");
            return category;
        }).map((category) -> {
            String productLinks="";
            productLinks = category.getProducts().stream().map((product) -> product+",").reduce(productLinks, String::concat);
            return productLinks;
        }).map((productLinks) -> productLinks.substring(0,productLinks.length()-1)).forEachOrdered((productLinks) -> {
            writer.println(productLinks+";");
        });
        writer.println();
        writer.close();
    }

    
    
}
