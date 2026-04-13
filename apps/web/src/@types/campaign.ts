import { Contact } from "./contact"
import { Template } from "./template"

type CampaignSendType = "IMMEDIATE" | "SCHEDULED"

export type Campaign = {
    name: string
    description: string | null
    sendType: CampaignSendType
    scheduleAt: string

    template: Template

    contacts: Contact[]
}