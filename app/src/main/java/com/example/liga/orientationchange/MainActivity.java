package com.example.liga.orientationchange;

import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    private final String TAG = "ActivityLifeCycle";
    private String text = "";
    ArrayList<String> textStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called");
        textStrings.add("TEST");
        textStrings.add("TEST 2");

        //get a reference for our save button from xml
        Button inputButton = (Button) findViewById(R.id.inputButton);

        //get a reference to our editText from xml
        final EditText editText = (EditText) findViewById(R.id.inputText);
        final TextView textView = (TextView) findViewById(R.id.outputText);
        final ListView listView = (ListView) findViewById(R.id.list);

        if(savedInstanceState!= null){
            Log.d("DEBUG", savedInstanceState.getString("savedText"));
            String str = savedInstanceState.getString("savedText");
            if(str != null){
                text = str;
            }

            ArrayList list = savedInstanceState.getStringArrayList("savedList");
            if (list!=null){
                textStrings = list;
            }
        }

        textView.setText(text);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, textStrings);

        //ListView listView = getListView();
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);
        //setListAdapter(adapter);

        //add a click listener to our save button
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                text = editText.getText().toString();
                textStrings.add(text);

                Log.d("DEBUG ON Click", text);
                Log.d("DEBUG ON Click VIEW", textView.getText().toString());

                //set the new value in the text field
                textView.setText(text);
                listView.setAdapter(adapter);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState");

        outState.putString("savedText", text);
        outState.putStringArrayList("savedList", textStrings);
    }

    protected void onRestoreInstanceState(Bundle savedState){
        super.onRestoreInstanceState(savedState);
        Log.i(TAG, "onRestoreInstanceState");

        TextView savedText = (TextView) findViewById(R.id.outputText);

        this.text = savedState.getString("savedText");
        this.textStrings = savedState.getStringArrayList("savedList");

    }
}
