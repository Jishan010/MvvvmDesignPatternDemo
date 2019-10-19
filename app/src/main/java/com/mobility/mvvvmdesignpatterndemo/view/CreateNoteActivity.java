package com.mobility.mvvvmdesignpatterndemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobility.mvvvmdesignpatterndemo.R;

public class CreateNoteActivity extends AppCompatActivity {


    private EditText titleEditText, descEditText;
    private Button saveNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        titleEditText=findViewById(R.id.titleEditText);
        descEditText=findViewById(R.id.descEditText);

        saveNoteButton=findViewById(R.id.saveNoteButton);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateNoteActivity.this,MainActivity.class);
                intent.putExtra("title",titleEditText.getText().toString());
                intent.putExtra("description",descEditText.getText().toString());
                setResult(1,intent);
                finish();
            }
        });

    }
}
