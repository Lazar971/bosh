/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service;

import com.duke.maki.bosh.service.load.LoadService;
import com.duke.maki.bosh.service.save.SaveService;

/**
 *
 * @author Lazar Milosavljević
 */
public class ConvertService {
    
    private LoadService loadService;
    private SaveService saveService;

    public ConvertService(LoadService loadService, SaveService saveService) {
        this.loadService = loadService;
        this.saveService = saveService;
    }
    
    
    
    public void convert(String inputLocation,String outputLocation) throws Exception{
        loadService.load(inputLocation);
        validate();
        saveService.save();
    }

    private void validate() {

    }
}
