import { Profile } from '@/@types/user'
import { httpClient } from './httpClient'
import { AuthValidators } from '@/validators/auth'
import { Campaign, Role, Template, ApiResponse, PaginatedApiResponse, Contact, Business } from '@/@types'
import { getCookie } from '../cookies'
import { TemplateValidators } from '@/validators/template'
import { CampaignValidators } from '@/validators/campaign'
import { BusinessValidators } from '@/validators/business'

// authentication
export const signIn = async (data: AuthValidators.SignIn): Promise<ApiResponse<{ user: Profile, accessToken: string }>> => {
    return await httpClient.post<ApiResponse<{ user: Profile, accessToken: string }>>('/auth/sign-in', { ...data })
}

export const signUp = async (data: AuthValidators.SignUp): Promise<ApiResponse<Profile>> => {
    return await httpClient.post<ApiResponse<Profile>>('/auth/sign-up', { ...data })
}

export const profile = async (): Promise<ApiResponse<Profile>> => {
    return await httpClient.get<ApiResponse<Profile>>('/auth/me')
}

export const signOut = async (): Promise<ApiResponse<null>> => {
    return await httpClient.get<ApiResponse<null>>('/auth/sign-out')
}

export const getCurrentRole = async (): Promise<ApiResponse<any>> => {
    return await httpClient.get<ApiResponse<any>>(`/auth/role?businessId=${'rc34mbn1q176xmzhk0lxkt4q'}`)
}

// file handling
export const uploadFile = async (file: File, subFolder?: string): Promise<ApiResponse<string>> => {
    const formData = new FormData()
    if (subFolder) formData.append('subFolder', `/${getCookie('X-Business-Id')}${subFolder}`)
    formData.append('file', file, file.name)
    return await httpClient.post<ApiResponse<string>>('/upload/single', formData)
}

export const uploadFiles = async (files: File[], subFolder?: string): Promise<ApiResponse<string[]>> => {
    const formData = new FormData()
    if (subFolder) formData.append('subFolder', `/${getCookie('X-Business-Id')}${subFolder}`)
    files.forEach(file => formData.append('files[]', file, file.name))
    return await httpClient.post<ApiResponse<string[]>>('/upload/multiple', formData)
}

// templates management
export const getTemplates = async (): Promise<ApiResponse<Template[]>> => {
    return await httpClient.get<ApiResponse<Template[]>>('/templates')
}

export const createTemplate = async (data: TemplateValidators.TemplateForm): Promise<ApiResponse<Template>> => {
    return await httpClient.post<ApiResponse<Template>>('/templates', { ...data })
}

export const updateTemplate = async (data: TemplateValidators.TemplateForm, id: string): Promise<ApiResponse<Template>> => {
    return await httpClient.put<ApiResponse<Template>>(`/templates/${id}`, { ...data })
}

export const deleteTemplate = async (id: string): Promise<ApiResponse<Template>> => {
    return await httpClient.delete<ApiResponse<Template>>(`/templates/${id}`)
}

// campaigns management
export const getCampaigns = async (): Promise<PaginatedApiResponse<Campaign[]>> => {
    return await httpClient.get<PaginatedApiResponse<Campaign[]>>('/campaigns')
}

export const createCampaign = async (data: CampaignValidators.CampaignForm): Promise<ApiResponse<Campaign>> => {
    return await httpClient.post<ApiResponse<Campaign>>('/campaigns', { ...data })
}

export const updateCampaign = async (data: CampaignValidators.CampaignForm, id: string): Promise<ApiResponse<Campaign>> => {
    return await httpClient.put<ApiResponse<Campaign>>(`/campaigns/${id}`, { ...data })
}

export const deleteCampaign = async (id: string): Promise<ApiResponse<Campaign>> => {
    return await httpClient.delete<ApiResponse<Campaign>>(`/campaigns/${id}`)
}

// contacts management
export const getContacts = async (): Promise<PaginatedApiResponse<Contact[]>> => {
    return await httpClient.get<PaginatedApiResponse<Contact[]>>('/contacts')
}

// business management
export const createBusiness = async (data: BusinessValidators.BusinessForm): Promise<ApiResponse<Business>> => {
    return await httpClient.post<ApiResponse<Business>>('/businesses', { ...data })
}
