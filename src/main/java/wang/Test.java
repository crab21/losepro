package wang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by k on 11/1/17.
 */
@Controller
public class Test {
    @RequestMapping("hehe/ok")
    public String index(){
        return "hehe/ok";
    }
}
