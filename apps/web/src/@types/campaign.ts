import { Contact } from "./contact"
import { Template } from "./template"

type CampaignStatus = "DRAFT" | "PENDING" | "PROCESSING" | "SENT" | "FAILED"

type CampaignSendType = "IMMEDIATE" | "SCHEDULED"

export type Campaign = {
    name: string
    description: string | null
    sendType: CampaignSendType
    scheduleAt: string
    status: CampaignStatus

    template: Template

    contacts: Contact[]
}