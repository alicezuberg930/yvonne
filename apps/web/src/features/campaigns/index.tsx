'use client'
import { ConfigDrawer } from '@/components/config-drawer'
import { Header } from '@/layout/header'
import { Main } from '@/layout/main'
import { ProfileDropdown } from '@/components/profile-dropdown'
import { Search } from '@/components/search'
import { ThemeSwitch } from '@/components/theme-switch'
import { CampaignsDialogs } from './components/campaigns-dialogs'
import { TemplatesPrimaryButtons } from './components/templates-primary-buttons'
import { CampaignsProvider } from './components/campaign-provider'
import { CampaignsTable } from './components/campaigns-table'
import { getCampaigns as gc } from '@/lib/repository/api'
import { useEffect, useState } from 'react'
import { Campaign } from '@/@types'

export function Campaigns() {
  const [campaigns, setCampaigns] = useState<Campaign[]>([])

  useEffect(() => {
    const getCampaigns = async () => {
      const response = await gc()
      setCampaigns(response.data.content)
    }
    getCampaigns()
  }, [])

  return (
    <CampaignsProvider>
      <Header fixed>
        <Search />
        <div className='ms-auto flex items-center space-x-4'>
          <ThemeSwitch />
          <ConfigDrawer />
          <ProfileDropdown />
        </div>
      </Header>

      <Main className='flex flex-1 flex-col gap-4 sm:gap-6'>
        <div className='flex flex-wrap items-end justify-between gap-2'>
          <div>
            <h2 className='text-2xl font-bold tracking-tight'>Campaign List</h2>
            <p className='text-muted-foreground'>
              Manage your marketing campaigns here.
            </p>
          </div>
          <TemplatesPrimaryButtons />
        </div>
        <CampaignsTable data={campaigns} />
      </Main>

      <CampaignsDialogs />
    </CampaignsProvider>
  )
}