package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapperMS {

    CustomerMapperMS INSTANCE = Mappers.getMapper(CustomerMapperMS.class);


    Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto customerToCustomerResponseDto(Customer customer);

    @Mapping(source = "id", target = "customerId")
    CustomerResponseDto customerListToCustomerResponseDtoList(Customer customer);

    Customer updateCustomerFromCustomerRequestDto(CustomerRequestDto customerRequestDto,
                                                  @MappingTarget Customer customer);
}
