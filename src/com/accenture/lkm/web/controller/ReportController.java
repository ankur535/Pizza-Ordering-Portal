package com.accenture.lkm.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.service.PizzaService;

import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.BillRangeBean;

@Controller
public class ReportController {

	@Autowired
	private PizzaService pizzaService;

	@RequestMapping(value = "/loadDateRangeReportPage", method = RequestMethod.GET)
	public ModelAndView loadDateRangeReportPage() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("OrderReport");
		modelAndView.addObject("billRangeObj", new BillRangeBean());
		return modelAndView;
	}

	@RequestMapping(value = "/getOrderDetails", method = RequestMethod.POST)
	public ModelAndView getOrderDetails(@ModelAttribute("billRangeObj") BillRangeBean billRangeBean) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		try {
			List<PizzaOrderBean> pizzaOrderBeans = pizzaService.getOrderDetails(billRangeBean.getFromPrice(), billRangeBean.getToPrice());
			modelAndView.setViewName("OrderReport");
			modelAndView.addObject("pizzaOrderList",pizzaOrderBeans);

		} catch (Exception e) {
			throw e;
		}
		
		return modelAndView;
	}

	// Error Handler:
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		return modelAndView;
	}

}
