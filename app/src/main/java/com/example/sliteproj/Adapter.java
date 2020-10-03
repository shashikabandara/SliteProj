package com.example.sliteproj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    private Context context;
    private ArrayList<Model> arrayList;

    DatabaseHelper DatabaseHelper;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        DatabaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        // get for views
        Model Model = arrayList.get(position);
        final String id = Model.getId();
        final String image = Model.getImage();
        final String name = Model.getName();
        final String date = Model.getDate();
        final String time = Model.getTime();
        final String type = Model.getType();
        final String notes = Model.getNotes();
        final String Cnumber = Model.getCnumber();
        final String Cvc = Model.getCvc();
        final String Edate = Model.getEdate();
        final String amount = Model.getAmount();
        final String paid = Model.getPaid();
        final String addTimeStamp = Model.getAddTimeStamp();
        final String updateTimeStamp = Model.getUpdateTimeStamp();

        // set data
        holder.profileIv.setImageURI(Uri.parse(image));
        holder.name.setText(name);
        holder.date.setText(date);
        holder.time.setText(time);
        holder.type.setText(type);
        holder.notes.setText(notes);
        holder.cnumber.setText(Cnumber);
        holder.cvc.setText(Cvc);
        holder.edate.setText(Edate);
        holder.amount.setText(amount);
        holder.paid.setText(paid);




        // handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // handle when click on more button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+name,
                        ""+date,
                        ""+time,
                        ""+type,
                        ""+notes,
                      ""+Cnumber,
                        ""+Cvc,
                        ""+Edate,
                        ""+amount,
                        ""+paid,
                        ""+image,
                        ""+addTimeStamp,
                        ""+updateTimeStamp


                );
            }
        });

        holder.deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteDialog(""+id);
                return false;
            }
        });

    }

    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_action_delete);
        builder.setCancelable(false);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper.deleteInfo(id);
                ((MainActivity)context).onResume();
                Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    private void editDialog(String position, final String id, final String name, final String date, final String time,final String type , final String notes ,final String cnumber,final String cvc,final String edate,final String amount,final String paid,final String image, final String addTimeStamp, final String updateTimeStamp) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_action_edit);
        builder.setCancelable(false);
        builder.setTitle("Edit");
        builder.setMessage("Are you want to edit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, EditRecordActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                intent.putExtra("DATE", date);
                intent.putExtra("TIME", time);
                intent.putExtra("TYPE",type);
                intent.putExtra("NOTES",notes);
                intent.putExtra("PAID",paid);
                intent.putExtra("CNUMBER",cnumber);
                intent.putExtra("CVC",cvc);
                intent.putExtra("EDATE",edate);
                intent.putExtra("AMOUNT",amount);
                intent.putExtra("IMAGE", image);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
                intent.putExtra("editMode", true);
                context.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView profileIv;
        TextView name, date, time, type, notes,cnumber,cvc,edate,amount,paid;
        ImageButton editButton,deleteButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            // initialize views
            profileIv = itemView.findViewById(R.id.profileIv);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            type = itemView.findViewById(R.id.type);
            notes = itemView.findViewById(R.id.notes);
            cnumber = itemView.findViewById(R.id.Cnumber);
            cvc = itemView.findViewById(R.id.cvc);
            edate = itemView.findViewById(R.id.Edate);
            amount = itemView.findViewById(R.id.amount);
            paid = itemView.findViewById(R.id.paid);
            // find id of more button
            editButton = itemView.findViewById(R.id.editBtn);
            deleteButton = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
