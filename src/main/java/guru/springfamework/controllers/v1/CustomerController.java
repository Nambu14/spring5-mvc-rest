package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers(){
        return new ResponseEntity<>(
                new CustomerListDTO(customerService.findAllCustomers()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        return new ResponseEntity<>(
                customerService.findCustomerById(Long.valueOf(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> saveNewCustomer(@PathVariable Long id,
                                                       @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(
                customerService.patchCustomer(id, customerDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
