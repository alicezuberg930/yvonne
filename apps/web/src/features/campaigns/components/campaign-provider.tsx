'use client'
import React, { useEffect, useState } from 'react'
import useDialogState from '@/hooks/use-dialog-state'
import { Campaign, Contact, Template } from '@/@types'
import { getContacts, getTemplates } from '@/lib/repository/api'

type CampaignsDialogType = 'preview' | 'add' | 'edit' | 'delete'

type CampaignsContextType = {
  open: CampaignsDialogType | null
  setOpen: (str: CampaignsDialogType | null) => void
  currentRow: Campaign | null
  setCurrentRow: React.Dispatch<React.SetStateAction<Campaign | null>>
  templates: Template[]
  contacts: Contact[]
}

const CampaignsContext = React.createContext<CampaignsContextType | null>(null)

export function CampaignsProvider({ children }: { children: React.ReactNode }) {
  const [open, setOpen] = useDialogState<CampaignsDialogType>(null)
  const [currentRow, setCurrentRow] = useState<Campaign | null>(null)
  const [templates, setTemplates] = useState<Template[]>([])
  const [contacts, setContacts] = useState<Contact[]>([])

  useEffect(() => {
    getTemplates().then(res => setTemplates(res.data))
    getContacts().then(res => setContacts(res.data.content))
  }, [])

  return (
    <CampaignsContext value={{ open, setOpen, currentRow, setCurrentRow, templates, contacts }}>
      {children}
    </CampaignsContext>
  )
}

// eslint-disable-next-line react-refresh/only-export-components
export const useCampaigns = () => {
  const campaignsContext = React.useContext(CampaignsContext)

  if (!campaignsContext) {
    throw new Error('useCampaigns hook has to be used within <CampaignsContext>')
  }

  return campaignsContext
}