/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.factory;

import com.duke.maki.bosh.service.ConvertService;
import com.duke.maki.bosh.service.load.LoadCsvService;
import com.duke.maki.bosh.service.load.LoadXMLService;
import com.duke.maki.bosh.service.save.SaveCsvService;
import com.duke.maki.bosh.service.save.SaveXMLService;
import com.duke.maki.bosh.constants.Extensions;

/**
 *
 * @author Lazar Milosavljević
 */
public class ConvertServiceFactory {
    
    public static ConvertService create(String extension  ){
        if(extension.equals(Extensions.XML_EXTENSION)){
            return new ConvertService(new LoadCsvService(), new SaveXMLService());
        }
        return new ConvertService(new LoadXMLService(), new SaveCsvService());
    }
}
