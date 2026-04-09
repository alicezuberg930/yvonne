import { AuthenticatedLayout } from "@/components/layout/authenticated-layout";

export default function layout({ children }: { children: React.ReactNode }) {
    return (
        <AuthenticatedLayout>
            {children}
        </AuthenticatedLayout>
    )
}