package core.sff.crm.services.customers;

import java.util.List;

import core.sff.crm.domain.Call;
import core.sff.crm.domain.Customer;

public interface CustomerManagementService {
	public void newCustomer(Customer newCustomer);
	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException;
	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException;
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException;
	public List<Customer> findCustomersByName (String name);
	public List<Customer> getAllCustomers();
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException;
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException;
}
