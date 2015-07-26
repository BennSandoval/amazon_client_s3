package com.bennsandoval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mackbook on 7/25/15.
 */
@Controller
public class RouteController {

    /**
     * TODO
     * AngularJs mappings
     *
     * @return redirects always to index.
     */
    @RequestMapping({
            "/home",
            "/bucket" ,
            "/files"
    })
    public String router() {
        return "forward:/index";
    }

}
