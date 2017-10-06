package com.adibta.android_sqllite.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adibta.android_sqllite.AddActivity;
import com.adibta.android_sqllite.R;
import com.adibta.android_sqllite.model.Note_Item;

import java.util.ArrayList;

/**
 * Created by Nsikak on 10/5/17.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private ArrayList<Note_Item> noteList;
    Context c;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.note_title);
            description = (TextView) view.findViewById(R.id.note_desciption);

        }
    }


    public NoteListAdapter( Context c,ArrayList<Note_Item> noteList) {
        this.noteList = noteList;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Note_Item item = noteList.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("note_title", item.getTitle() );
                intent.putExtra("note_desc", item.getDescription());
                intent.putExtra("update",true);
                intent.putExtra("id", item.getId());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public void clear(){
        noteList.clear();
        notifyDataSetChanged();
    }
}