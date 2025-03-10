package com.example.apptest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample data for the ListView
        List<Map<String, String>> data = new ArrayList<>();
        data.add(createVerbPair("Commencer", "To start"));
        data.add(createVerbPair("Entrer", "To enter"));
        data.add(createVerbPair("Manger", "To eat"));
        data.add(createVerbPair("Parler", "To speak"));
        data.add(createVerbPair("Voler", "To fly"));

        // Find the ListView in the layout
        ListView listView = findViewById(R.id.listView);

        // Create a custom adapter to bind the data to the ListView
        VerbAdapter adapter = new VerbAdapter(this, data);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }

    private Map<String, String> createVerbPair(String french, String english) {
        Map<String, String> pair = new HashMap<>();
        pair.put("french", french);
        pair.put("english", english);
        return pair;
    }

    // Custom adapter to display French verbs and their English translations
    private static class VerbAdapter extends ArrayAdapter<Map<String, String>> {

        public VerbAdapter(MainActivity context, List<Map<String, String>> data) {
            super(context, android.R.layout.simple_list_item_2, data);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            // Get the data item for this position
            Map<String, String> verbPair = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = View.inflate(getContext(), android.R.layout.simple_list_item_2, null);
            }

            // Lookup view for data population
            TextView frenchTextView = convertView.findViewById(android.R.id.text1);
            TextView englishTextView = convertView.findViewById(android.R.id.text2);

            // Populate the data into the template view using the data object
            if (verbPair != null) {
                frenchTextView.setText(verbPair.get("french"));
                englishTextView.setText(verbPair.get("english"));
            }

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
