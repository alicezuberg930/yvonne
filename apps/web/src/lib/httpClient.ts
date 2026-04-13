import { HttpError } from "./httpError"
import { InterceptorManager } from "./interceptor"

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL || 'http://localhost:8080/api/v1'

type FetchOptions = RequestInit

export class HttpClient {
    interceptors = {
        request: new InterceptorManager<FetchOptions>(),
        response: new InterceptorManager<Error | HttpError>(),
    }

    private async fetchJson<T>(url: string, options: FetchOptions = {}): Promise<T> {
        let config: FetchOptions = {
            ...options,
            headers: {
                'Content-Type': 'application/json',
            }
        }
        for (const { onFulfilled } of this.interceptors.request.getHandlers()) {
            if (onFulfilled) config = await onFulfilled(config)
        }
        try {
            const response = await fetch(url, config)
            if (!response.ok) {
                const text = await response.text()
                let data: unknown
                try {
                    data = text ? JSON.parse(text) : null
                } catch {
                    data = text
                }
                const error = new HttpError(response.status, 'Request Failed', data)
                // if error is due to authentication, handle it here (e.g., redirect to login)
                for (const { onRejected } of this.interceptors.response.getHandlers()) {
                    if (onRejected) onRejected(error)
                }
                throw error
            }

            let data = await response.json()
            for (const { onFulfilled } of this.interceptors.response.getHandlers()) {
                if (onFulfilled) data = await onFulfilled(data)
            }
            return data as T
        } catch (error: unknown) {
            // Handle Network Errors or re-thrown Interceptor errors
            for (const { onRejected } of this.interceptors.response.getHandlers()) {
                if (onRejected) onRejected(error)
            }
            throw error
        }
    }

    get<T>(endpoint: string, options?: FetchOptions) {
        return this.fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'GET',
            credentials: 'include',
            ...options,
        })
    }

    post<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return this.fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'POST',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    }

    put<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return this.fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'PUT',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    }

    patch<T>(endpoint: string, body?: any, options?: FetchOptions) {
        return this.fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'PATCH',
            credentials: 'include',
            body: body ? JSON.stringify(body) : undefined,
            ...options,
        })
    }

    delete<T>(endpoint: string, options?: FetchOptions) {
        return this.fetchJson<T>(`${BASE_URL}${endpoint}`, {
            method: 'DELETE',
            credentials: 'include',
            ...options,
        })
    }
}

export const httpClient = new HttpClient()