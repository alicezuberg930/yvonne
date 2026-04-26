'use client'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { AuthLayout } from '../auth-layout'
import dynamic from 'next/dynamic'
import Link from 'next/link'

const SignInForm = dynamic(
  () => import('./components/sign-in-form').then(mod => mod.SignInForm),
  { ssr: false }
)

export function SignIn() {
  return (
    <AuthLayout>
      <Card className='gap-4'>
        <CardHeader>
          <CardTitle className='text-lg tracking-tight'>Sign in</CardTitle>
          <CardDescription>
            Enter your email and password below to log into your account <br />
            Don't have an account? {' '}
            <Link
              href='/sign-up'
              className='underline underline-offset-4 hover:text-primary'
            >
              Sign up
            </Link>
          </CardDescription>
        </CardHeader>
        <CardContent>
          <SignInForm redirectTo={'businesses'} />
        </CardContent>
        <CardFooter>
          <p className='px-8 text-center text-sm text-muted-foreground'>
            By clicking sign in, you agree to our{' '}
            <a
              href='/terms'
              className='underline underline-offset-4 hover:text-primary'
            >
              Terms of Service
            </a>{' '}
            and{' '}
            <a
              href='/privacy'
              className='underline underline-offset-4 hover:text-primary'
            >
              Privacy Policy
            </a>
            .
          </p>
        </CardFooter>
      </Card>
    </AuthLayout>
  )
}
