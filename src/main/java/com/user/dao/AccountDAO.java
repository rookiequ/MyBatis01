package com.user.dao;

import com.user.domain.Account;
import com.user.domain.User;

import java.util.List;

public interface AccountDAO {

    /**
     * 获取所有的account信息
     * @param
     * @return
     */
    List<Account> findAccountWithUser();
}
