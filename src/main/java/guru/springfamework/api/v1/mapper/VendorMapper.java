package guru.springfamework.api.v1.mapper;

import guru.springfamework.domain.Vendor;
import guru.springfamework.api.v1.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);

    VendorDTO vendorToVendorDto(Vendor vendor);
}
