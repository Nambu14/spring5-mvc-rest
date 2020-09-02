package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Categories Loaded = " + categoryRepository.count() );


        Customer turco = new Customer();
        turco.setFirstName("Carlos");
        turco.setLastName("Menem");

        Customer chupete = new Customer();
        chupete.setFirstName("Chupete");
        chupete.setLastName("De La Rua");

        Customer tortuga = new Customer();
        tortuga.setFirstName("Arturo");
        tortuga.setLastName("Illia");

        customerRepository.save(turco);
        customerRepository.save(chupete);
        customerRepository.save(tortuga);

        System.out.println("Customers Loaded: " + customerRepository.count());

    }
}
