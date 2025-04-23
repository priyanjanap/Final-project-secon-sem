package org.example._backend.util;

import lombok.RequiredArgsConstructor;
import org.example._backend.dto.impl.LoanDTO;
import org.example._backend.dto.impl.PaymentDTO;
import org.example._backend.dto.impl.UserDTO;
import org.example._backend.dto.impl.UserDetailsDTO;
import org.example._backend.entity.Impl.LoanEntity;
import org.example._backend.entity.Impl.PaymentEntity;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModelMapping {
    private  final ModelMapper modelMapper;



    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDetailsEntity toUserDetailsEntity( UserDetailsDTO userDetailsDTO) {
        return modelMapper.map(userDetailsDTO, UserDetailsEntity.class);
    }
    public LoanEntity toLoanEntity(LoanDTO loanDTO) {
        return modelMapper.map(loanDTO, LoanEntity.class);
    }
    public PaymentEntity toPaymentEntity(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, PaymentEntity.class);
    }


//    public List<StaffDTO> StaffEntityToStaffDTOList(List<StaffEntity> list) {
//        return modelMapper.map(list, new TypeToken<List<StaffDTO>>() {
//        }.getType());
//    }
//
//    public StaffDTO toStaffDTO(StaffEntity staff) {
//        return modelMapper.map(staff, StaffDTO.class);
//    }
//
//    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
//        return modelMapper.map(staffDTO, StaffEntity.class);
//    }
//
//    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
//        return modelMapper.map(vehicleEntity, VehicleDTO.class);
//    }
//
//    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
//        return modelMapper.map(vehicleDTO, VehicleEntity.class);
//    }
//
//    public List<UserDTO,UserDetailsDTO> toVehicleDTOList(List<>) {
//        return modelMapper.map(, new TypeToken<List<>>() {
//        }.getType());
//    }
//
//    public FieldDTO toFieldDTO(FieldEntity field) {
//        return modelMapper.map(field, FieldDTO.class);
//    }
//
//    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
//        return modelMapper.map(fieldDTO, FieldEntity.class);
//    }
//
//    public List<FieldDTO> toFieldDTOLIST(List<FieldEntity> list) {
//        return modelMapper.map(list, new TypeToken<List<FieldDTO>>() {
//        }.getType());
//    }
//
//    public CropDTO toCropDTO(CropEntity cropEntity) {
//        return modelMapper.map(cropEntity, CropDTO.class);
//    }
//
//    public CropEntity toCropEntity(CropDTO cropDTO) {
//        return modelMapper.map(cropDTO, CropEntity.class);
//    }
//
//    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntities) {
//        return modelMapper.map(cropEntities, new TypeToken<List<CropDTO>>() {
//        }.getType());
//    }
//
//    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
//        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
//    }
//
//    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
//        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
//    }
//
//    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity> equipmentEntityList) {
//        return modelMapper.map(equipmentEntityList, new TypeToken<List<EquipmentDTO>>() {
//        }.getType());
//    }
//
//    public LogDTO toLogDTO(LogEntity logEntity) {
//        return modelMapper.map(logEntity, LogDTO.class);
//    }
//
//    public LogEntity toLogEntity(LogDTO logDTO) {
//        return modelMapper.map(logDTO, LogEntity.class);
//    }
//
//    public List<LogDTO> toLogDtoList(List<LogEntity> logEntities) {
//        return modelMapper.map(logEntities, new TypeToken<List<LogDTO>>() {
//        }.getType());
//    }
}
