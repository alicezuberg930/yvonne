'use client'
import { useRouter, useSearchParams } from "next/navigation"

const useQueryState = () => {
    const router = useRouter()
    const searchParams = useSearchParams()

    const setQuery = (updates: Record<string, string | undefined>) => {
        const params = new URLSearchParams(searchParams?.toString())

        Object.entries(updates).forEach(([key, value]) => {
            if (!value) params.delete(key)
            else params.set(key, value)
        })

        router.push(`?${params.toString()}`)
    }

    return { searchParams, setQuery }
}

export default useQueryState