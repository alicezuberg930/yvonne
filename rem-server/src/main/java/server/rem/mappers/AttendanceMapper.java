package server.rem.mappers;

import server.rem.dtos.attendance.AttendanceDto;
import server.rem.entities.Attendance;
import server.rem.enums.CheckInType;

public class AttendanceMapper {
    public static Attendance toEntity(AttendanceDto dto) {
        return Attendance.builder()
                .checkInTime(dto.getCheckInTime())
                .checkOutTime(dto.getCheckOutTime())
                .date(dto.getDate())
                .type(dto.getType() != null ? dto.getType() : CheckInType.OFFICE)
                .note(dto.getNote())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }

}
