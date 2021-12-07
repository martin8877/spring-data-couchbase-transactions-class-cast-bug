package com.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;

import java.util.List;

@Document
public class Menu {

    public static final String PREFIX = "menu";
    private static final String ID_DELIMITER = "#";

    @IdPrefix
    private final String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE, delimiter = ID_DELIMITER) private String id;

    @Field
    private String name;

    @Field private List<MenuCategory> categories;

    public String getPrefix() {
        return prefix;
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

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategory> categories) {
        this.categories = categories;
    }

    public String toString(){
        return "{ id: "+ id+", name:"+name+", categories:"+categories+"}";
    }
}
