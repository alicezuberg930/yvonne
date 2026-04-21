import { Profile } from "@/@types/user"
import { httpClient } from "./httpClient"
import { ApiResponse } from "@/@types/response"
import { AuthValidators } from "@/validators/auth"
import { Role, Template } from "@/@types"
import { getCookie } from "../cookies"

export const signIn = async (data: AuthValidators.SignIn): Promise<ApiResponse<{ user: Profile, accessToken: string }>> => {
    return await httpClient.post<ApiResponse<{ user: Profile, accessToken: string }>>('/auth/sign-in', { ...data })
}

export const signUp = async (data: AuthValidators.SignUp): Promise<ApiResponse<Profile>> => {
    return await httpClient.post<ApiResponse<Profile>>('/auth/sign-in', { ...data })
}

export const profile = async (): Promise<ApiResponse<Profile>> => {
    return await httpClient.get<ApiResponse<Profile>>('/auth/me')
}

export const signOut = async (): Promise<ApiResponse<null>> => {
    return await httpClient.get<ApiResponse<null>>("/auth/sign-out")
}

export const getTemplates = async (): Promise<ApiResponse<Template[]>> => {
    return await httpClient.get<ApiResponse<Template[]>>("/templates")
}

export const getCurrentRole = async (): Promise<ApiResponse<any>> => {
    return await httpClient.get<ApiResponse<any>>(`/auth/role?businessId=${"rc34mbn1q176xmzhk0lxkt4q"}`)
}

export const uploadFile = async (file: File, subFolder?: string): Promise<ApiResponse<string>> => {
    const formData = new FormData()
    if (subFolder) formData.append('subFolder', `/${getCookie('X-Business-Id')}${subFolder}`)
    formData.append('file', file, file.name)
    return await httpClient.post<ApiResponse<string>>('/upload/single', formData)
}

export const uploadFiles = async (files: File[], subFolder?: string): Promise<ApiResponse<string[]>> => {
    const formData = new FormData()
    if (subFolder) formData.append('subFolder', `/${getCookie('X-Business-Id')}${subFolder}`)
    files.forEach(file => {
        formData.append("files[]", file, file.name)
    })
    return await httpClient.post<ApiResponse<string[]>>('/upload/multiple', formData)
}