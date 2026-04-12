type EmailProvider = "SMTP" | "EXCHANGE" | "SENDGRID" | "RESEND" | "MAILGUN" | "AMAZON_SES" | "POSTMARK" | "OTHER"

type PhoneProvider = "TWILIO" | "VONAGE" | "OTHER"

export type Business = {
    id: string
    name: string
    description: string
    slug: string
    logoUrl: string
    workStartTime: string
    insuranceContributionSalary: number
    twilioAccountSid: string
    twilioAuthToken: string
    twilioPhoneNumber: string
    vonageApiKey: string
    vonageApiSecret: string
    cloudinaryCloudName: string
    cloudinaryApiKey: string
    cloudinaryApiSecret: string
    resendApiKey: string
    resendEmail: string
    mailHost: string
    mailPort: number
    mailUsername: string
    mailPassword: string
    sendGridApiKey: string
    sendGridUsername: string
    mailgunApiKey: string
    mailgunDomain: string
    mailgunUsername: string
    mailProvider: EmailProvider
    phoneDriver: PhoneProvider
}