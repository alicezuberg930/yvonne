'use client'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import Link from 'next/link'
import { Loader2, LogIn } from 'lucide-react'
import { IconFacebook, IconGithub } from '@/assets/brand-icons'
import { useAuthStore } from '@/stores/auth-store'
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
import { FormProvider, RHFPasswordField, RHFTextField } from '@/components/hook-form'
import { Field, FieldGroup } from '@/components/ui/field'
import { AuthValidators } from '@/validators/auth'
import { useAuth } from '@/context/auth-provider'

interface UserAuthFormProps extends React.HTMLAttributes<HTMLDivElement> {
  redirectTo?: string
}

export function SignInForm({
  className,
  redirectTo,
  ...props
}: UserAuthFormProps) {
  const { auth } = useAuthStore()
  const { signIn } = useAuth()

  const form = useForm<AuthValidators.SignIn>({
    resolver: zodResolver(AuthValidators.signInSchema),
    defaultValues: {
      email: '',
      password: '',
    },
  })

  const { handleSubmit, formState: { isSubmitting } } = form

  const onSubmit = async (data: AuthValidators.SignIn) => {
    await signIn(data)
    const mockUser = {
      accountNo: 'ACC001',
      email: data.email,
      role: ['user'],
      exp: Date.now() + 24 * 60 * 60 * 1000,
    }
    auth.setUser(mockUser)
    auth.setAccessToken('mock-access-token')
  }

  return (
    <FormProvider methods={form} onSubmit={handleSubmit(onSubmit)}>
      <div
        className={cn('grid gap-3', className)}
        {...props}
      >
        <FieldGroup>
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
        </FieldGroup>

        <Field orientation={'horizontal'}>
          <Link
            href='/forgot-password'
            className='text-sm font-medium text-muted-foreground hover:opacity-75'
          >
            Forgot password?
          </Link>
        </Field>

        <Button disabled={isSubmitting} type='submit'>
          {isSubmitting ? <Loader2 className='animate-spin' /> : <LogIn />}
          Sign in
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
          <Button variant='outline' type='button' disabled={isSubmitting}>
            <IconGithub className='h-4 w-4' /> GitHub
          </Button>
          <Button variant='outline' type='button' disabled={isSubmitting}>
            <IconFacebook className='h-4 w-4' /> Facebook
          </Button>
        </div>
      </div>
    </FormProvider>
  )
}