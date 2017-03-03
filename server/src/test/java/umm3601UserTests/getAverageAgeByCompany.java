package umm3601UserTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.user.User;
import umm3601.user.UserController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Dogxx000 on 3/2/17.
 */
public class getAverageAgeByCompany {
    @Test
    public void getAverageAge() throws IOException {
        UserController userController = new UserController();
        User[] filterUser;
        Gson gson = new Gson();

        String rawUsers = userController.getAverageAgeByCompany();
        filterUser = gson.fromJson(rawUsers, User[].class);
        System.out.print(rawUsers);
    }
}
