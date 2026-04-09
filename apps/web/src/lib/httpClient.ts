const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL || 'http://localhost:8080/api/v1'

type RequestOptions = {
    headers?: Record<string, string>
    credentials?: RequestCredentials
    [key: string]: any
}

type FetchOptions = RequestInit & {
    headers?: Record<string, string>
}

async function fetchJson<T>(
    url: string,
    options: FetchOptions = {}
): Promise<T> {
    const { headers = {}, ...rest } = options

    try {
        const response = await fetch(url, {
            ...rest,
            headers: {
                'Content-Type': 'application/json',
                ...headers,
            },
        })

        if (!response.ok) {
            const error = await response.text()
            throw new Error(`HTTP ${response.status}: ${error}`)
        }

        return response.json() as Promise<T>
    } catch (error) {
        console.error(`Fetch error for ${url}:`, error)
        throw error
    }
}

export const httpClient = {
    get<T>(endpoint: string, options?: FetchOptions) {
        return fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'GET',
            credentials: 'include',
            ...options,
        })
    },

    post<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'POST',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    },

    put<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'PUT',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    },

    patch<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'PATCH',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    },

    delete<T>(endpoint: string, options?: FetchOptions) {
        return fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'DELETE',
            credentials: 'include',
            ...options,
        })
    },
}
