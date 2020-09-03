package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> findAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl("/api/v1/vendor/" +  vendor.getId());
                    return  vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findVendorById(Long id) {
        Optional<VendorDTO> vendorDTO = vendorRepository.findById(id).map(vendor -> {
            VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDto(vendor);
            vendorDTO1.setVendorUrl("/api/v1/vendor/" +  vendor.getId());
            return vendorDTO1;
        });

        return vendorDTO.orElse(null);
    }

    @Override
    public VendorDTO findVendorByName(String name) {
        Optional<VendorDTO> vendorDTO = vendorRepository.findOneByNameLike("%"+name+"%").map(vendor -> {
            VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDto(vendor);
            vendorDTO1.setVendorUrl("/api/v1/vendor/" +  vendor.getId());
            return vendorDTO1;
        });

        return vendorDTO.orElse(null);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        return saveAndReturnVendorDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDto(savedVendor);

        savedVendorDTO.setVendorUrl("/api/v1/vendor/" + savedVendor.getId());

        return savedVendorDTO;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO patchVendorById(Long id, VendorDTO vendorDTO) {
        Optional<Vendor> vendor = vendorRepository.findById(id);

        if (vendor.isPresent()){
            if (vendorDTO.getName() != null){
                vendor.get().setName(vendorDTO.getName());
            }
            if (vendorDTO.getEmployeesQty() != null){
                vendor.get().setEmployeesQty(vendorDTO.getEmployeesQty());
            }

            return saveAndReturnVendorDTO(vendor.get());
        } else {
            // TODO: handle exceptios
            throw new RuntimeException("Vendor Id not found");
        }
    }

    @Override
    public void deleteVendorById(Long id) {

        vendorRepository.deleteById(id);

    }
}
