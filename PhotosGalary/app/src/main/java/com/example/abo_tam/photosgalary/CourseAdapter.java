package com.example.abo_tam.photosgalary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
public class CourseAdapter extends ArrayAdapter<Course> {

    private static CourseDatabase db;

    public CourseAdapter(Context context, Course[] objects) {
        super(context, R.layout.list_view, objects);
        db = new CourseDatabase(MainActivity.getActivity());

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View courseView = inflater.inflate(R.layout.list_view, parent, false);

        TextView courseName = (TextView) courseView.findViewById(R.id.courseName);
        TextView absents = (TextView) courseView.findViewById(R.id.absentCounter);
        Button increase = (Button) courseView.findViewById(R.id.increase);
        Button decrease = (Button) courseView.findViewById(R.id.decrease);

        courseName.setText(getItem(position).getName());
        absents.setText(String.valueOf(getItem(position).getAbsents()));

        increase.setOnClickListener(
                new ClicksHandler(this, db, position)
        );

        decrease.setOnClickListener(
                new ClicksHandler(this, db, position)
        );
        courseName.setOnLongClickListener(
                new ClicksHandler(this, db, position)
        );

        return courseView;
    }

}
