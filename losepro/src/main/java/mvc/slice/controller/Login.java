package mvc.slice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by k on 2018/4/9.
 */
@Controller
public class Login {
    @RequestMapping("/loginpage")
    public String loginPage(){
        return "/loginpage";
    }

}
