package server.rem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.rem.annotations.RequestUser;
import server.rem.dtos.leave_request.CreateLeaveRequestDto;
import server.rem.dtos.leave_request.QueryLeaveRequestDto;
import server.rem.dtos.leave_request.UpdateLeaveRequestDto;
import server.rem.entities.Business;
import server.rem.entities.LeaveRequest;
import server.rem.entities.User;
import server.rem.enums.LeaveStatus;
import server.rem.mappers.LeaveRequestMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.LeaveRequestRepository;
import server.rem.repositories.UserRepository;
import server.rem.specifications.LeaveRequestSpecification;
import server.rem.utils.exceptions.ResourceNotFoundException;
import server.rem.utils.exceptions.UnauthorizedException;

import java.util.List;

@Service
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, UserRepository userRepository, BusinessRepository businessRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
    }

    public LeaveRequest createLeaveRequest(@RequestUser String userId, CreateLeaveRequestDto dto) {
        Business business = businessRepository.findById(dto.getBusinessId()).orElseThrow(() -> new ResourceNotFoundException("Business not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        LeaveRequest leaveRequest = LeaveRequestMapper.toEntity(dto);
        leaveRequest.setUser(user);
        leaveRequest.setBusiness(business);
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequestByUser(String userId, QueryLeaveRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found"));

        return leaveRequestRepository.findAllByUser(user);
    }

    public Page<LeaveRequest> getLeaveRequests(QueryLeaveRequestDto dto) {
        Pageable pageable = PageRequest.of(
                dto.getPage() != null ? Integer.parseInt(dto.getPage()) : 0,
                dto.getLimit() != null ? Integer.parseInt(dto.getLimit()) : 10
        );
        Specification<LeaveRequest> spec = LeaveRequestSpecification.withFilters(dto);
        return leaveRequestRepository.findAll(spec, pageable);
    }

    public LeaveRequest updateLeaveRequest(String leaveId, UpdateLeaveRequestDto dto, String userId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        if(!leaveRequest.getStatus().equals(LeaveStatus.PENDING)) throw new RuntimeException("Cannot update leave request after it has been processed");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!user.getId().equals(leaveRequest.getUser().getId())) throw new UnauthorizedException("This leave request is not your");
        if(dto.getReason() != null) leaveRequest.setReason(dto.getReason());
        if(dto.getStartDate() != null) leaveRequest.setStartDate(dto.getStartDate());
        if(dto.getEndDate() != null) leaveRequest.setEndDate(dto.getEndDate());
        if(dto.getDays() != null) leaveRequest.setDays(dto.getDays());
        if(dto.getType() != null) leaveRequest.setType(dto.getType());
        return leaveRequestRepository.save(leaveRequest);
    }

    // approve or reject (only team_lead, admin, hr are authorized)
    public LeaveRequest updateLeaveRequestStatus(String leaveId, UpdateLeaveRequestDto dto, String userId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        if(!leaveRequest.getStatus().equals(LeaveStatus.PENDING)) throw new RuntimeException("Cannot update leave request after it has been processed");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        leaveRequest.setStatus(dto.getStatus());
        leaveRequest.setApprover(user);
        leaveRequest.setApproverNote(dto.getApproverNote());
        return leaveRequestRepository.save(leaveRequest);
    }
}