import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'
import { httpClient } from './lib/httpClient'
import { ApiResponse, Role } from './@types'
import { HttpError } from './lib/httpError'

export async function middleware(request: NextRequest) {
    const { pathname } = request.nextUrl
    const token = request.cookies.get('ACCESS_TOKEN')?.value
    const businessId = request.cookies.get('X-Business-Id')?.value
    const signInUrl = new URL('/sign-in', request.url)
    const businessesUrl = new URL('/businesses', request.url)
    let currentRole: Role | null = null;

    if (!token) {
        if (pathname === '/sign-in') return NextResponse.next()
        return NextResponse.redirect(signInUrl)
    }

    if (token) {
        try {
            const response = await httpClient.get<ApiResponse<Role>>(`/auth/role?businessId=${businessId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            currentRole = response.data
            return NextResponse.next()
        } catch (error) {
            console.log(error instanceof HttpError ? error.data : "NCEQuhcquieh")
            if (error instanceof HttpError) {
                if (pathname === '/businesses') return NextResponse.next()
                return NextResponse.redirect(businessesUrl)
            }
        }
    }
    return NextResponse.next()
}

export const config = {
    matcher: [
        '/settings/:path*',
        '/apps',
        '/dashboard',
        '/templates',
        '/attendances',
        '/campaigns',
        '/businesses',
        '/users',
        '/tasks',
        '/leaves',
        '/payrolls',
        '/payslips/:path*',
        '/roles',
        '/permissions',
        '/notifications',
    ],
}