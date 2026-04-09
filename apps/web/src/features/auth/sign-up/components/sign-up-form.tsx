'use client'
import { useState } from 'react'
import { z } from 'zod'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { IconFacebook, IconGithub } from '@/assets/brand-icons'
import { cn } from '@/lib/utils'
import { Button } from '@/components/ui/button'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { PasswordInput } from '@/components/password-input'
import { FormProvider, RHFPasswordField, RHFTextField } from '@/components/hook-form'
import { FieldGroup } from '@/components/ui/field'

const formSchema = z
  .object({
    email: z.email({
      error: (iss) =>
        iss.input === '' ? 'Please enter your email' : undefined,
    }),
    password: z
      .string()
      .min(1, 'Please enter your password')
      .min(7, 'Password must be at least 7 characters long'),
    confirmPassword: z.string().min(1, 'Please confirm your password'),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "Passwords don't match.",
    path: ['confirmPassword'],
  })

export function SignUpForm({
  className,
  ...props
}: React.HTMLAttributes<HTMLDivElement>) {
  const [isLoading, setIsLoading] = useState(false)

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: '',
      password: '',
      confirmPassword: '',
    },
  })

  const { handleSubmit } = form

  function onSubmit(data: z.infer<typeof formSchema>) {
    setIsLoading(true)
    // eslint-disable-next-line no-console
    console.log(data)

    setTimeout(() => {
      setIsLoading(false)
    }, 3000)
  }

  return (
    <FormProvider methods={form} onSubmit={handleSubmit(onSubmit)}>
      <div
        className={cn('grid gap-3', className)}
        {...props}
      >
        {/* <FormField
          control={form.control}
          name='email'
          render={({ field }) => (
            <FormItem>
              <FormLabel>Email</FormLabel>
              <FormControl
                render={() => {
                  <Input placeholder='name@example.com' {...field} />
                }}
              />
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name='password'
          render={({ field }) => (
            <FormItem>
              <FormLabel>Password</FormLabel>
              <FormControl
                render={() => {
                  <PasswordInput placeholder='********' {...field} />
                }}
              />
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name='confirmPassword'
          render={({ field }) => (
            <FormItem>
              <FormLabel>Confirm Password</FormLabel>
              <FormControl
                render={() => {
                  <PasswordInput placeholder='********' {...field} />
                }}
              />
              <FormMessage />
            </FormItem>
          )}
        /> */}
        <FieldGroup>
          <RHFTextField
            name="fullname"
            type="text"
            fieldLabel="Full name"
            placeholder="Hatsune Miku"
          />
          <RHFTextField
            name="phone"
            type="text"
            fieldLabel="Phone"
            placeholder="09333348882"
          />
          <RHFTextField
            name="email"
            type="email"
            fieldLabel="Email"
            placeholder="name@example.com"
          />
          <RHFPasswordField
            name="password"
            fieldLabel="Password"
            placeholder='********'
          />
          <RHFPasswordField
            name="confirmPassword"
            fieldLabel="Confirm Password"
            placeholder='********'
          />
        </FieldGroup>

        <Button className='mt-2' disabled={isLoading} type='submit'>
          Create Account
        </Button>

        <div className='relative my-2'>
          <div className='absolute inset-0 flex items-center'>
            <span className='w-full border-t' />
          </div>
          <div className='relative flex justify-center text-xs uppercase'>
            <span className='bg-background px-2 text-muted-foreground'>
              Or continue with
            </span>
          </div>
        </div>

        <div className='grid grid-cols-2 gap-2'>
          <Button
            variant='outline'
            className='w-full'
            type='button'
            disabled={isLoading}
          >
            <IconGithub className='h-4 w-4' /> GitHub
          </Button>
          <Button
            variant='outline'
            className='w-full'
            type='button'
            disabled={isLoading}
          >
            <IconFacebook className='h-4 w-4' /> Facebook
          </Button>
        </div>
      </div>
    </FormProvider>
  )
}
