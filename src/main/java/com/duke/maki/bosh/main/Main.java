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

        if(args.length<2){
            System.out.println("Not enough args");
            return;
        }
        String input=args[0];
        String output=args[1];
       
        Controller controller = new Controller(input, output);
    }

}
