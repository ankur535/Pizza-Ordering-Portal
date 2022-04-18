package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.PizzaBean;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.entity.PizzaEntity;
import com.accenture.lkm.entity.PizzaOrderEntity;

@Repository
@Transactional(value = "txManager")
public class PizzaDAOWrapper {

	@Autowired
	private PizzaDAO pizzaDAO;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;

	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) throws Exception {

		PizzaOrderBean pizzaOrderBean2 = null;
		try {
			PizzaOrderEntity pizzaOrderEntity = convertPizzaOrderBeanToEntity(pizzaOrderBean);
			pizzaOrderBean2 = convertPizzaOrderEntityToBean(pizzaOrderDAO.save(pizzaOrderEntity));

		} catch (Exception e) {
			throw e;
		}
		return pizzaOrderBean2;
	}

	public Double getPizzaPrice(Integer pizzaId) throws Exception {

		PizzaEntity pizzaEntity = entityManager.find(PizzaEntity.class, pizzaId);
		return pizzaEntity.getPrice();
	}

	public List<PizzaBean> findAllPizzaDetails() throws Exception {
		List<PizzaBean> pizzaBeans = null;
		List<PizzaEntity> pizzaEntities = null;
		try {
			pizzaBeans = new ArrayList<PizzaBean>();
			pizzaEntities = pizzaDAO.findAllPizzaDetails();
			for (PizzaEntity pizzaEntity : pizzaEntities) {
				pizzaBeans.add(convertPizzaEntityToBean(pizzaEntity));
			}
		} catch (Exception e) {
			throw e;
		}
		return pizzaBeans;
	}

	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception {

		List<PizzaOrderBean> pizzaOrderBeans = null;
		try {
			Query query = entityManager.createQuery("Select k from PizzaOrderEntity k where k.bill between ?1 and ?2");
			query.setParameter(1, fromBill);
			query.setParameter(2, toBill);
			pizzaOrderBeans = new ArrayList<PizzaOrderBean>();
			
			List<PizzaOrderEntity> pizzaOrderEntities = query.getResultList();
			for(PizzaOrderEntity pizzaOrderEntity: pizzaOrderEntities)
			{
				pizzaOrderBeans.add(convertPizzaOrderEntityToBean(pizzaOrderEntity));
			}
		} catch (Exception e) {
			throw e;
		}
		
		
		return pizzaOrderBeans;
	}

	// Utility Methods.......
	public static PizzaBean convertPizzaEntityToBean(PizzaEntity entity) {
		PizzaBean customerBean = new PizzaBean();
		BeanUtils.copyProperties(entity, customerBean);
		return customerBean;
	}

	public static PizzaEntity convertPizzaBeanToEntity(PizzaBean bean) {
		PizzaEntity entity = new PizzaEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}

	// Utility Methods.......
	public static PizzaOrderBean convertPizzaOrderEntityToBean(PizzaOrderEntity entity) {
		PizzaOrderBean pizzaOrderBean = new PizzaOrderBean();
		BeanUtils.copyProperties(entity, pizzaOrderBean);
		return pizzaOrderBean;
	}

	public static PizzaOrderEntity convertPizzaOrderBeanToEntity(PizzaOrderBean bean) {
		PizzaOrderEntity entity = new PizzaOrderEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}

}
