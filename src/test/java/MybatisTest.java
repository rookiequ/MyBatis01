import com.user.dao.AccountDAO;
import com.user.dao.RoleDAO;
import com.user.dao.UserDAO;
import com.user.domain.Account;
import com.user.domain.QueryVO;
import com.user.domain.Role;
import com.user.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author zzq
 */
public class MybatisTest {

//    public static void main(String[] args) throws IOException {
//        //1.读取核心配置文件
//        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        //2.创建SqlSession工厂
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
//        //3.使用工厂创建session对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //4.用session创建dao接口的代理对象
//        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
//        //5.使用代理对象执行方法
//        List<User> userList= userDAO.findAll();
//        for (User user : userList) {
//            System.out.println(user.toString());
//        }
//        //6.释放资源
//        sqlSession.close();
//        inputStream.close();
//    }

    private AccountDAO accountDAO;
    private RoleDAO roleDao;

    @Before
    public void init() throws IOException {
        //1.读取核心配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSession工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //3.使用工厂创建session对象
        sqlSession = sqlSessionFactory.openSession();
        //4.用session创建dao接口的代理对象
        userDAO = sqlSession.getMapper(UserDAO.class);
        accountDAO = sqlSession.getMapper(AccountDAO.class);
        roleDao = sqlSession.getMapper(RoleDAO.class);
        //5.使用代理对象执行方法

    }

    @After
    public void destroy() throws IOException {
        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }


    @Test
    public void findAllTest(){
        //5.使用代理对象执行方法
        List<User> userList= userDAO.findAll();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setUsername("周三钦");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("盐城");
        userDAO.saveUser(user);
        sqlSession.commit();
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(50);
        user.setUsername("渠立庆");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("徐州");
        userDAO.updateUser(user);
        sqlSession.commit();
    }


    @Test
    public void deleteUserTest(){
        Integer id = 49;
        userDAO.deleteUser(id);
        sqlSession.commit();
    }

    @Test
    public void findUserByIdTest(){
        User user = userDAO.findUserById(45);
        System.out.println(user.toString());
    }

    @Test
    public void findUserByNameTest(){
        List<User> userList = userDAO.findUserByName("%三%");
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void findTotal(){
        int total = userDAO.findTotal();
        System.out.println("总共有"+total+"条记录");
    }

    @Test
    public void lastInsertId(){
        User user = new User();
        user.setUsername("刘邦昱");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("四川");
        System.out.println("执行insert操作之前的user:" + user);
        userDAO.saveUser(user);
        sqlSession.commit();
        System.out.println("执行insert操作之后的user:" + user);

    }

    @Test
    public void findByVOTest(){
        QueryVO queryVO = new QueryVO();
        User user = new User();
        user.setUsername("%三%");
        queryVO.setUser(user);
//        System.out.println(queryVO);
        List<User> userList = userDAO.findByVO(queryVO);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }


    @Test
    public void findByUser(){
        User user = new User();
        user.setUsername("%三%");
        user.setSex("男");
        List<User> userList = userDAO.findByUser(user);
        for (User user1 : userList) {
            System.out.println(user1);
        }

    }

    @Test
    public void findAccountWithUserTest(){
        List<Account> accountList = accountDAO.findAccountWithUser();
        for (Account account : accountList) {
            System.out.println(account.toString());
        }
    }


    @Test
    public void findAllUserWithAccountTest(){
        List<User> userList = userDAO.findAllUserWithAccount();
        for (User user : userList) {
            System.out.println(user);
        }
    }


    @Test
    public void findRoleWithUserTest(){
        List<Role> roleList = roleDao.findRoleWithUser();
        for (Role role : roleList) {
            System.out.println(role);
        }
    }

    @Test
    public void cacheTest(){

        //这里研究的mybatis的缓存问题
        User user1 = userDAO.findUserById(50);
        //清空缓存
        sqlSession.clearCache();
        User user2 = userDAO.findUserById(50);

        //这里比较的是user1和user2的地址值
        System.out.println(user1==user2);
    }



}
