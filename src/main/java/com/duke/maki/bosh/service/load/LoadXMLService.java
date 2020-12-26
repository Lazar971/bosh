/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.service.load;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lazar Milosavljević
 */
public class LoadXMLService implements LoadService{

    private DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
    
    
    
    @Override
    public void load(String source) throws Exception {
       DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document= builder.parse(new BufferedInputStream(new FileInputStream(source)));
        Element root=document.getDocumentElement();
        NodeList products=root.getElementsByTagName("items");
        NodeList categories=root.getElementsByTagName("categories");
        
        
    }
    
}
