package com.user.domain;

import java.io.Serializable;

/**
 * @author zzq
 */
public class QueryVO implements Serializable {

    /** 实体类中包含另一个实体类 user.username,user.sex*/
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QueryVO{" +
                "user=" + user +
                '}';
    }
}
