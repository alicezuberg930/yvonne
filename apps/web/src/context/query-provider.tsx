'use client'
import { defaultShouldDehydrateQuery, QueryCache, QueryClient, QueryClientProvider as QueryProvider } from '@tanstack/react-query'
import { useRouter } from 'next/navigation'
import { toast } from 'sonner'

export const createQueryClient = () => new QueryClient({
    defaultOptions: {
        queries: {
            retry: (failureCount, error) => {
                console.log({ failureCount, error })

                if (failureCount > 3) return false

                return !(
                    error instanceof Error && [401, 403].includes(parseInt(error.message) ?? 0)
                )
            },
            // With SSR, we usually want to set some default staleTime
            // above 0 to avoid refetching immediately on the client
            staleTime: 60 * 60 * 1000, // 60 minutes
            gcTime: 1000 * 60 * 60 * 1, // 1 hours (must be >= maxAge for persister)
            retryDelay: (attemptIndex) => Math.min(1000 * 2 ** attemptIndex, 30000),
            refetchOnWindowFocus: false, // Disable refetch on window focus
            refetchOnReconnect: true, // Refetch when internet reconnects
            refetchOnMount: true, // Refetch when component mounts if data is stale
        },
        mutations: {
            retry: 1, // Retry mutations once
            retryDelay: 3000, // Wait 3 seconds before retry
            onError: (error: unknown) => {
                if (error instanceof Error) {
                    toast.error(error.message)
                }
            },
        },
        dehydrate: {
            shouldDehydrateQuery: (query) => defaultShouldDehydrateQuery(query) || query.state.status === 'pending',
        },
        hydrate: {},
    },
    queryCache: new QueryCache({
        onError: (error: unknown) => {
            const router = useRouter()
            // 401 error
            toast.error('Session expired')
            router.replace('/sign-in')
        }
    }),
})

let clientQueryClientSingleton: QueryClient | undefined = undefined

export const getQueryClient = () => {
    // Server: always return a new query client
    if (typeof globalThis === 'undefined') return createQueryClient()
    // Browser: reuse singleton to avoid creating new clients on every request
    clientQueryClientSingleton ??= createQueryClient();
    return clientQueryClientSingleton
}

export const QueryClientProvider = ({ children }: { children: React.ReactNode }) => {
    return (
        <QueryProvider client={getQueryClient()}>
            {children}
        </QueryProvider>
    )
}