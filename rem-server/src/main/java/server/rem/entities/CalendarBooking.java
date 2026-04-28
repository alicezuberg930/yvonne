package server.rem.entities;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.CalendarBookingStatus;

@Entity
@Table(name = "calendar_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarBooking extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_staff_id", nullable = true)
    private User serviceStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_id", nullable = true)
    private User correspondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = true)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @Column(name = "booking__start_date", nullable = false)
    private Instant bookingStartDate;

    @Column(name = "booking_end_date", nullable = false)
    private Instant bookingEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Builder.Default
    private CalendarBookingStatus type = CalendarBookingStatus.BOOKED;
    
    @Column(name = "cancel_reason", length = 255, nullable = true)
    private String cancelReason;
}
