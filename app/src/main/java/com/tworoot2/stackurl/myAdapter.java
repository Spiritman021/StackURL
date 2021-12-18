package com.tworoot2.stackurl;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<myModel> notes;
    AlertDialog.Builder builder;

    public myAdapter(Context context, List<myModel> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_edittext, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String link = notes.get(position).getLink();
        int image = notes.get(position).getImage();
        holder.link.setText(link);


        int link1 = R.drawable.link;
        int gmail1 = R.drawable.gmail;
        int linkedin1 = R.drawable.linkedin;
        int git1 = R.drawable.git;
        int twitter1 = R.drawable.twitter;
        int stack1 = R.drawable.stack;
        int docs1 = R.drawable.docs;
        int sheets1 = R.drawable.sheets;
        int forms1 = R.drawable.forms;
        int classroom1 = R.drawable.classroom;
        int drive1 = R.drawable.drive;
        int dev1 = R.drawable.dev;
        int photos1 = R.drawable.photos;
        int instagram1 = R.drawable.instagram;
        int meet1 = R.drawable.meet;
        int youtube1 = R.drawable.youtube;
        int playstore1 = R.drawable.playstore;
        int zoom1 = R.drawable.zoom;
        int facebook1 = R.drawable.facebook;
        int teams1 = R.drawable.teams;
        int whatsapp1 = R.drawable.whatsapp;
        int firebase1 = R.drawable.firebase;
        int blogger1 = R.drawable.blogger;
        int medium1 = R.drawable.medium;



        if (notes.get(position).getLink().toString().contains("@") &
                        notes.get(position).getLink().toString().contains(".")){
            holder.image.setImageResource(gmail1);
        }
        else if (notes.get(position).getLink().toString().contains("linkedin")){
            holder.image.setImageResource(linkedin1);
        }
        else if (notes.get(position).getLink().toString().contains("stackoverflow")){
            holder.image.setImageResource(stack1);
        }
        else if (notes.get(position).getLink().toString().contains("dev")){
            holder.image.setImageResource(dev1);
        }
        else if (notes.get(position).getLink().toString().contains("zoom")){
            holder.image.setImageResource(zoom1);
        }
        else if (notes.get(position).getLink().toString().contains("teams") & notes.get(position).getLink().toString().contains("microsoft")){
            holder.image.setImageResource(teams1);
        }
        else if (notes.get(position).getLink().toString().contains("facebook") || notes.get(position).getLink().toString().contains("fb")){
            holder.image.setImageResource(facebook1);
        }
        else if (notes.get(position).getLink().toString().contains("play.google.com")){
            holder.image.setImageResource(playstore1);
        }
        else if (notes.get(position).getLink().toString().contains("whatsapp")){
            holder.image.setImageResource(whatsapp1);
        }
        else if (notes.get(position).getLink().toString().contains("medium")){
            holder.image.setImageResource(medium1);
        }
        else if (notes.get(position).getLink().toString().contains("drive")){
            holder.image.setImageResource(drive1);
        }
        else if (notes.get(position).getLink().toString().contains("docs")){
            holder.image.setImageResource(docs1);
        }
        else if (notes.get(position).getLink().toString().contains("youtube") ||
                notes.get(position).getLink().toString().contains("youtu.be")){
            holder.image.setImageResource(youtube1);
        }

        else if (notes.get(position).getLink().toString().contains("sheet")){
            holder.image.setImageResource(sheets1);
        }else if (notes.get(position).getLink().toString().contains("instagram")){
            holder.image.setImageResource(instagram1);
        }
        else if (notes.get(position).getLink().toString().contains("classroom")){
            holder.image.setImageResource(classroom1);
        }
        else if (notes.get(position).getLink().toString().contains("form")){
            holder.image.setImageResource(forms1);
        }
        else if (notes.get(position).getLink().toString().contains("meet")){
            holder.image.setImageResource(meet1);
        }
        else if (notes.get(position).getLink().toString().contains("photo")){
            holder.image.setImageResource(photos1);
        }
        else if (notes.get(position).getLink().toString().contains("git")){
            holder.image.setImageResource(git1);
        }
        else if (notes.get(position).getLink().toString().contains("twitter")){
            holder.image.setImageResource(twitter1);
        }
        else if (notes.get(position).getLink().toString().contains("firebase")){
            holder.image.setImageResource(firebase1);
        }
        else if (notes.get(position).getLink().toString().contains("blogspot.com") || notes.get(position).getLink().toString().contains("blogger")){
            holder.image.setImageResource(blogger1);
        }
        else {
            holder.image.setImageResource(link1);
        }

        holder.browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.link.getText().toString().contains("@") &
                        holder.link.getText().toString().contains(".")){
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" +
                            holder.link.getText().toString().trim()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "Text");
                    context.startActivity(intent);
                }
                else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(holder.link.getText().toString().trim()));
                    context.startActivity(i);
                }
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setIcon(context.getResources().getDrawable(R.drawable.warning));
                builder.setMessage("Are you sure you want to permanently remove this link?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // code for delete the link
                                int position1 = holder.getAdapterPosition();
                                myModel note = notes.get(position1);
                                long ID = note.getID();
                                NotesDataBase db = new NotesDataBase(v.getContext());
                                db.deleteLink(ID);
                                notes.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                                Toast.makeText(context,"Deleted successfully",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();




            }
        });

        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String linkCopied = holder.link.getText().toString();

                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied",linkCopied );
                clipboard.setPrimaryClip(clip);

                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();

            }
        });

        int lastPosition = -1;
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.down_to_up
                        : R.anim.up_to_down);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String linkCopied = holder.link.getText().toString();

                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied",linkCopied );
                clipboard.setPrimaryClip(clip);

                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position1 = holder.getAdapterPosition();
                myModel note = notes.get(position1);
                String link = note.getLink();
                long ID = note.getID();

                NotesDataBase db = new NotesDataBase(v.getContext());


                Intent i = new Intent(v.getContext(),AddLink.class);
                i.putExtra("edit","true");
                i.putExtra("RLink",link);
                i.putExtra("ID",ID);
                v.getContext().startActivity(i);

                Log.d("idfor","ID = " + ID);



                Toast.makeText(v.getContext(), "Update your link", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }
}