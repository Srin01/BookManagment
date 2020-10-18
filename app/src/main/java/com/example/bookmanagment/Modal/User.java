package com.example.bookmanagment.Modal;

public class User
{
    private int id;
    private String userName;
    private String userPassword;
    private String question;
    private String answer;

    public User(String userName, String userPassword)
    {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId() {
        return id;
    }

    public User(int id, String userName, String userPassword, String question, String answer)
    {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.question = question;
        this.answer = answer;
    }

    public User( String userName, String userPassword, String question, String answer)
    {
        this.userName = userName;
        this.userPassword = userPassword;
        this.question = question;
        this.answer = answer;
    }

    public User()
    {
    }

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
}
