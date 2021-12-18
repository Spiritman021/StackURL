package com.tworoot2.stackurl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddLink extends AppCompatActivity {

    EditText link;
    TextView textView;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);
        save = (Button) findViewById(R.id.save);
        link = (EditText) findViewById(R.id.link);
        textView = (TextView) findViewById(R.id.textView);
        int imageResource = R.drawable.link;
        int imageResource1 = R.drawable.gmail;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String rLink = i.getStringExtra("RLink");
        String edit = i.getStringExtra("edit");
        long ID = i.getLongExtra("ID",0);
        link.setText(rLink);

        if (edit.equals("true")){
            save.setText("UPDATE");
            textView.setText("Update your link");

            if (link.getText().toString().contains("@") & link.getText().toString().contains(".")){
                setTitle("Mail");
            }
            else if (link.getText().toString().contains("linkedin")){
                setTitle("LinkedIn");
            }
            else if (link.getText().toString().contains("git")){
                setTitle("GitHub");
            }
            else if (link.getText().toString().contains("drive")){
                setTitle("Google Drive");
            }
            else if (link.getText().toString().contains("docs")){
                setTitle("Google Docs");
            }
            else if (link.getText().toString().contains("youtube") ||
                    link.getText().toString().contains("youtu.be")){
                setTitle("Youtube");
            }
            else if (link.getText().toString().contains("stackoverflow")){
                setTitle("Stackoverflow");
            }
            else if (link.getText().toString().contains("classroom")){
                setTitle("Google Classroom");
            }
            else if (link.getText().toString().contains("sheet")){
                setTitle("Google Sheets");
            }
            else if (link.getText().toString().contains("form")){
                setTitle("Google Form");
            }
            else if (link.getText().toString().contains("dev")){
                setTitle("DEV");
            }
            else if (link.getText().toString().contains("photo")){
                setTitle("Google Photo");
            }
            else if (link.getText().toString().contains("facebook") || link.getText().toString().contains("fb")){
                setTitle("Facebook");
            }
            else if (link.getText().toString().contains("instagram")){
                setTitle("Instagram");
            }
            else if (link.getText().toString().contains("twitter")){
                setTitle("Twitter");
            }
            else if (link.getText().toString().contains("meet")){
                setTitle("Google Meet");
            }
            else if (link.getText().toString().contains("zoom")){
                setTitle("ZOOM");
            }
            else if (link.getText().toString().contains("teams") & link.getText().toString().contains("microsoft")){
                setTitle("Microsoft Teams");
            }
            else if (link.getText().toString().contains("whatsapp")){
                setTitle("WhatsApp");
            }
            else if (link.getText().toString().contains("medium")){
                setTitle("Medium");
            }
            else if (link.getText().toString().contains("firebase")){
                setTitle("Firebase");
            }
            else if (link.getText().toString().contains("play.google.com")){
                setTitle("Google Playstore");
            }
            else if (link.getText().toString().contains("blogspot.com") || link.getText().toString().contains("blogger")){
                setTitle("Blogger");
            }
            else{
                setTitle("Stack URL");
            }


            link.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    setTitle(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    myModel note = new myModel(ID,link.getText().toString());
                    NotesDataBase db = new NotesDataBase(AddLink.this);

                    long id = db.updateLink(note);
                    Toast.makeText(AddLink.this, "ID " + id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddLink.this, MainActivity.class);
                    startActivity(i);
                    finishAffinity();



                }
            });
        }
        else {
            textView.setText("Add new link");
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myModel note = new myModel(link.getText().toString());
                    NotesDataBase db = new NotesDataBase(AddLink.this);
                    db.addNote(note);
                    Intent i = new Intent(AddLink.this, MainActivity.class);
                    startActivity(i);
                    finishAffinity();
                }
            });

            link.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (link.getText().toString().contains("@") & link.getText().toString().contains(".")){
                        setTitle("Mail");
                    }
                    else if (link.getText().toString().contains("linkedin")){
                        setTitle("LinkedIn");
                    }
                    else if (link.getText().toString().contains("blogspot.com") || link.getText().toString().contains("blogger")){
                        setTitle("Blogger");
                    }
                    else if (link.getText().toString().contains("meet")){
                        setTitle("Google Meet");
                    }
                    else if (link.getText().toString().contains("classroom")){
                        setTitle("Google Classroom");
                    }
                    else if (link.getText().toString().contains("teams") & link.getText().toString().contains("microsoft")){
                        setTitle("Microsoft Teams");
                    }
                    else if (link.getText().toString().contains("firebase")){
                        setTitle("Firebase");
                    }
                    else if (link.getText().toString().contains("git")){
                        setTitle("GitHub");
                    }
                    else if (link.getText().toString().contains("medium")){
                        setTitle("Medium");
                    }
                    else if (link.getText().toString().contains("whatsapp")){
                        setTitle("WhatsApp");
                    }
                    else if (link.getText().toString().contains("stackoverflow")){
                        setTitle("Stackoverflow");
                    }
                    else if (link.getText().toString().contains("dev")){
                        setTitle("DEV");
                    }
                    else if (link.getText().toString().contains("zoom")){
                        setTitle("ZOOM");
                    }
                    else if (link.getText().toString().contains("youtube") ||
                            link.getText().toString().contains("youtu.be")){
                        setTitle("Youtube");
                    }
                    else if (link.getText().toString().contains("twitter")){
                        setTitle("Twitter");
                    }
                    else if (link.getText().toString().contains("instagram")){
                        setTitle("Instagram");
                    }
                    else if (link.getText().toString().contains("play.google.com")){
                        setTitle("Google Playstore");
                    }
                    else if (link.getText().toString().contains("facebook") || link.getText().toString().contains("fb")){
                        setTitle("Facebook");
                    }
                    else if (link.getText().toString().contains("drive")){
                        setTitle("Google Drive");
                    }
                    else if (link.getText().toString().contains("docs")){
                        setTitle("Google Docs");
                    }
                    else if (link.getText().toString().contains("sheet")){
                        setTitle("Google Sheets");
                    }
                    else if (link.getText().toString().contains("photo")){
                        setTitle("Google Photo");
                    }
                    else if (link.getText().toString().contains("form")){
                        setTitle("Google Form");
                    }
                    else{
                        setTitle("Stack URL");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }

}