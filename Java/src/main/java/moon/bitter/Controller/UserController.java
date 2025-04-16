package moon.bitter.Controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import moon.bitter.Config.SpringbootKafkaConfig;
import moon.bitter.Pojo.User;
import moon.bitter.Service.UserService;
import moon.bitter.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/adminLogin")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("adminRegister")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result userLogin(){
        return null ;
    }
}
