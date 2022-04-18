package com.accenture.lkm.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.PizzaOrderBean;


import com.accenture.lkm.service.PizzaService;

@Controller
public class PizzaOrderController {

	@Autowired
	private PizzaService pizzaService;

	@RequestMapping(value = "/loadSavePizza", method = RequestMethod.GET)
	public ModelAndView loadSavePizza() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("PizzaOrder");
		modelAndView.addObject("pizzaObj", new PizzaOrderBean());
		return modelAndView;
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public ModelAndView addPizzaOrderDetails(@Valid @ModelAttribute("pizzaObj") PizzaOrderBean pizzaOrderBean,
			BindingResult bindingResult) throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("PizzaOrder");
		} else {
			PizzaOrderBean pizzaOrderBean2 = pizzaService.addPizzaOrderDetails(pizzaOrderBean);
			modelAndView.setViewName("PizzaOrderSuccess");
			modelAndView.addObject("message",
					"Hi: " + pizzaOrderBean2.getCustomerName() + " your order is placed with orderId: "
							+ pizzaOrderBean2.getOrderId() + ", Bill to be paid is: " + pizzaOrderBean2.getBill());
		}

		return modelAndView;
	}

	@ModelAttribute("dynamicLoadingPizzaNames")
	public Map<Integer, String> populatePizzaNames() throws Exception {
		try {
			return pizzaService.findAllPizzaDetails();
		} catch (Exception e) {
			throw e;
		}
	}

	// Error Handler:
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.addObject("exception", exception);
		return modelAndView;

	}

}
