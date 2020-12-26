/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.controller;

/**
 *
 * @author Lazar Milosavljević
 */
public class Controller {
    
    private String inputDir;
    private String outputDir;

    public Controller(String inputDir, String outputDir) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;
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
    
    
    
}
