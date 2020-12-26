/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.load;

import java.util.Map;

/**
 *
 * @author Lazar Milosavljević
 */
public interface LoadService {
    
    public Map<String,Object> load(String source) throws Exception;
}
