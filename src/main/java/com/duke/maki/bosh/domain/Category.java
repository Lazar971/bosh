/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duke.maki.bosh.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lazar Milosavljević
 */
public class Category {
    
    private String id;
    private String name;
    private List<String> products=new LinkedList<>();

    public Category(String id, String name, List<String> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Category() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", productsSize=" + products.size() + '}';
    }
    
    
    
}
