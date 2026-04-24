import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'
import { ApiResponse, Role } from './@types'
import { httpClient } from './lib/repository/httpClient'
import { HttpError } from './lib/repository/httpError'
 
export async function middleware(request: NextRequest) {
    const { pathname } = request.nextUrl
    const token = request.cookies.get('X-Access-Token')?.value
    const businessId = request.cookies.get('X-Business-Id')?.value
    const signInUrl = new URL('/sign-in', request.url)
    const businessesUrl = new URL('/businesses', request.url)
    let currentRole: Role | null = null

    if (!token) {
        if (pathname === '/sign-in') return NextResponse.next()
        return NextResponse.redirect(signInUrl)
    }

    if (token) {
        try {
            // let a = await fetch(`http://localhost:8080/auth/role?businessId=${businessId}`, {
            //     headers: {
            //         "Authorization": `Bearer ${token}`
            //     }
            // })
            // let b = await a.json()
            // console.log(b)
            const response = await httpClient.get<ApiResponse<Role>>(`/auth/role?businessId=${businessId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                // cache: "no-cache"
            })
            console.log(response)
            currentRole = response.data
            return NextResponse.next()
        } catch (error) {
            console.log(error instanceof Error ? error.message : "NCEQuhcquieh")

            if (error instanceof HttpError) {
                console.log(error instanceof HttpError ? error.data : "NCEQuhcquieh")
                // if (pathname === '/businesses') return NextResponse.next()
                // return NextResponse.redirect(businessesUrl)
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
        // '/users',
        // '/tasks',
        // '/leaves',
        // '/payrolls',
        // '/payslips/:path*',
        // '/roles',
        // '/permissions',
        // '/notifications',
    ],
}