'use client'
import { useRouter, useSearchParams } from "next/navigation"
import { useCallback, useMemo } from "react"

const useQueryState = () => {
    const router = useRouter()
    const searchParams = useSearchParams()

    const search: Record<string, string | undefined> = useMemo(() => {
        return Object.fromEntries(searchParams?.entries())
    }, [searchParams])

    const navigate = useCallback((records: Record<string, string | undefined>) => {
        const params = new URLSearchParams(searchParams?.toString())
        Object.entries(records).forEach(([key, value]) => {
            if (value === undefined || value === null || value === '') {
                params.delete(key)
            } else {
                params.set(key, value)
            }
        })
        router.push(`?${params.toString()}`, { scroll: false })
    }, [router])

    return { navigate, search }
}

export default useQueryState