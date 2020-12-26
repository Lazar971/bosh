/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.main;

import com.duke.maki.bosh.controller.Controller;

/**
 *
 * @author Lazar Milosavljević
 */
public class Main {

    public static void main(String[] args) {

        String input = "C:\\Users/Lazar Milosavljević/Desktop/practical task/input/";
        String output = "C:\\Users/Lazar Milosavljević/Desktop/practical task/output/";

       
        Controller controller = new Controller(input, output);
    }

}
