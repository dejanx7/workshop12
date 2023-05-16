package sg.nus.iss.visa.ssf.workshop12.controller;

import java.util.ArrayList;
import java.util.List;

import org.attoparser.discard.DiscardMarkupHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sg.nus.iss.visa.ssf.workshop12.model.Image;
import sg.nus.iss.visa.ssf.workshop12.service.RandomNumService;

@Controller
// @RequestMapping(path = "/api")
public class RandomNumberGenController {


    @Autowired
    RandomNumService service;

    @GetMapping("/home")
    public String landingPage() {
        return "home.html";

    }

    @GetMapping("/get")
    public String generateRanNumbers(Model model, HttpServletRequest request) {

        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("input no is: " + number);

        if(number <1 || number >30 ){
            String errorMessage = "Invalid number: " + number;
            model.addAttribute("errorMessage", errorMessage);
            return "home";
        }

        List<Integer> randomNumbers = service.generateRanNumbers(number);

        List<Image> imageList = new ArrayList<Image>();

        for(int randomNumber : randomNumbers){
            imageList.add(new Image(Integer.toString(randomNumber), "/images/" +Integer.toString(randomNumber) +".png"));
        }

        model.addAttribute(imageList);             

        return "display";

        

    }

}
