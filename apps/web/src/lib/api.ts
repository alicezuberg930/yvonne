import { httpClient } from "./httpClient";
import { ApiResponse } from "@/@types/response";

export const signIn = async (email: string, password: string) => {
    try {
        const response = await httpClient.post<ApiResponse<string>>('/auth/sign-in', {
            email, password
        });
        return response

        // const cookieStore = await cookies();
        // cookieStore.set('token', response.data, {
        //     httpOnly: true,
        //     secure: true, // use true in production (HTTPS)
        //     sameSite: 'strict',
        //     path: '/',
        //     maxAge: 60 * 60 * 24, // 1 day
        // });
    } catch (error) {
        console.error('Sign-in error:', error);
        throw error;
    }


};