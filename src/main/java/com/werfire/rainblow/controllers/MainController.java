package com.werfire.rainblow.controllers;

import com.werfire.rainblow.models.Equipment;
import com.werfire.rainblow.models.Order;
import com.werfire.rainblow.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    private Order cart = null;

    @RequestMapping({"/", "/index", "equipment_store"})
    public String index(Model model) {
        logger.debug("Welcome to RainBlow!");
        List<Equipment> equipmentList = DatabaseUtil.getEquipments();
        for (Equipment e: equipmentList) {

        }

        model.addAttribute("equipments", DatabaseUtil.getEquipments());
        return "equipment_store";
    }

    @RequestMapping({"/login"})
    public String login(Model model) {
        return "login";
    }

    @RequestMapping({"/register"})
    public String register(Model model) {
        return "register";
    }

    @RequestMapping({"/shopping_cart"})
    public String shoppingCart(Model model) {
        return "shopping_cart";
    }

    @RequestMapping({"/site_reservation"})
    public String siteReservation(Model model) {
        return "site_reservation";
    }

    @RequestMapping({"/errors"})
    public String errorPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("errCode", getErrorCode(httpRequest));
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
