'use client'
import { ConfigDrawer } from '@/components/config-drawer'
import { Header } from '@/layout/header'
import { Main } from '@/layout/main'
import { ProfileDropdown } from '@/components/profile-dropdown'
import { Search } from '@/components/search'
import { ThemeSwitch } from '@/components/theme-switch'
import { TemplatesDialogs } from './components/templates-dialogs'
import { TemplatesPrimaryButtons } from './components/templates-primary-buttons'
import { TemplatesProvider } from './components/templates-provider'
import { TemplatesTable } from './components/templates-table'
import { getTemplates as gt } from '@/lib/repository/api'
import { useEffect, useState } from 'react'
import { Template } from '@/@types'

export function Templates() {
  const [templates, setTemplates] = useState<Template[]>([])

  useEffect(() => {
    const getTemplates = async () => {
      const response = await gt()
      setTemplates(response.data)
    }
    getTemplates()
  }, [])

  return (
    <TemplatesProvider>
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
            <h2 className='text-2xl font-bold tracking-tight'>Template List</h2>
            <p className='text-muted-foreground'>
              Manage your marketing templates here.
            </p>
          </div>
          <TemplatesPrimaryButtons />
        </div>
        <TemplatesTable data={templates} />
      </Main>

      <TemplatesDialogs />
    </TemplatesProvider>
  )
}