/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.load;

import com.duke.maki.bosh.constants.MapKeyNames;
import com.duke.maki.bosh.domain.Category;
import com.duke.maki.bosh.domain.Product;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lazar Milosavljević
 */
public class LoadCsvService implements LoadService {

  
    
    
    @Override
    public Map<String, Object> load(InputStream stream) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MapKeyNames.CATEGORIES, new LinkedList<>());
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        String line = reader.readLine();
        while (line != null && !line.startsWith("INSERT_UPDATE")) {
            line = reader.readLine();
        }
        if (line == null) {
            throw new RuntimeException("no insert update line specified");
        }
        String objectName = line.split(";")[0].split(" ")[1];
        if (objectName.equals("Product")) {
            
            List<Product> products = readProducts(reader, line);
            resultMap.put(MapKeyNames.PRODUCTS, products);
            line = reader.readLine();
            while (line != null && !line.startsWith("INSERT_UPDATE")) {
                line = reader.readLine();
            }
            if (line == null) {
                throw new RuntimeException("no insert update line specified");
            }
            List<Category> categories = readCategories(reader, line);
            
            resultMap.put(MapKeyNames.CATEGORIES, categories);
        } else {
            List<Category> categories = readCategories(reader, line);
            resultMap.put(MapKeyNames.CATEGORIES, categories);
            line = reader.readLine();
            while (line != null && !line.startsWith("INSERT_UPDATE")) {
                line = reader.readLine();
            }
            if (line == null) {
                throw new RuntimeException("no insert update line specified");
            }
            List<Product> products = readProducts(reader, line);
            resultMap.put(MapKeyNames.PRODUCTS, products);
        }

        return resultMap;
    }

    private List<Product> readProducts(BufferedReader reader, String line) throws IOException {
        List<Product> products = new LinkedList<>();
        String[] rowArray = line.split(";");
        int idPos = -1;
        int namePos = -1;
        int descPos = -1;
        
        for (int i = 0; i < rowArray.length; i++) {
            if (rowArray[i].equals("id")) {
                idPos = i;
                continue;
            }
            if (rowArray[i].equals("name")) {
                namePos = i;
                continue;
            }
            if (rowArray[i].equals("description")) {
                descPos = i;
            }
        }
        line = reader.readLine();
        while (line != null && line.split(";").length>3) {

            rowArray = line.split(";");
            
            Product p = new Product();
            p.setId(rowArray[idPos]);
            p.setDescription(rowArray[descPos]);
            p.setName(rowArray[namePos]);
            products.add(p);
            line = reader.readLine();
        }
        return products;
    }

    private List<Category> readCategories(BufferedReader reader, String line) throws IOException {
        
        List<Category> categories = new LinkedList<>();
        String[] rowArray = line.split(";");
        int idPos = -1;
        int namePos = -1;
        int productsPos = -1;
        for (int i = 0; i < rowArray.length; i++) {
            if (rowArray[i].equals("id")) {
                idPos = i;
                continue;
            }
            if (rowArray[i].equals("name")) {
                namePos = i;
                continue;
            }
            if (rowArray[i].equals("products")) {
                productsPos = i;
            }
        }
        line = reader.readLine();
        while (line != null && line.split(";").length>3) {
           
            rowArray = line.split(";");
            Category cat = new Category();
            cat.setId(rowArray[idPos]);
            cat.setName(rowArray[namePos]);

            String[] productLinks = rowArray[productsPos].split(",");
            for (int j = 0; j < productLinks.length; j++) {
                cat.getProducts().add(productLinks[j]);
            }

            categories.add(cat);
            line = reader.readLine();
        }
        return categories;
    }

}
