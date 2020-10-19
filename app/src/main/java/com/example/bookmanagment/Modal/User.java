package com.example.bookmanagment.Modal;

public class User
{
    private int id;
    private String userName;
    private String userPassword;
    private String question;
    private String answer;
    private int roomId;

    public User(int id, String userName, String userPassword, String question, String answer, int roomId)
    {
        this.userName = userName;
        this.userPassword = userPassword;
        this.question = question;
        this.answer = answer;
        this.roomId = roomId;
    }

    public User( String userName, String userPassword, String question, String answer, int roomId)
    {
        this.roomId = roomId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.question = question;
        this.answer = answer;
    }

    public int getRoomId() { return roomId; }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }
}
