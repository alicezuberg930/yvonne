import "./globals.css"
import { Geist, Geist_Mono, Inter } from "next/font/google"
import { cn } from "@/lib/utils"
import { TooltipProvider } from "@/components/ui/tooltip"
import { ThemeProvider } from "@/context/theme-provider"
import { DirectionProvider } from "@/context/direction-provider"
import { FontProvider } from "@/context/font-provider"
import { Suspense } from "react"
import { Toaster } from "@/components/ui/sonner"
import { AuthProvider } from "@/context/auth-provider"
import createSEO from "@/lib/seo"

const inter = Inter({ subsets: ['latin'], variable: '--font-sans' })

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
})

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
})

export const metadata = createSEO({})

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html
      lang="en"
      className={cn("h-full", "antialiased", geistSans.variable, geistMono.variable, "font-sans", inter.variable)}
    >
      <body className="min-h-full flex flex-col">
        <Suspense>
          <Toaster duration={3000} />
          <TooltipProvider>
            <ThemeProvider>
              <FontProvider>
                <DirectionProvider>
                  <AuthProvider>
                    {children}
                  </AuthProvider>
                </DirectionProvider>
              </FontProvider>
            </ThemeProvider>
          </TooltipProvider>
        </Suspense>
      </body>
    </html>
  )
}