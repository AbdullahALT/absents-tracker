package com.example.abo_tam.photosgalary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abo_tam on 8/19/2015.
 */
public class CourceAdapter extends ArrayAdapter<Cource> {


    public CourceAdapter(Context context, Cource[] objects) {
        super(context, R.layout.list_view, objects);

    }

    public CourceAdapter(Context context, List<Cource> objects) {
        super(context, R.layout.list_view, objects);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View courceView = inflater.inflate(R.layout.list_view, parent, false);

        TextView courceName = (TextView) courceView.findViewById(R.id.courseName);
        TextView absents = (TextView) courceView.findViewById(R.id.absentCounter);
        Button increase = (Button) courceView.findViewById(R.id.increase);
        Button decrease = (Button) courceView.findViewById(R.id.decrease);


        courceName.setText(getItem(position).getName());
        absents.setText(String.valueOf(getItem(position).getAbsents()));

        increase.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(MainActivity.TAG, "increase button clicked on " + getItem(position).getName());
                        MainActivity.increaseAbsent(getItem(position));
                        //TODO: Handle absents increases here
                    }
                }
        );

        decrease.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(MainActivity.TAG, "decrease button clicked on " + getItem(position).getName());
                        MainActivity.decreaseAbsent(getItem(position));

                        //TODO: Handle absents decrease here
                    }
                }
        );

        courceName.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //TODO: DELETE ROWS HERE
                        MainActivity.deleteCourse(getItem(position));
                        return true;
                    }
                }

        );


        return courceView;
    }
}
