package com.example.pedros.menu;

/**
 * Created by PedroS on 20-Jul-16.
 */
public class Recipe {

    private String _name;
    private String _recipe;
    private int _id;
    private String _ingredients;

    public Recipe(){

    }

    public Recipe(String name,String recipe,String ingredients){
        this._name=name;
        this._recipe=recipe;
        this._ingredients=ingredients;
    }


    public void set_name(String name){
        this._name=name;
    }

    public void set_recipe(String recipe){
        this._recipe=recipe;
    }

    public void set_id(int id){
        this._id=id;
    }

    public void set_ingredients(String ingredients){this._ingredients=ingredients; }

    public String get_name(){
        return _name;
    }

    public int get_id(){
        return _id;
    }

    public String get_recipe(){
        return _recipe;
    }

    public String get_ingredients(){return _ingredients;}

}
