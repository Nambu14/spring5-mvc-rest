package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors(){
        return new VendorListDTO(vendorService.findAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.findVendorById(id);
    }

    @GetMapping("/find/{name}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorByName(@PathVariable String name){
        return vendorService.findVendorByName(name);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO newVendor){
        return vendorService.createNewVendor(newVendor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO saveVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendorById(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }
}
