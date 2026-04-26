'use client'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { Button } from '@/components/ui/button'
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
} from '@/components/ui/dialog'
import { FormProvider, RHFTextField } from '@/components/hook-form'
import { FieldGroup } from '@/components/ui/field'
import { toast } from 'sonner'
import { HttpError } from '@/lib/repository/httpError'
import { BusinessValidators } from '@/validators/business'
import { RHFUpload } from '@/components/hook-form/RHFUpload'
import { useCallback } from 'react'
import { createBusiness, uploadFile } from '@/lib/repository/api'

type CampaignActionDialogProps = {
    open: boolean
    onOpenChange: (open: boolean) => void
}

export function NewBusinessDialog({
    open,
    onOpenChange,
}: CampaignActionDialogProps) {
    const form = useForm<BusinessValidators.BusinessForm>({
        resolver: zodResolver(BusinessValidators.formSchema),
        defaultValues: {
            name: 'Rem Solution',
            description: 'Rem outsource company, a place for worshipping rem',
            slug: 'rem-solution',
            logoUrl: null,
            workStartTime: undefined,
            insuranceContributionSalary: "1000000",
        },
    })

    const { handleSubmit, reset, setValue } = form

    const onSubmit = async (values: BusinessValidators.BusinessForm) => {
        const submit = async () => {
            try {
                if (values.logoUrl instanceof File) {
                    let imgResponse = await uploadFile(values.logoUrl as File, "/business-logo");
                    values = { ...values, logoUrl: imgResponse.data }
                }
                return await createBusiness(values)
            } catch (error) {
                throw error
            } finally {
                form.reset()
                onOpenChange(false)
            }
        }
        toast.promise(submit,
            {
                loading: "Submitting data",
                error: (err) => (err as HttpError).message,
                success: (data) => data?.message
            }
        )
    }

    const handleDropThumbnail = useCallback((acceptedFiles: File[]) => {
        const file = acceptedFiles[0]
        if (!file) return
        const img = new window.Image()
        img.src = URL.createObjectURL(file)
        const newFile = Object.assign(file, { preview: URL.createObjectURL(file) })
        setValue('logoUrl', newFile, { shouldValidate: true })
    }, [setValue])


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
                    <DialogTitle>Create new business</DialogTitle>
                    <DialogDescription>
                        Create new business here. Click save when you're done.
                    </DialogDescription>
                </DialogHeader>
                <div className='h-105 w-[calc(100%+0.75rem)] overflow-y-auto py-1 pe-3'>
                    <FormProvider id='businesses-form' methods={form} onSubmit={handleSubmit(onSubmit)}>
                        <div className='space-y-4 px-0.5'>
                            <FieldGroup>
                                <RHFTextField
                                    name='name'
                                    fieldLabel='Name'
                                />
                                <RHFTextField
                                    name='description'
                                    fieldLabel='Description'
                                />
                                <RHFTextField
                                    name='slug'
                                    fieldLabel='Slug'
                                />
                                <RHFTextField
                                    type='time'
                                    name='workStartTime'
                                    fieldLabel='Work start time'
                                />
                                <RHFTextField
                                    type='number'
                                    name='insuranceContributionSalary'
                                    fieldLabel='Insurance Contribution Salary'
                                />
                                <RHFUpload
                                    multiple={false}
                                    name='logoUrl'
                                    maxSize={15728640}
                                    onDrop={handleDropThumbnail}
                                    onDelete={() => setValue('logoUrl', null, { shouldValidate: true })}
                                    fieldLabel={('song_audio_file')}
                                />
                            </FieldGroup>
                        </div>
                    </FormProvider>
                </div>
                <DialogFooter>
                    <Button type='submit' form='businesses-form'>
                        Save changes
                    </Button>
                </DialogFooter>
            </DialogContent >
        </Dialog >
    )
}