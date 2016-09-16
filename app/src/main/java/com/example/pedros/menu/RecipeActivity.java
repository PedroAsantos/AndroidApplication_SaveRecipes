package com.example.pedros.menu;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputRecipe;
    EditText inputIngredient;


    Button buttonAddIngredient;
    ListView ingredientList;
    List<String> ingredientArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ingredientArrayList = new ArrayList<String>();
        inputName = (EditText) findViewById(R.id.inputRecipeName);
        inputRecipe = (EditText) findViewById(R.id.inputRecipe);


    }




    public void addButtonClicked(View view){
        DataBaseHandler db = DataBaseHandler.getHelper(this);
        Toast toast;
        String[] nameTest= db.searchNameRecipe(inputName.getText().toString());
        if(inputName.getText().toString().trim().isEmpty()){
            toast = Toast.makeText(this, "Recipe name invalid", Toast.LENGTH_SHORT);
            toast.show();
        }else if(nameTest[0].length()!=0){
                toast = Toast.makeText(this, "Recipe name already exists", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                String ingredients = "";
                for (int index = 0; index < ingredientArrayList.size(); index++) {
                    ingredients += ingredientArrayList.get(index) + " ";
                }
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("name", inputName.getText().toString());
                i.putExtra("recipe", inputRecipe.getText().toString());
                i.putExtra("ingredients", ingredients);
                startActivity(i);
            }
    }

    public void addIngredientClicked(View view){
        inputIngredient = (EditText) findViewById(R.id.ingredientInput);
        if(inputIngredient.getText().toString().trim().isEmpty()){
            Toast toast = Toast.makeText(this, "Ingredient name invalid", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            buttonAddIngredient = (Button) findViewById(R.id.addIngredient);
            ingredientList = (ListView) findViewById(R.id.ingredientList);
            ingredientArrayList.add(inputIngredient.getText().toString());

            ListAdapter adapter = new CustomAdpatertoList(this,ingredientArrayList);

            ingredientList.setAdapter(adapter);
            inputIngredient.setText("");
        }


    }

    public void backButtonClicked(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
