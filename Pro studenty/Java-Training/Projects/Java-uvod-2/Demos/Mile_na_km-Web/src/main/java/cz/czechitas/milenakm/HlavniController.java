package cz.czechitas.milenakm;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import net.sevecek.util.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/prevod", consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String provedPrevod(@RequestBody String mileText) {
        DoubleFormatter prevodnikCisel;
        prevodnikCisel = new DoubleFormatter("0.##");

        Double mile;
        double km;
        String kmText;
        mile = prevodnikCisel.parse(mileText);
        km = mile * 1.609344;
        kmText = prevodnikCisel.print(km);

        return kmText;
    }

}
