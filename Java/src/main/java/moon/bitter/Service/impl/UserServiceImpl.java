package moon.bitter.Service.impl;

import cn.hutool.json.JSONUtil;
import moon.bitter.Config.SpringbootKafkaConfig;
import moon.bitter.Mapper.UserMapper;
import moon.bitter.Pojo.User;
import moon.bitter.Service.UserService;
import moon.bitter.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Result login(User user) {
        User u = userMapper.GetByName(user.getName());
        if( u == null) {
            return Result.error("用户不存在!") ;
        }
        if (u.getName().equals(user.getName()) && u.getPass().equals(user.getPass())) {
            try {
                String obj2String = JSONUtil.toJsonStr(user);
                kafkaTemplate.send(SpringbootKafkaConfig.TOPIC_LOGIN_LOG, obj2String);
                return Result.success();
            }catch(Exception e){
                return Result.error("登录失败") ;
            }
        }
        return Result.error("用户账号或密码错误");

    }

    @Override
    public Result register(User user) {
        User u = userMapper.GetByName(user.getName()) ;
        if( u == null ){
            try {
                String obj2String = JSONUtil.toJsonStr(user);
                kafkaTemplate.send(SpringbootKafkaConfig.TOPIC_REGISTER_LOG, obj2String);
                userMapper.register(user.getName() , user.getPass()) ;
                return Result.success();
            }catch (Exception e){
                return Result.error("注册失败") ;
            }
        }
        return Result.error("用户已存在");
    }
}
