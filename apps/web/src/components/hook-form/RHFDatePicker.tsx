import { useFormContext, Controller } from 'react-hook-form'
import { Field, FieldError, FieldLabel } from '../ui/field'
import { type DateRange, type DayPickerProps } from 'react-day-picker'
import { Popover, PopoverContent, PopoverTrigger } from '../ui/popover'
import { Button } from '../ui/button'
import { CalendarIcon } from 'lucide-react'
import { format } from 'date-fns'
import { Calendar } from '../ui/calendar'
import { Input } from '../ui/input'

type RHFDatePickerProps = Omit<DayPickerProps, 'mode'> & {
    placeholder?: string
    withTime?: boolean
    name: string,
    fieldLabel: string
}

export function RHFSingleDatePicker({
    placeholder,
    name,
    fieldLabel,
    withTime = false,
    ...other
}: RHFDatePickerProps) {
    const { control } = useFormContext()

    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState: { error, invalid } }) => {
                const handleDateChange = (selectedDate: Date | undefined) => {
                    if (!selectedDate) return
                    const updated = new Date(selectedDate)
                    // preserve existing time if already set
                    if (field.value) {
                        updated.setHours(field.value.getHours())
                        updated.setMinutes(field.value.getMinutes())
                    }
                    field.onChange(updated)
                }

                const handleTimeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
                    const [hours, minutes] = e.target.value.split(':').map(Number)
                    const updated = new Date(field.value ?? new Date())
                    updated.setHours(hours)
                    updated.setMinutes(minutes)
                    field.onChange(updated)
                }

                return (
                    <Field data-invalid={invalid}>
                        <FieldLabel htmlFor={field.name}>{fieldLabel}</FieldLabel>
                        <Popover>
                            <PopoverTrigger
                                render={
                                    <Button
                                        variant={invalid ? 'destructive' : 'outline'}
                                        data-empty={!field.value}
                                        className='justify-start text-start font-normal data-[empty=true]:text-muted-foreground'
                                    >
                                        {field.value ? (
                                            withTime ? (
                                                format(field.value, 'dd/MM/yyyy HH:mm')
                                            ) : (
                                                format(field.value, 'dd/MM/yyyy')
                                            )
                                        ) : (
                                            <span>{placeholder}</span>
                                        )}
                                        <CalendarIcon className='ms-auto h-4 w-4 opacity-50' />
                                    </Button>
                                }
                            />
                            <PopoverContent className='w-auto p-0'>
                                <Calendar
                                    {...other}
                                    mode='single'
                                    captionLayout='dropdown'
                                    selected={field.value}
                                    onSelect={handleDateChange}
                                    disabled={(date: Date) => date < new Date('1900-01-01')}
                                />
                                {withTime && (
                                    <div className='border-t p-3 flex items-center gap-2'>
                                        <Input
                                            type='time'
                                            className='w-full'
                                            value={field.value ? format(field.value, 'HH:mm') : ''}
                                            onChange={handleTimeChange}
                                        />
                                    </div>
                                )}
                            </PopoverContent>
                        </Popover>
                        {invalid && <FieldError errors={[error]} />}
                    </Field>
                )
            }}
        />
    )
}

export function RHFRangeDatePicker({
    name,
    fieldLabel,
    placeholder,
    ...other
}: RHFDatePickerProps) {
    const { control } = useFormContext()

    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState: { error, invalid } }) => {
                const range: DateRange | undefined = field.value

                const handleRangeChange = (selectedRange: DateRange | undefined) => {
                    field.onChange(selectedRange)
                }

                const formatDisplay = () => {
                    if (!range?.from) return <span>{placeholder ?? 'Pick a date range'}</span>
                    if (!range.to) return format(range.from, 'dd/MM/yyyy')
                    return `${format(range.from, 'dd/MM/yyyy')} - ${format(range.to, 'dd/MM/yyyy')}`
                }

                return (
                    <Field data-invalid={invalid}>
                        <FieldLabel htmlFor={field.name}>{fieldLabel}</FieldLabel>
                        <Popover>
                            <PopoverTrigger
                                render={
                                    <Button
                                        variant={invalid ? 'destructive' : 'outline'}
                                        data-empty={!range?.from}
                                        className='justify-start text-start font-normal data-[empty=true]:text-muted-foreground'
                                    >
                                        {formatDisplay()}
                                        <CalendarIcon className='ms-auto h-4 w-4 opacity-50' />
                                    </Button>
                                }
                            />
                            <PopoverContent className='w-auto p-0'>
                                <Calendar
                                    {...other}
                                    mode='range'
                                    captionLayout='dropdown'
                                    selected={range}
                                    onSelect={handleRangeChange}
                                    numberOfMonths={2}
                                />
                            </PopoverContent>
                        </Popover>
                        {invalid && <FieldError errors={[error]} />}
                    </Field>
                )
            }}
        />
    )
}