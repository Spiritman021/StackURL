package com.tworoot2.stackurl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<myModel> notes;
    static myAdapter adapter;
    RecyclerView recV;
    Button add, share;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        NotesDataBase db = new NotesDataBase(MainActivity.this);
        int imageResource = R.drawable.link;
        int imageResource1 = R.drawable.gmail;
        notes = db.getNotes();


        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        share = (Button) findViewById(R.id.share);
        add = (Button) findViewById(R.id.add);
        recV = (RecyclerView) findViewById(R.id.recView);
        recV.setHasFixedSize(true);
        recV.setLayoutManager(layoutManager);

        adapter = new myAdapter(MainActivity.this, notes);
        adapter.notifyDataSetChanged();
        recV.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Paste your link", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, AddLink.class);
                i.putExtra("edit", "false");
                startActivity(i);


            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String allLinks = "";

                for (myModel model : notes
                ) {
                    allLinks = allLinks + "->> " + model.getLink() + "\n \n";
                }

                Intent intent = new Intent(MainActivity.this, AllLinks.class);
                intent.putExtra("allLinks", allLinks);
                startActivity(intent);

            }
        });

        CheckForUpdate();
    }


    private void CheckForUpdate() {
        try {
            String version = this.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("app").child("stackURL");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String versionName = (String) dataSnapshot.getValue();

                    if (!versionName.equals(version)) {


                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.update_ui);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.setCancelable(false);
                        dialog.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent1);
                            }
                        });
                        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "Please update your app as soon as possible, you are loosing lots of thing without this update", Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}