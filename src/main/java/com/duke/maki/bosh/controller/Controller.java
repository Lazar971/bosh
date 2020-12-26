/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.controller;

import com.duke.maki.bosh.form.WindowApp;
import com.duke.maki.bosh.service.ConvertService;
import com.duke.maki.bosh.service.factory.ConvertServiceFactory;
import com.duke.maki.bosh.controller.util.LoadFiles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.duke.maki.bosh.constants.Extensions;
import javax.swing.JOptionPane;

/**
 *
 * @author Lazar Milosavljević
 */
public class Controller {
    
    private String inputDir;
    private String outputDir;
    private final WindowApp app;
    public Controller(String inputDir, String outputDir) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;
        app=new WindowApp(this);
        app.setVisible(true);
    }

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public List<String> getInputFiles() {
        LoadFiles lf=new LoadFiles(inputDir);
        return lf.execute();
        
    }

    public String getExtention(String item) {
        String[] exploded=item.split("\\.");
        
        String ext=exploded[exploded.length-1];
        if(ext.equals(Extensions.XML_EXTENSION)){
            return Extensions.CSV_EXTENSION;
        }else{
            return Extensions.XML_EXTENSION;
        }
        
    }

    public void convert(String inputName,String outpuName, String format) {
        
        
        ConvertService service=ConvertServiceFactory.create(format);
        try {
            service.convert(inputDir+inputName, outputDir+outpuName);
            JOptionPane.showMessageDialog(this.app, "Success");
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(app, "Failure");
        }
    }

   
    
    
}
