export type ApiResponse<T> = {
    timestamp: string
    statusCode: number
    data: T
    message: string
}