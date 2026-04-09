package server.rem.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import server.rem.annotations.RequestUser;
import server.rem.dtos.APIResponse;
import server.rem.dtos.attendance.AttendanceDto;
import server.rem.dtos.attendance.QueryAttendanceDto;
import server.rem.entities.Attendance;
import server.rem.services.AttendanceService;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse<Attendance>> checkIn(@RequestUser String userId, @Valid @RequestBody AttendanceDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            201,
            "Check in successfully",
            attendanceService.checkIn(userId, dto)
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse<Page<Attendance>>> getMyAttendances(@RequestUser String userId, @ModelAttribute QueryAttendanceDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Attendance list retrieved successfully",
            attendanceService.getMyAttendances(userId, dto)
        ));
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<Page<Attendance>>> getAllAttendances(@ModelAttribute QueryAttendanceDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Attendance list retrieved successfully",
            attendanceService.getAllAttendances(dto)
        ));
    }
}
