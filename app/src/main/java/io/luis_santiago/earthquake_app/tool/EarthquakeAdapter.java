package io.luis_santiago.earthquake_app.tool;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import io.luis_santiago.earthquake_app.R;

import static io.luis_santiago.earthquake_app.R.id.city;
import static io.luis_santiago.earthquake_app.R.id.date;

/**
 * Created by legendarywicho on 4/9/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private String part1;
    private String part2;

    public EarthquakeAdapter(Activity context, ArrayList <Earthquake> earthquake){

        super(context, 0, earthquake);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_activity,parent,false);
        }

        Earthquake earthquake = getItem(position);

        // Getting the magnitude position
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitud);

        Double mag = earthquake.getMagnitude_earthquake();
        String mag2 = formatMagnitude(mag);

        magnitude.setText(mag2);

        // Finding the city position
        //TODO: Split the city String into two
        String citySplit = earthquake.getLocation_earthqueake();


        if(citySplit.contains("of")){
            String [] citySplitIntoTwo= citySplit.split("of");
             part1 = citySplitIntoTwo[0]; // The north way of
             part2 = citySplitIntoTwo[1]; // San Francisco, CA
        }

        else if (citySplit.contains("Near the")){
            String [] citySplitIntoTwo= citySplit.split("Near the");
            part1 = citySplitIntoTwo[0]; // The north way of
            part2 = citySplitIntoTwo[1]; // San Francisco, CA
        }

        TextView city = (TextView) listItemView.findViewById(R.id.city);
        city.setText(part1);

        TextView location_city =  (TextView)listItemView.findViewById(R.id.city_location);
        location_city.setText(part2);

        Log.e("Mensaje the City",part1+ "Este es el segundo String "+part2);
        // Date position
        // TODO: Create a Date object and put the correct format
        Date dateObject = new Date(earthquake.getTime_of_earthquake());
        String formatDate = formatDate(dateObject);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(formatDate);

        // Time position
        TextView time = (TextView) listItemView.findViewById(R.id.time);
        String timeFormat = formatTime(dateObject);
        time.setText(timeFormat);

        return listItemView;
    }


    public String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        String carl = dateFormat.format(dateObject);
        return carl;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double mag){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String output = decimalFormat.format(mag);
        return output;
    }
}
