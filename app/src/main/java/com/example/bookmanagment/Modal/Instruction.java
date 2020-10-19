package com.example.bookmanagment.Modal;

import java.util.ArrayList;

public class Instruction
{
    private int imageId;
    private String instruction;

    public Instruction(int imageId, String instruction)
    {
        this.imageId = imageId;
        this.instruction = instruction;
    }

    public int getImageId()
    {
        return imageId;
    }

    public String getInstruction()
    {
        return instruction;
    }
}
