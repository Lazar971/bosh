/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service;

import com.duke.maki.bosh.constants.MapKeyNames;
import com.duke.maki.bosh.domain.Category;
import com.duke.maki.bosh.domain.Product;
import com.duke.maki.bosh.service.load.LoadService;
import com.duke.maki.bosh.service.save.SaveService;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lazar Milosavljević
 */
public class ConvertService {
    
    private final LoadService loadService;
    private final SaveService saveService;

    public ConvertService(LoadService loadService, SaveService saveService) {
        this.loadService = loadService;
        this.saveService = saveService;
    }
    
    
    
    public void convert(String inputLocation,String outputLocation) throws Exception{
        Map<String,Object> map= loadService.load(new FileInputStream(inputLocation));
        List<Product> products=(List<Product>)map.get(MapKeyNames.PRODUCTS);
        
        List<Category> categories=(List<Category>)map.get(MapKeyNames.CATEGORIES);
        
        if(validate(products,categories))
            saveService.save(map,outputLocation);
    }

   private boolean validate(List<Product> products,List<Category> categories) {
       
        for (Product product : products) {
            if(!product.getId().endsWith("-000")){
                return false;
            }
           
            String[] idSplit = product.getId().split("\\-");
            if(!idSplit[0].contains(".")){
                return false;
            }
            String[] idSplit2 = idSplit[0].split("\\.");
            if(idSplit2.length!=3){
                return false;
            }
            if(idSplit2[0].length()!=4 || idSplit2[1].length()!=3 || idSplit2[2].length()!=3
                    || !isAlphaNumeric(idSplit2[0]) || !isAlphaNumeric(idSplit2[1])|| !isAlphaNumeric(idSplit2[2])){
                return false;
            }  
        }
       
        for (Category category : categories) {
            for (int i = 0; i < category.getProducts().size();i++) {
                String product1 = category.getProducts().get(i);
                for(int j =i + 1;j<category.getProducts().size();j++){
                    String product2 = category.getProducts().get(j);
                    if(product1==product2){
                        return false;
                    }
                }
            }
        }
       
        for (Product product : products) {
            int counter = 0;
            for (Category category : categories) {
                for (int i = 0; i < category.getProducts().size(); i++) {
                    String id = category.getId();
                    if(id == product.getId()){
                        counter++;
                    }
                }
            }
            if(counter>1){
                return false;
            }
        }
        return true;
    }
    public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
    
    private<T> void printArray(List<T> arr ){
        arr.forEach((object) -> {
            System.out.println(object);
        });
    }
}
