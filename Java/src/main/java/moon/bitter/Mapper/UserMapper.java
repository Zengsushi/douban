package moon.bitter.Mapper;

import moon.bitter.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from admin where name = #{name}")
    public User GetByName(String name);

    @Insert("insert into admin(name , pass) values(#{name} , #{pass})")
    void register(@Param("name")String name, @Param("pass") String pass);
}
