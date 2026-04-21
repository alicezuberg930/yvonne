package server.rem.enums;

public enum CampaignStatus {
    DRAFT,
    PENDING, // scheduled
    PROCESSING, // being sent
    SENT,
    FAILED
}