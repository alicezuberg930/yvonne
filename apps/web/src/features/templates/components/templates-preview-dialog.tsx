'use client'
import { MailPlus, Eye } from 'lucide-react'
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog'
import { Template } from '@/@types'
import { ScrollArea } from '@/components/ui/scroll-area'

type UserInviteDialogProps = {
  open: boolean
  onOpenChange: (open: boolean) => void
  currentRow: Template
}

export function TemplatesPreviewDialog({
  open,
  onOpenChange,
  currentRow
}: UserInviteDialogProps) {

  return (
    <Dialog
      open={open}
      onOpenChange={(state) => {
        onOpenChange(state)
      }}
    >
      <DialogContent className='sm:max-w-3xl max-h-[90vh] flex flex-col'>
        <DialogHeader className='text-start'>
          <DialogTitle className='flex items-center gap-2'>
            <Eye />
            Preview Email Template
          </DialogTitle>
          <DialogDescription>
            Invite new user to join your team by sending them an email
            invitation. Assign a role to define their access level.
          </DialogDescription>
        </DialogHeader>
        <div className='flex-1 min-h-0 overflow-scroll wrap-break-word'>
          <div dangerouslySetInnerHTML={{ __html: currentRow.header }}>
          </div>
          <div dangerouslySetInnerHTML={{ __html: currentRow.body }}>
          </div>
          <div dangerouslySetInnerHTML={{ __html: currentRow.footer }}>
          </div>
        </div>
        {/* <DialogFooter className='gap-y-2'>
          <DialogClose>
            <Button variant='outline'>Cancel</Button>
          </DialogClose>
          <Button type='submit' form='user-invite-form'>
            Invite
            <Send />
          </Button>
        </DialogFooter> */}
      </DialogContent>
    </Dialog>
  )
}
