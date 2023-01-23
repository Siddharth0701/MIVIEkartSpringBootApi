package com.miviekart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.miviekart.dao.ICustomerRepository;
import com.miviekart.dto.CustomerData;
import com.miviekart.exception.IdNotFoundException;
import com.miviekart.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    private Customer getCustomerEntity(CustomerData customerData) {
        Customer customer = new Customer();
        customer.setCustomerId(customerData.getCustomerId());
        customer.setCustomerName(customerData.getCustomerName());
        customer.setCustomerAddress(customerData.getCustomerAddress());
        customer.setCustomerEmailId(customerData.getCustomerEmailId());
        customer.setCustomerPhoneNumber(customerData.getCustomerPhoneNumber());
        return customer;
    }

    private CustomerData getCustomerData(Customer customer) {
        CustomerData customerData = new CustomerData();
        customerData.setCustomerId(customer.getCustomerId());
        customerData.setCustomerName(customer.getCustomerName());
        customerData.setCustomerAddress(customer.getCustomerAddress());
        customerData.setCustomerEmailId(customer.getCustomerEmailId());
        customerData.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        return customerData;
    }

    @Override
    public List<CustomerData> findAll() {

        List<CustomerData> customerDataList = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> {
            customerDataList.add(getCustomerData(customer));
        });
        return customerDataList;
    }

    @Override
    public CustomerData findById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional == null) {
            new IdNotFoundException("customer not found");

        }
        return getCustomerData(customerOptional.get());
    }

    @Override
    public CustomerData create(CustomerData t) {
        Customer customer = getCustomerEntity(t);

        return getCustomerData(customerRepository.save(customer));
    }

    @Override
    public boolean delete(int id) {
        Customer customer = customerRepository.findById(id).get();
        if (customer != null) {
            customerRepository.deleteById(id);
            return true;

        }
        return false;
    }

    @Override
    public CustomerData update(CustomerData t, int id) {
        Customer customer1 = customerRepository.findById(id).get();
        if (customer1 != null) {
            customer1.setCustomerName(t.getCustomerName());
            customer1.setCustomerAddress(t.getCustomerAddress());
            customer1.setCustomerEmailId(t.getCustomerEmailId());
            customer1.setCustomerPhoneNumber(t.getCustomerPhoneNumber());
           customerRepository.save(customer1);
            return getCustomerData(customer1);

        }
        return null;
    }

   
}
