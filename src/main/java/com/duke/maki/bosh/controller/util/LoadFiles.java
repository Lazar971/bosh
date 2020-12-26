/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.controller.util;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Lazar Milosavljević
 */
public class LoadFiles {
    
    private String directory;

    public LoadFiles(String directory) {
        this.directory = directory;
    }
    
    public List<String> execute(){
        List<String> output=new ArrayList<String>();
        File folder=new File(directory);
        File[] files=folder.listFiles();
        for (File file : files) {
            if(file.isFile()){
                output.add(file.getName());
            }
        }
        return output;
    }
    
    
}
