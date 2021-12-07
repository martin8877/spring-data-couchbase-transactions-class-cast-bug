package com.example;

import java.util.List;

public class MenuCategory {

    private String id;

    private List<CategoryProduct> products;

    public String getId() {
        return id;
    }

    public List<CategoryProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CategoryProduct> products) {
        this.products = products;
    }

    public String toString(){
        return ("{ id:"+id+", products:"+products)+"}";
    }

    public static class CategoryProduct {

        private String id;

        public CategoryProduct() {
        }

        public String toString(){
            return "{ id:"+id+"}";
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
