package moon.bitter.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String id ;
    private String name ;
    private String pass ;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
