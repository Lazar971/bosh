/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.save;

import java.util.Map;

/**
 *
 * @author Lazar Milosavljević
 */
public interface SaveService {
    
    public void save(Map<String, Object> input, String targetLocation) throws Exception;
}
