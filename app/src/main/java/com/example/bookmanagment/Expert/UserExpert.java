package com.example.bookmanagment.Expert;

import android.util.Log;
import com.example.bookmanagment.Driver.UserDataBaseDriver;
import com.example.bookmanagment.Modal.User;

import java.util.ArrayList;

import static com.example.bookmanagment.Activities.MainActivity.TAG;

public class UserExpert
{
    private UserDataBaseDriver userDataBaseDriver;
    private ArrayList<User> userList ;
    public UserExpert(UserDataBaseDriver userDataBaseDriver)
    {
        this.userDataBaseDriver = userDataBaseDriver;
        userList = userDataBaseDriver.getUserList();
        Log.d(TAG, "UserExpert: users obtained");
    }

    public int getUserId(int userPosition)
    {
        return userList.get(userPosition).getId();
    }

    public String getUserPassword(int userPosition)
    {
        return userList.get(userPosition).getUserPassword();
    }

    public String getUserQuestion(int userPosition)
    {
        return userList.get(userPosition).getQuestion();
    }

    public String getAnswer(int userPosition)
    {
        return userList.get(userPosition).getAnswer();
    }

    public int getTotalUsers()
    {

        return userDataBaseDriver.getUserList().size();
    }

    public void AddNewUser(User user)
    {
        userDataBaseDriver.insertNewUser(user);
        userList.add(user);
        Log.d(TAG, "addNewUser: User " + user.getUserName() +"Added to db");
    }

    public User getUserOfSpecificName(String userName)
    {
        for (int i = 0; i < userList.size(); i++)
        {
            if(userList.get(i).getUserName().equals(userName))
            {
                return userList.get(i);
            }
        }
        return null;
    }

    public boolean ifUserExist(String userName)
    {
        User user = getUserOfSpecificName(userName);
        if(user != null) {
            return user.getUserName().equals(userName);
        }
        else {
            return false;
        }
    }
    public boolean validatePassword(String usernameGiven, String passwordGiven, int roomId)
    {
        User user2 = getUserOfSpecificRoom(roomId);
        if(user2 != null) {
        String password = user2.getUserPassword();
        String userName = user2.getUserName();
            Log.d(TAG, "validatePassword: got pwd " + password);
            return password.equals(passwordGiven);
        }
        else {
            Log.d(TAG, "validatePassword: got pwd null" );
            return false;
        }
    }

    private User getUserOfSpecificRoom(int roomId)
    {
        for (int i = 0; i < userList.size(); i++)
        {
            if(userList.get(i).getRoomId() == roomId)
            {
                return userList.get(i);
        }
    }
        return null;
    }
}
