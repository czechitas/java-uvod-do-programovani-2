package cz.czechitas.milenakm;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import net.sevecek.util.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/prevod", consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String provedPrevod(@RequestBody String mileText) {
        DoubleFormatter prevodnikNaCislo = new DoubleFormatter("0.##");

        double mile = prevodnikNaCislo.parse(mileText);
        double km = mile * 1.609344;
        String kmText = prevodnikNaCislo.print(km);

        return kmText;
    }

}
