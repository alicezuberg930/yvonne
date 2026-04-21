'use client'
import { z } from 'zod'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { showSubmittedData } from '@/lib/show-submitted-data'
import { Button } from '@/components/ui/button'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog'
import { Template } from '@/@types'
import { FormProvider, RHFRichTextEditor, RHFTextField } from '@/components/hook-form'
import { FieldGroup } from '@/components/ui/field'
import { TemplateValidators } from '@/validators/template'
import { useCallback } from 'react'
import { RHFUpload } from '@/components/hook-form/RHFUpload'
import { uploadFile } from '@/lib/repository/api'

type UserActionDialogProps = {
  currentRow?: Template
  open: boolean
  onOpenChange: (open: boolean) => void
}

export function TemplatesActionDialog({
  currentRow,
  open,
  onOpenChange,
}: UserActionDialogProps) {
  const isEdit = !!currentRow
  const form = useForm<TemplateValidators.TemplateForm>({
    resolver: zodResolver(TemplateValidators.formSchema),
    defaultValues: isEdit
      ? {
        ...currentRow,
        isEdit,
      }
      : {
        header: '',
        body: '',
        footer: '',
        websiteUrl: null,
        contactPhone: null,
        isEdit,
      },
  })

  const onSubmit = async (values: TemplateValidators.TemplateForm) => {
    form.reset()
    console.log(values)
    // showSubmittedData(values)
    // onOpenChange(false)
  }

  const { handleSubmit, reset, setError, setValue } = form

  const handleDropThumbnail = useCallback((acceptedFiles: File[]) => {
    const file = acceptedFiles[0]
    if (!file) return
    const img = new window.Image()
    img.src = URL.createObjectURL(file)
    const newFile = Object.assign(file, {
      preview: URL.createObjectURL(file),
    })
    // setValue('thumbnail', newFile, { shouldValidate: true })
    // img.onload = () => {
    //   URL.revokeObjectURL(img.src)
    //   if (img.naturalWidth / img.naturalHeight !== 1) {
    //     setError('thumbnail', { type: 'manual', message: ('thumbnail_must_be_square') })
    //   } else {
    //     const newFile = Object.assign(file, {
    //       preview: URL.createObjectURL(file),
    //     })
    //     setValue('thumbnail', newFile, { shouldValidate: true })
    //   }
    // }
    // img.onerror = () => {
    //   URL.revokeObjectURL(img.src)
    //   setError('thumbnail', { type: 'manual', message: ('thumbnail_must_be_square') })
    // }
  }, [setValue, setError])

  return (
    <Dialog
      open={open}
      onOpenChange={(state) => {
        reset()
        onOpenChange(state)
      }}
    >
      <DialogContent className='sm:max-w-xl'>
        <DialogHeader className='text-start'>
          <DialogTitle>{isEdit ? 'Edit User' : 'Add New User'}</DialogTitle>
          <DialogDescription>
            {isEdit ? 'Update the user here. ' : 'Create new user here. '}
            Click save when you're done.
          </DialogDescription>
        </DialogHeader>
        <div className='h-105 w-[calc(100%+0.75rem)] overflow-y-auto py-1 pe-3'>
          <FormProvider id='templates-form' methods={form} onSubmit={handleSubmit(onSubmit)}>
            <div className='grid gap-3'>
              <FieldGroup>
                {/* <RHFUpload
                  multiple={false}
                  name='thumbnail'
                  maxSize={15728640}
                  onDrop={handleDropThumbnail}
                  onDelete={() => setValue('thumbnail', null, { shouldValidate: true })}
                  fieldLabel={('song_audio_file')}
                /> */}
                <RHFRichTextEditor
                  name='header'
                  fieldLabel='Header'
                />
                <RHFRichTextEditor
                  name='body'
                  fieldLabel='Body'
                />
                <RHFRichTextEditor
                  name='footer'
                  fieldLabel='Footer'
                />
                <RHFTextField
                  name='websiteUrl'
                  fieldLabel='Website URL'
                />
                <RHFTextField
                  name='contactPhone'
                  fieldLabel='Contact Phone'
                />
              </FieldGroup>
            </div>
          </FormProvider>
        </div>
        <DialogFooter>
          <Button type='submit' form='templates-form'>
            Save changes
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  )
}