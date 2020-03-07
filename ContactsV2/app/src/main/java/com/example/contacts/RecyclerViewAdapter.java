package com.example.contacts;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Contact> contacts;
    private Dialog dialog;

    public RecyclerViewAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_contact);

        viewHolder.itemContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialogName = dialog.findViewById(R.id.dialogNameId);
                TextView dialogNumber = dialog.findViewById(R.id.dialogNumberId);
                ImageView dialogImage = dialog.findViewById(R.id.dialogImageId);
                dialogName.setText(contacts.get(viewHolder.getAdapterPosition()).getName());
                dialogNumber.setText(contacts.get(viewHolder.getAdapterPosition()).getNumber());
                dialogImage.setImageResource(contacts.get(viewHolder.getAdapterPosition()).getImage());
                dialog.show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.number.setText(contacts.get(position).getNumber());
        holder.image.setImageResource(contacts.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemContact;
        private TextView name;
        private TextView number;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemContact = itemView.findViewById(R.id.contactItemId);
            name = itemView.findViewById(R.id.contactName);
            number = itemView.findViewById(R.id.contactNumber);
            image = itemView.findViewById(R.id.contactImageView);
        }
    }


}
