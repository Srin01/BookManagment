package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Expert.InstructionExpert;
import com.example.bookmanagment.R;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.MyViewHolder>
{
    InstructionExpert instructionExpert;
    Context context;

    public InstructionAdapter(Context context, InstructionExpert instructionExpert)
    {
        this.instructionExpert = instructionExpert;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.instruction_view, parent, false);
        return new InstructionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        View view = holder.itemView;
        TextView instruction = view.findViewById(R.id.instruction_text);
        ImageView imageView = view.findViewById(R.id.instruction_image);
        instruction.setText(instructionExpert.getInstruction(position));
        imageView.setImageResource(instructionExpert.getImageId(position));
    }

    @Override
    public int getItemCount() {
       return instructionExpert.getTotalInstructions();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
