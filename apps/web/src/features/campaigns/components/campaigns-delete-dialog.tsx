'use client'
import { useState } from 'react'
import { AlertTriangle } from 'lucide-react'
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { ConfirmDialog } from '@/components/confirm-dialog'
import { Campaign } from '@/@types'
import { toast } from 'sonner'
import { deleteCampaign } from '@/lib/repository/api'
import { HttpError } from '@/lib/repository/httpError'

type CampaignDeleteDialogProps = {
  open: boolean
  onOpenChange: (open: boolean) => void
  currentRow: Campaign
}

export function CampaignsDeleteDialog({
  open,
  onOpenChange,
  currentRow,
}: CampaignDeleteDialogProps) {
  const [value, setValue] = useState<string>('')

  const handleDelete = () => {
    if (value.trim() !== currentRow.name) return
    const submit = async () => {
      try {
        return await deleteCampaign(currentRow.id)
      } catch (error) {
        throw error
      } finally {
        onOpenChange(false)
      }
    }
    toast.promise(submit,
      {
        loading: "Deleting campaign",
        error: (err) => err instanceof HttpError ? err.message : "Internal server error",
        success: (data) => data?.message
      }
    )
  }

  return (
    <ConfirmDialog
      open={open}
      onOpenChange={onOpenChange}
      handleConfirm={handleDelete}
      disabled={value.trim() !== currentRow.name}
      title={
        <span className='text-destructive'>
          <AlertTriangle
            className='me-1 inline-block stroke-destructive'
            size={18}
          />{' '}
          Delete campaign
        </span>
      }
      desc={
        <div className='space-y-4'>
          <p className='mb-2'>
            Are you sure you want to delete{' '}
            <span className='font-bold'>{currentRow.name}</span>?
            <br />
            This action will permanently remove the campaign from the system. This cannot be undone.
          </p>

          <Label className='my-2'>
            Campaign name:
            <Input
              value={value}
              onChange={(e) => setValue(e.target.value)}
              placeholder='Enter campaign name to confirm deletion.'
            />
          </Label>

          <Alert variant='destructive'>
            <AlertTitle>Warning!</AlertTitle>
            <AlertDescription>
              Please be careful, this operation can not be rolled back.
            </AlertDescription>
          </Alert>
        </div>
      }
      confirmText='Delete'
      destructive
    />
  )
}