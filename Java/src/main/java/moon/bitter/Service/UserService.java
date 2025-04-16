package moon.bitter.Service;

import moon.bitter.Pojo.User;
import moon.bitter.Utils.Result;

public interface UserService {
    Result login(User user);

    Result register(User user);
}
