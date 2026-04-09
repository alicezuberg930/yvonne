package server.rem.enums;

public enum LeaveStatus {
    PENDING,         // waiting for approval
    APPROVED,        // approved by manager
    REJECTED,        // rejected by manager
    CANCELLED,       // canceled by employee
    EXPIRED          // approved but never used
}