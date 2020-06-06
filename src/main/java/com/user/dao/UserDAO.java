package com.user.dao;

import com.user.domain.QueryVO;
import com.user.domain.User;

import java.util.List;

public interface UserDAO {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 根据姓名查询用户信息
     * @param username
     * @return
     */
    List<User> findUserByName(String username);

    /**
     * 查询总条数
     * @return
     */
    int findTotal();


    /**
     * 根据vo查user
     * @param vo
     * @return
     */
    List<User> findByVO(QueryVO vo);


    /**
     * 根据姓名和性别查询学生信息
     * @param user
     * @return
     */
    List<User> findByUser(User user);


    /**
     * 获取带有account的所有用户信息
     * @return
     */
    List<User> findAllUserWithAccount();
}
