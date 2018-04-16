package mvc.slice.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by k on 2018/4/9.
 */
@Controller
public class Login {
    @RequestMapping("/loginpage")
    public String loginPage(){
        return "show/loginpage";
    }

    @ModelAttribute
    public AccountData setupAccountData() { // (1)
        return new AccountData();
    }

    @RequestMapping(value = "/login")
    public String login(@Validated AccountData form, BindingResult result) {
        // omitted
        if (result.hasErrors()) {
            // omitted
        }
        return "forward:/authenticate"; // (2)
    }

}
