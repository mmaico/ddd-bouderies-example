package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.User;

public class UserSaveMessage {


    private User user;

    public UserSaveMessage(User user) {
        this.user = user;
    }

    public static UserSaveMessage create(User userChange) {
          return new UserSaveMessage(userChange);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
