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
        Map<String,Object> map= loadService.load(inputLocation);
        List<Product> products=(List<Product>)map.get(MapKeyNames.PRODUCTS);
        
        List<Category> categories=(List<Category>)map.get(MapKeyNames.CATEGORIES);
        
        validate();
        saveService.save(map,outputLocation);
    }

    private void validate() {

    }
    
    
    private<T> void printArray(List<T> arr ){
        arr.forEach((object) -> {
            System.out.println(object);
        });
    }
}
