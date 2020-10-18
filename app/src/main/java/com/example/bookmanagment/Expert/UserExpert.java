package com.example.bookmanagment.Expert;

import android.util.Log;
import com.example.bookmanagment.Driver.UserDataBaseDriver;
import com.example.bookmanagment.Modal.User;

import java.util.ArrayList;

import static com.example.bookmanagment.MainActivity.TAG;

public class UserExpert
{
    UserDataBaseDriver userDataBaseDriver;
    ArrayList<User> userList ;

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

    public String getUserName(int userPosition)
    {
        return userList.get(userPosition).getUserName();
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
    public boolean validatePassword(String usernameGiven, String passwordGiven)
    {
        User user = getUserOfSpecificName(usernameGiven);
        if(user != null) {
        String password = user.getUserPassword();
            Log.d(TAG, "validatePassword: got pwd " + password);
            return password.equals(passwordGiven);
        }
        else {
            Log.d(TAG, "validatePassword: got pwd null" );
            return false;
        }
    }

    public boolean ifUserExistForSpecifiRoom(String usernameValue, int roomId)
    {
        User user = userDataBaseDriver.getUserFromSpecifRoom(roomId);
        if(user != null) {
            return user.getUserName().equals(usernameValue);
        }
        return false;
    }
}
