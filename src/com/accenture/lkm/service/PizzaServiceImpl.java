package com.accenture.lkm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PizzaBean;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.dao.PizzaDAOWrapper;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaDAOWrapper pizzaDAOWrapper;

	@Override
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) throws Exception {

		try {
			Double price = pizzaDAOWrapper.getPizzaPrice(pizzaOrderBean.getPizzaId());
			Double bill = price * pizzaOrderBean.getNumberOfPiecesOrdered();
			pizzaOrderBean.setBill(bill);
			

		} catch (Exception e) {
			throw e;
		}
		return pizzaDAOWrapper.addPizzaOrderDetails(pizzaOrderBean);
	}

	@Override
	public Map<Integer, String> findAllPizzaDetails() throws Exception {

		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		try {
			List<PizzaBean> pizzaBeans = pizzaDAOWrapper.findAllPizzaDetails();

			for (PizzaBean pizzaBean : pizzaBeans) {
				map.put(pizzaBean.getPizzaId(), pizzaBean.getPizzaName());
			}
		} catch (Exception e) {
			throw e;
		}

		return map;
	}

	@Override
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception {
		
		List<PizzaOrderBean> pizzaOrderBeans = null;
		try {
			pizzaOrderBeans = pizzaDAOWrapper.getOrderDetails(fromBill,toBill);
		} catch (Exception e) {
			throw e;
		}
		return pizzaOrderBeans;
	}


}
