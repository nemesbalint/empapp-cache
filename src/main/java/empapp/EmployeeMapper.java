package empapp;

import empapp.dto.AddressDto;
import empapp.dto.EmployeeDto;
import empapp.entity.Address;
import empapp.entity.Employee;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeesDto(List<Employee> employees);

    AddressDto toAddressDto(Address address);

    List<AddressDto> toAddressesDto(List<Address> addresses);

    Employee toEmployee(EmployeeDto employee);

    @AfterMapping
    default void setParent(@MappingTarget Employee employee){
        if (employee.getAddresses() != null) {
            for (Address address : employee.getAddresses()) {
                address.setEmployee(employee);
            }
        }
    }
}
