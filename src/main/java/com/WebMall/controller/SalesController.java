package com.WebMall.controller;

import com.WebMall.model.User;
import com.WebMall.model.enums.DeliveryType;
import com.WebMall.model.enums.PaymentType;
import com.WebMall.service.AccessDeniedException;
import com.WebMall.service.ordersServices.OrderService;
import com.WebMall.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/checkout")
public class SalesController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping
    public String checkoutPage(Model model){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) throw new AccessDeniedException();

        int resultSum = loggedUser.getCart().stream()
                .mapToInt(el -> el.getGood().getPrice())
                .sum();

        model.addAttribute("cart", loggedUser.getCart());
        model.addAttribute("sum", resultSum);
        return "checkout";
    }

    @RequestMapping(value = "/proceed", method = RequestMethod.POST)
    public String proceedCheckout(@RequestBody MultiValueMap<String, String> checkoutParams, Model model){
        //Check authorization
        if (!userService.checkAuthUser())
            throw new AccessDeniedException();

        String selectedDeliveryType = checkoutParams.getFirst("delivery-type");
        String selectedPaymentType = checkoutParams.getFirst("payment-type");

        if (selectedDeliveryType.equals("") || selectedPaymentType.equals("")){
            model.addAttribute("error", "Вы не выбрали тип доставки или оплаты");
            return "redirect:/checkout";
        }

        DeliveryType deliveryType = DeliveryType.values()[Integer.parseInt(selectedDeliveryType)];
        PaymentType paymentType = PaymentType.values()[Integer.parseInt(selectedPaymentType)];

        //Create new order with cart removal
        orderService.createNewOrder(deliveryType, paymentType, true);

        return "/";
    }
}
