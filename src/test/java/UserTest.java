import cn.hdlmx.mybatis.dao.IUser;
import cn.hdlmx.mybatis.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

public class UserTest {
    private static SqlSessionFactory sessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configure.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSessionFactory getSession() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        testInsert();
    }

    public static void testInsert() {
        SqlSession session = sessionFactory.openSession();
        IUser userMaper = session.getMapper(IUser.class);
        System.out.println("Test insert start...");
        User user = new User();
        user.setId(0);
        user.setName("Google");
        user.setDept("Tech");
        user.setWebsite("http://www.google.com");
        user.setPhone("120");
        userMaper.insertUser(user);
        session.commit();
        getUserList();
        System.out.println("After insert");
    }

    public static void getUserList() {
        SqlSession session = sessionFactory.openSession();
        IUser userMaper = session.getMapper(IUser.class);
        System.out.println("Test Get start...");
        printUsers(userMaper.getUserList());
        System.out.println("Test Get finished...");
    }

    private static void printUsers(final List<User> users) {
        int count = 0;

        for (User user : users) {
            System.out.println(MessageFormat.format(
                    "============= User[{0}]=================", ++count));
            System.out.println("User Id: " + user.getId());
            System.out.println("User Name: " + user.getName());
            System.out.println("User Dept: " + user.getDept());
            System.out.println("User Website: " + user.getWebsite());
        }
    }
}
