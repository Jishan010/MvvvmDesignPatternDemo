package com.mobility.mvvvmdesignpatterndemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobility.mvvvmdesignpatterndemo.R;

import static com.mobility.mvvvmdesignpatterndemo.Utility.DESCRIPTION;
import static com.mobility.mvvvmdesignpatterndemo.Utility.ID;
import static com.mobility.mvvvmdesignpatterndemo.Utility.TITLE;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descEditText);
        if (getIntent().hasExtra(ID)) {
            toolbar.setTitle(getResources().getString(R.string.edit_note));
            titleEditText.setText(getIntent().getStringExtra(TITLE));
            descEditText.setText(getIntent().getStringExtra(DESCRIPTION));
            noteId = getIntent().getIntExtra(ID, -1);
        } else {
            toolbar.setTitle(getResources().getString(R.string.add_note));
        }


        Button saveNoteButton = findViewById(R.id.saveNoteButton);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                intent.putExtra(TITLE, titleEditText.getText().toString());
                intent.putExtra(DESCRIPTION, descEditText.getText().toString());
                if (noteId != -1) {
                    intent.putExtra(ID, noteId);
                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
