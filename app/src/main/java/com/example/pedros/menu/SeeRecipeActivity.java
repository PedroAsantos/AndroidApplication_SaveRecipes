package com.example.pedros.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeeRecipeActivity extends AppCompatActivity {

    EditText inputIngredient;
    EditText editName;
    EditText editRecipe;
    Bundle mainData;
    EditText editIngredients;
    ListView ingredientList;
    List<String> ingredientArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_recipe);

        inputIngredient = (EditText) findViewById(R.id.ingredientInput);

        editName = (EditText) findViewById(R.id.inputRecipeName);
        editRecipe = (EditText) findViewById(R.id.inputRecipe);

        mainData = getIntent().getExtras();
        String engre;
        if (mainData == null) {
            return;
        } else {
            editName.setText(mainData.getString("nameRecipe"));
            editRecipe.setText(mainData.getString("recipe"));
            engre = mainData.getString("ingredients");
        }


        ingredientsToList(engre);

    }

    public void ingredientsToList(String engre){

        ingredientList = (ListView) findViewById(R.id.ingredientList);

        ingredientArrayList = new ArrayList<String>(Arrays.asList(engre.split(" ")));
        ListAdapter adapter = new CustomAdpatertoList(this,new ArrayList<String>(Arrays.asList(engre.split(" "))));

        ingredientList.setAdapter(adapter);



    }
    public void saveButtonClicked(View view){

       if(editName.getText().toString().trim().isEmpty()){
           Toast toast = Toast.makeText(this, "Recipe name invalid", Toast.LENGTH_SHORT);
           toast.show();
       }else{
           String ingredients = "";
           for(int index = 0;index<ingredientArrayList.size();index++){
               ingredients+= ingredientArrayList.get(index)+" ";
           }

           Intent i = new Intent(this,MainActivity.class);

           i.putExtra("originalName",mainData.getString("nameRecipe"));
           i.putExtra("newNameRecipe",editName.getText().toString());
           i.putExtra("newRecipe",editRecipe.getText().toString());
           i.putExtra("newingredients",ingredients);

           startActivity(i);
       }
    }
    public void delButtonClicked(View view){

        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("nameRecipe", mainData.getString("nameRecipe"));
        startActivity(i);


    }
    public void addIngredientClicked(View view){
        if(inputIngredient.getText().toString().trim().isEmpty()){
            Toast toast = Toast.makeText(this, "Ingredient name invalid", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            ingredientList = (ListView) findViewById(R.id.ingredientList);
            ingredientArrayList.add(inputIngredient.getText().toString());

            ListAdapter adapter = new CustomAdpatertoList(this, ingredientArrayList);

            ingredientList.setAdapter(adapter);
            inputIngredient.setText("");
        }

    }
    public void backButtonClicked(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
}
