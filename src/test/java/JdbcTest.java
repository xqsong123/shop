import com.example.shop.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



public class JdbcTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void connectDatabase(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void DatabaseConnectionTest(){
        User user = jdbcTemplate.queryForObject("select * from t_user where username = ?", new String[]{"zhangsan"},
                (rs, rowNum) -> {
                    User user1 = new User();
                    user1.setUsername(rs.getString("username"));
                    user1.setAge(rs.getInt("age"));
                    user1.setEmail(rs.getString("email"));
                    user1.setRealname(rs.getString("realname"));
                    return user1;
                });
        Assert.assertNotNull("20".equals(user.getAge()));
    }
}
