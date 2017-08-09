package com.atwhere.p2p.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atwhere.p2p.dao.ICustomerDAO;
import com.atwhere.p2p.service.ICustomerService;
import com.atwhere.p2p.vo.CustomerModel;
import com.atwhere.p2p.vo.CustomerQueryModel;
import com.atwhere.p2p.vo.Student;


@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel,CustomerQueryModel> implements ICustomerService
{
	private ICustomerDAO customerDao;
	
	@Autowired
	public void setCustomerDao(ICustomerDAO customerDao) 
	{
		this.customerDao = customerDao;
		super.setDao(customerDao);
	}

	@Override
	public void addStudetn(Student student) {
		customerDao.addStudent(student);
	}

	
	
}
