'use client'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Plus } from "lucide-react"
import Image from "next/image"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { Typography } from "@/components/ui/typography"
import { setCookie } from "@/lib/cookies"
import { useAuth } from "@/context/auth-provider"
import { NewBusinessDialog } from "./components/new-business-dialog"
import useDialogState from "@/hooks/use-dialog-state"

const roleColors: Record<string, string> = {
    'Owner': 'bg-blue-100 text-blue-800',
    'Member': 'bg-gray-100 text-gray-800',
    'Developer': 'bg-purple-100 text-purple-800',
    'Admin': 'bg-red-100 text-red-800',
}

export default function BusinessesPage() {
    const router = useRouter()
    const { user, getCurrentRole } = useAuth()
    const [open, setOpen] = useDialogState<"add">(null)

    const accessBusiness = async (businessId: string) => {
        setCookie("X-Business-Id", businessId)
        getCurrentRole(businessId)
        router.push('/dashboard')
    }

    return (
        <div className="p-4 md:p-8">
            <Typography variant={'h5'}>Here is a list of your businesses</Typography>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-6">
                {user?.businesses?.map((business, index) => (
                    <Card
                        key={index} onClick={() => accessBusiness(business.id)}
                        className="pt-0 flex flex-col overflow-hidden hover:shadow-md transition-shadow"
                    >
                        <div className="relative w-full h-48 bg-muted">
                            <Image
                                src={business.logoUrl ?? "./assets/placeholder.webp"}
                                alt={business.name}
                                fill
                                className="object-cover"
                            />
                        </div>
                        <CardHeader>
                            <div className="flex items-start justify-between gap-2">
                                <div className="flex-1">
                                    <CardTitle>{business.name}</CardTitle>
                                </div>
                                <Badge className={roleColors[business.role?.name!]}>
                                    {business.role?.name}
                                </Badge>
                            </div>
                        </CardHeader>
                        <CardContent className="flex-1">
                            <CardDescription className="line-clamp-3">
                                {business.description}
                            </CardDescription>
                        </CardContent>
                    </Card>
                ))}

                <Card className="min-h-64 cursor-pointer bg-muted/50 p-0">
                    <Button onClick={() => setOpen("add")} variant="ghost" className="w-full h-full flex items-center justify-center">
                        <div className="flex flex-col gap-3 items-center">
                            <Plus className="size-8" />
                            <span>Create New Business</span>
                        </div>
                    </Button>
                </Card>
            </div>

            <NewBusinessDialog
                open={open === "add"}
                onOpenChange={() => setOpen("add")}
            />
        </div>
    )
}