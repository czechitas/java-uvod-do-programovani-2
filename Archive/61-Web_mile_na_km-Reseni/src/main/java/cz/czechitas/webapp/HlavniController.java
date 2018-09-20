package cz.czechitas.webapp;

import java.text.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import net.sevecek.util.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/proved", consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String provedPrevod(@RequestBody String mileText) {
        double mile;
        DoubleFormatter prevodnikCisel;
        double kilometry;
        String kilometryText;

        prevodnikCisel = new DoubleFormatter("0.##");
        mile = prevodnikCisel.parse(mileText);
        kilometry = mile * 1.609;
        kilometryText = prevodnikCisel.print(kilometry);

        return kilometryText;
    }

}
