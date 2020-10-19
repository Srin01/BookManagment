package com.example.bookmanagment.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Adapter.InstructionAdapter;
import com.example.bookmanagment.Expert.InstructionExpert;
import com.example.bookmanagment.R;

public class InstructionsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private InstructionAdapter instructionAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_page);
        bindViews();
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(instructionAdapter);
    }

    private void bindViews()
    {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerViewInstructions);
        InstructionExpert instructionExpert = new InstructionExpert();
        instructionAdapter = new InstructionAdapter(this, instructionExpert);
    }
}
