import { Profile } from "@/@types/user";
import { httpClient } from "./httpClient";
import { ApiResponse } from "@/@types/response";
import { AuthValidators } from "@/validators/auth";
import { Role } from "@/@types";

export const signIn = async (data: AuthValidators.SignIn): Promise<ApiResponse<{ user: Profile, accessToken: string }>> => {
    return await httpClient.post<ApiResponse<{ user: Profile, accessToken: string }>>('/auth/sign-in', { ...data })
};

export const signUp = async (data: AuthValidators.SignUp): Promise<ApiResponse<Profile>> => {
    return await httpClient.post<ApiResponse<Profile>>('/auth/sign-in', { ...data })
};

export const profile = async (): Promise<ApiResponse<Profile>> => {
    return await httpClient.get<ApiResponse<Profile>>('/auth/me')
};

export const signOut = async (): Promise<void> => {

}

export const getCurrentRole = async (): Promise<ApiResponse<any>> => {
    return await httpClient.get<ApiResponse<any>>(`/auth/role?businessId=${"rc34mbn1q176xmzhk0lxkt4q"}`)
}