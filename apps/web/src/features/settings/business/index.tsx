import { ContentSection } from '../components/content-section'
import { BusinessForm } from './business-form'

export function SettingsBusiness() {
  return (
    <ContentSection
      title='Business'
      desc='Update your business settings. Set your company"s work start time, taxes, name, description, logo, etc.'
    >
      <BusinessForm />
    </ContentSection>
  )
}
