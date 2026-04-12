import { AuthenticatedLayout } from "@/layout/authenticated-layout";

export default function layout({ children }: { children: React.ReactNode }) {
    return (
        <AuthenticatedLayout>
            {children}
        </AuthenticatedLayout>
    )
}