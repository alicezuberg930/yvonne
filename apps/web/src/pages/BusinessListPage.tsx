'use client'

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Plus } from "lucide-react"
import Image from "next/image"
import Link from "next/link"
import { httpClient } from "@/lib/httpClient"
import { useRouter } from "next/navigation"

const businesses = [
    {
        name: "Business 1",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        image: "https://www.vhv.rs/dpng/d/240-2401121_-anime-rem-re-zero-rem.png",
        role: 'Owner'
    },
    {
        name: "Business 2",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        image: "https://www.vhv.rs/dpng/d/240-2401121_-anime-rem-re-zero-rem.png",
        role: 'Member'
    },
    {
        name: "Business 3",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        image: "https://www.vhv.rs/dpng/d/240-2401121_-anime-rem-re-zero-rem.png",
        role: 'Developer'
    },
    {
        name: "Business 4",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        image: "https://www.vhv.rs/dpng/d/240-2401121_-anime-rem-re-zero-rem.png",
        role: 'Admin'
    },
    {
        name: "Business 1341345",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        image: "https://www.vhv.rs/dpng/d/240-2401121_-anime-rem-re-zero-rem.png",
        role: 'Owner'
    },
]

const roleColors: Record<string, string> = {
    'Owner': 'bg-blue-100 text-blue-800',
    'Member': 'bg-gray-100 text-gray-800',
    'Developer': 'bg-purple-100 text-purple-800',
    'Admin': 'bg-red-100 text-red-800',
}

export default function BusinessesPage() {
    const router = useRouter()

    const accessBusiness = async (businessId: string) => {
        try {
            const response = await httpClient.get(`/businesses/${businessId}/access`)
        } catch (error) {
            console.error("Error accessing business:", error)
        }
    }

    return (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {/* Business Cards */}
            {businesses.map((business, index) => (
                <Card
                    key={index} onClick={() => accessBusiness(business.name)}
                    className="pt-0 flex flex-col overflow-hidden hover:shadow-md transition-shadow"
                >
                    <div className="relative w-full h-48 bg-muted">
                        <Image
                            src={business.image}
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
                            <Badge className={roleColors[business.role as keyof typeof roleColors]}>
                                {business.role}
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

            {/* Create New Business Card */}
            <Card className="flex items-center justify-center min-h-64 cursor-pointer hover:shadow-md transition-shadow bg-muted/50">
                <Link href="/businesses/create" className="w-full h-full flex items-center justify-center">
                    <Button variant="ghost" size="lg" className="flex-col gap-2 h-auto py-8">
                        <Plus className="size-8" />
                        <span>Create New Business</span>
                    </Button>
                </Link>
            </Card>
        </div>
    )
}