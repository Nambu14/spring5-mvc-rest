package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> findAllVendors();

    VendorDTO findVendorById(Long id);

    VendorDTO findVendorByName(String name);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendorById(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
