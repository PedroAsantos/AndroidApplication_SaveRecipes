package com.example.pedros.menu;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends Activity {
    ListView listV;
    DataBaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DataBaseHandler.getHelper(this);


        //--------------------//
        Bundle recipeData = getIntent().getExtras();
        Bundle delClicked = getIntent().getExtras();
        Bundle saveClicked = getIntent().getExtras();

        if(delClicked!=null){
            db.deleteRecipe(delClicked.getString("nameRecipe"));
        }

        if(saveClicked!=null){
            db.updateNameRecipe(saveClicked.getString("originalName"), saveClicked.getString("newNameRecipe"), saveClicked.getString("newRecipe"), saveClicked.getString("newingredients"));
        }

        if(recipeData!=null){
            Recipe recipe = new Recipe(recipeData.getString("name"),recipeData.getString("recipe"),recipeData.getString("ingredients"));
            db.addRecipe(recipe);
        }


        toList();
    }


    public void toList(){

        String s = db.dataBasetoString();
        String[] arrayRecipesString  =s.split("\n");

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayRecipesString);

        listV = (ListView) findViewById(R.id.listView);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemL = String.valueOf(parent.getItemAtPosition(position));

                        String[] recipe = db.searchNameRecipe(itemL);

                        Intent intent = new Intent(view.getContext(),SeeRecipeActivity.class);
                        intent.putExtra("nameRecipe",recipe[0]);
                        intent.putExtra("recipe",recipe[1]);
                        intent.putExtra("ingredients",recipe[2]);
                        startActivity(intent);

                    }
                }
        );

    }

    public void addButtonClicked(View view){
        Intent i = new Intent(this,RecipeActivity.class);
        startActivity(i);

    }


}
