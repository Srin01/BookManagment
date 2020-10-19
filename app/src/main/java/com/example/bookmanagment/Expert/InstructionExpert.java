package com.example.bookmanagment.Expert;

import com.example.bookmanagment.Modal.Instruction;
import com.example.bookmanagment.R;

import java.util.ArrayList;

public class InstructionExpert
{
    private ArrayList<Instruction> instructionArrayList ;
    private int[] imageIds = {R.drawable.instruction1, R.drawable.instruction2, R.drawable.instruction3, R.drawable.instruction4, R.drawable.instruction5 ,R.drawable.instruction6, R.drawable.instruction7, R.drawable.instruction8, R.drawable.instruction9, R.drawable.creaters};
    private String[] instructions = {"Open the app", "Add a new room", "Sign in", "Select the room", "Log in", "Click on add button", "Add a new book","Select the book" ,"Open and read book","From the creators: Enjoy reading"};

    public InstructionExpert()
    {
        instructionArrayList = new ArrayList<>();
        addInstructions();
    }

    private void addInstructions()
    {
        for (int i = 0; i < imageIds.length; i++)
        {
            instructionArrayList.add(new Instruction(imageIds[i], instructions[i]));
        }
    }

    public int getImageId(int position)
    {
        return instructionArrayList.get(position).getImageId();
    }

    public String getInstruction(int position)
    {
        return instructionArrayList.get(position).getInstruction();
    }

    public int getTotalInstructions()
    {
        return instructionArrayList.size();
    }
}
