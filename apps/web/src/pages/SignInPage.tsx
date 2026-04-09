"use client"
import * as React from "react"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import * as z from "zod"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    Field,
    FieldDescription,
    FieldError,
    FieldGroup,
    FieldLabel,
} from "@/components/ui/field"
import { FormProvider, RHFTextField } from "@/components/hook-form"
import { Typography } from "@/components/ui/typography"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { signIn } from "@/lib/api"
import { useSnackbar } from "@/components/snackbar"

const formSchema = z.object({
    email: z
        .string()
        .email("Invalid email address."),
    password: z
        .string()
        .min(6, "Password must be at least 6 characters.")
        .max(100, "Password must be at most 100 characters."),
})

export default function SignInPage() {
    const { enqueueSnackbar } = useSnackbar()
    const router = useRouter();
    const methods = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            email: "",
            password: "",
        },
    })

    const { handleSubmit, reset } = methods

    const onSubmit = async (data: z.infer<typeof formSchema>) => {
        try {
            const response = await signIn(data.email, data.password)
            enqueueSnackbar(response.message, { variant: "success" })
            router.push('/businesses')
        } catch (error: unknown) {
            const errorMessage = error instanceof Error ? error.message : "An error occurred"
            enqueueSnackbar(errorMessage, { variant: "error" })
        }
        // toast("You submitted the following values:", {
        //     description: (
        //         <pre className="mt-2 w-[320px] overflow-x-auto rounded-md bg-code p-4 text-code-foreground">
        //             <code>{JSON.stringify(data, null, 2)}</code>
        //         </pre>
        //     ),
        //     position: "bottom-right",
        //     classNames: {
        //         content: "flex flex-col gap-2",
        //     },
        //     style: {
        //         "--border-radius": "calc(var(--radius)  + 4px)",
        //     } as React.CSSProperties,
        // })
    }

    return (
        <div className="w-full h-screen content-center">
            <Card className="w-full sm:max-w-md mx-auto">
                <CardHeader className="text-center">
                    <CardTitle className="text-xl">Sign In</CardTitle>
                    <CardDescription>
                        Enter your credentials to start accessing businesses.
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <FormProvider id="login-form" methods={methods} onSubmit={handleSubmit(onSubmit)}>
                        <FieldGroup>
                            <RHFTextField
                                name="email"
                                type="email"
                                fieldLabel="Email"
                                placeholder="Type your email"
                            />

                            <RHFTextField
                                name="password"
                                type="password"
                                fieldLabel="Password"
                                placeholder="Type your password"
                            />
                        </FieldGroup>
                    </FormProvider>
                </CardContent>
                <CardFooter>
                    <Field orientation={"vertical"}>
                        <Field orientation={"vertical"} className="text-center">
                            <Typography>
                                <Link href={'/password/reset'}>Forgot Your Password?</Link>
                            </Typography>
                            <Typography>
                                <Link href={'/sign-up'}>Don't have an account? <b>Sign Up</b></Link>
                            </Typography>
                        </Field>

                        <Button type="submit" form="login-form">
                            Submit
                        </Button>
                    </Field>
                </CardFooter>
            </Card>
        </div>
    )
}