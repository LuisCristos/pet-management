package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapperMS {

    CustomerMapperMS INSTANCE = Mappers.getMapper(CustomerMapperMS.class);

    @ValueMappings({
            @ValueMapping(source = "MALE", target = "Male"),
            @ValueMapping(source = "FEMALE", target = "Female"),
            @ValueMapping(source = "OTHER", target = "Other")
    })
    @Mapping(source = "gender", target = "gender")
    Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto);
}
