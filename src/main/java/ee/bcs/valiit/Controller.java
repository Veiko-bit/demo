package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Lesson1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("N/hello-world/{nameInUrl}")
    public String demo(@PathVariable("nameInUrl") String name,

        @RequestParam("action") String action)
    {
        return action + " " + name;
    }
}
