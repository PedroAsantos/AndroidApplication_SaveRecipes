package com.example.pedros.menu;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Created by PedroS on 27-Jul-16.
 */
class CustomAdpatertoList extends ArrayAdapter<String> {
    private List<String> ingrList;


    CustomAdpatertoList(Context context, List<String> ingredients) {
        super(context, R.layout.custom_list, ingredients);
        ingrList = ingredients;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_list, parent, false);

        FloatingActionButton deleteBtn = (FloatingActionButton) customView.findViewById(R.id.delIngredientButton);
        deleteBtn.setTag(position);
        String singleIngredient = getItem(position);
        TextView ingedientText = (TextView) customView.findViewById(R.id.ingredientInList);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Integer index = (Integer) v.getTag();
                ingrList.remove(index.intValue());
                notifyDataSetChanged();
            }
        });

        ingedientText.setText(singleIngredient);

        return customView;
    }


}
