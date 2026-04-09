package server.rem.services;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import server.rem.dtos.attendance.AttendanceDto;
import server.rem.dtos.attendance.QueryAttendanceDto;
import server.rem.entities.Attendance;
import server.rem.entities.Business;
import server.rem.entities.User;
import server.rem.enums.CheckInStatus;
import server.rem.mappers.AttendanceMapper;
import server.rem.repositories.AttendanceRepository;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.UserRepository;
import server.rem.specifications.AttendanceSpecification;
import server.rem.utils.exceptions.ConflictException;
import server.rem.utils.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository, BusinessRepository businessRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
    }

    public Attendance checkIn(String userId, AttendanceDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Business business = businessRepository.findById(dto.getBusinessId()).orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        if (attendanceRepository.existsByBusinessAndUserAndDate(business, user, LocalDate.now())){
            throw new ConflictException("User already checked in today");
        }
        Attendance attendance = AttendanceMapper.toEntity(dto);
        attendance.setUser(user);
        attendance.setBusiness(business);
        attendance.setStatus(resolveStatus(dto.getCheckInTime(), business.getWorkStartTime()));
        return attendanceRepository.save(attendance);
    }

//    public Attendance checkOut

    private CheckInStatus resolveStatus(LocalDateTime checkInTime, LocalTime workStartTime) {
        if (workStartTime == null) return CheckInStatus.ON_TIME;
        return checkInTime.toLocalTime().isAfter(workStartTime) ? CheckInStatus.LATE : CheckInStatus.ON_TIME;
    }

    @NonNull
    private Page<Attendance> getAttendanceList(QueryAttendanceDto dto) {
        Pageable pageable = PageRequest.of(
                dto.getPage() != null ? Integer.parseInt(dto.getPage()) : 0,
                dto.getLimit() != null ? Integer.parseInt(dto.getLimit()) : 10
        );
        Specification<Attendance> spec = AttendanceSpecification.withFilters(dto);
        return attendanceRepository.findAll(spec, pageable);
    }

    public Page<Attendance> getMyAttendances(String userId, QueryAttendanceDto dto) {
        dto.setUserId(userId);
        return getAttendanceList(dto);
    }

    // only HR, admin are authorized
    public Page<Attendance> getAllAttendances(QueryAttendanceDto dto) {
        System.out.println(dto.toString());
        return getAttendanceList(dto);
    }
}