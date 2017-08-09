package com.atwhere.p2p.service;

import com.atwhere.p2p.vo.CustomerModel;
import com.atwhere.p2p.vo.CustomerQueryModel;
import com.atwhere.p2p.vo.Student;

public interface ICustomerService extends IBaseService<CustomerModel,CustomerQueryModel>
{

	void addStudetn(Student student);

}
