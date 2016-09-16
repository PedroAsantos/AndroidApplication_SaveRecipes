package com.example.pedros.menu;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by PedroS on 20-Jul-16.
 */
public class DataBaseHandler extends SQLiteOpenHelper{


    private static DataBaseHandler instance;
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME ="recipes6.db";

    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME= "recipename";
    public static final String COLUMN_RECIPE = "recipe";
    public static final String COLUMN_INGREDIENTS = "ingredients";


    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public static synchronized DataBaseHandler getHelper(Context context)
    {
        if (instance == null)
            instance = new DataBaseHandler(context,null,null,1);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_RECIPES + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPENAME + " TEXT, " + COLUMN_RECIPE + " TEXT, " + COLUMN_INGREDIENTS + " TEXT"
                + ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS" + TABLE_RECIPES);
        onCreate(db);
    }

    public String[] searchNameRecipe(String name){

        String[] dbString = {"","",""};
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+ TABLE_RECIPES + " WHERE 1";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("recipename"))!=null){
                if(name.equals(c.getString(c.getColumnIndex(("recipename"))))){
                    dbString[0] += c.getString(c.getColumnIndex("recipename"));
                    dbString[1] += c.getString(c.getColumnIndex("recipe"));
                    dbString[2] += c.getString(c.getColumnIndex("ingredients"));
                }

            }
            c.moveToNext();
        }

        db.close();

        return dbString;

    }

    public void addRecipe(Recipe recipe){

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME,recipe.get_name());
        values.put(COLUMN_RECIPE,recipe.get_recipe());
        values.put(COLUMN_INGREDIENTS,recipe.get_ingredients());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public void deleteRecipe(String productName){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPENAME + "=\"" + productName + "\";");
        db.close();  //adicionei depois

    }

    public void updateRecipe(String name, String new_recipe,String ingredients){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE "+ TABLE_RECIPES +" SET " + COLUMN_RECIPE + " = '"+ new_recipe+ " SET " + COLUMN_INGREDIENTS + " = '" + ingredients + "' WHERE "+ COLUMN_RECIPENAME + " = "+name);
        db.close();
    }

    public void updateNameRecipe(String name,String new_name, String new_recipe,String new_ingredients){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE "+ TABLE_RECIPES +" SET " + COLUMN_RECIPE + " = '"+ new_recipe+"', "+ COLUMN_INGREDIENTS+ " = '"+ new_ingredients+"', "+COLUMN_RECIPENAME+ " = '"+ new_name +"' WHERE "+ COLUMN_RECIPENAME + " = '"+name+"'");

        db.close();
    }



    public String dataBasetoString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+ TABLE_RECIPES + " WHERE 1";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("recipename"))!=null){
                dbString += c.getString(c.getColumnIndex("recipename"));
                dbString+= "\n";
            }
            c.moveToNext();
        }

        db.close();
        return dbString;

    }




}
