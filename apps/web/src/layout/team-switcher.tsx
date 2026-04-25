'use client'
import * as React from 'react'
import { ChevronsUpDown, Command, Plus } from 'lucide-react'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuShortcut,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import {
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  useSidebar,
} from '@/components/ui/sidebar'
import { useAuth } from '@/context/auth-provider'
import Image from 'next/image'
import { Business, Role } from '@/@types'
// import { getCookie, setCookie } from '@/lib/cookies'
import { useRouter } from 'next/navigation'

export function TeamSwitcher() {
  const router = useRouter()
  const { user } = useAuth()
  const { isMobile } = useSidebar()
  const [activeTeam, setActiveTeam] = React.useState<Business & { role: Role } | undefined>(undefined)

  React.useEffect(() => {
    // setActiveTeam(user?.businesses.find(b => b.id === getCookie('X-Business-Id')))
  }, [user])

  return (
    <SidebarMenu>
      <SidebarMenuItem>
        <DropdownMenu>
          <DropdownMenuTrigger
            render={
              <SidebarMenuButton
                size='lg'
                className='data-[state=open]:bg-sidebar-accent data-[state=open]:text-sidebar-accent-foreground'
              >
                <div className='relative aspect-square size-8 items-center justify-center rounded-lg bg-sidebar-primary overflow-hidden'>
                  <Image fill src={activeTeam?.logoUrl ?? "/assets/placeholder.webp"} alt='active' />
                </div>
                <div className='grid flex-1 text-start text-sm leading-tight'>
                  <span className='truncate font-semibold'>
                    {activeTeam?.name}
                  </span>
                  <span className='truncate text-xs'>{activeTeam?.description}</span>
                </div>
                <ChevronsUpDown className='ms-auto' />
              </SidebarMenuButton>
            }
          />
          <DropdownMenuContent
            className='w-(--radix-dropdown-menu-trigger-width) min-w-56 rounded-lg'
            align='start'
            side={isMobile ? 'bottom' : 'right'}
            sideOffset={4}
          >
            <DropdownMenuGroup>
              <DropdownMenuLabel className='text-xs text-muted-foreground'>
                Teams
              </DropdownMenuLabel>
              {user?.businesses?.map((business, index) => (
                <DropdownMenuItem
                  key={business.name}
                  onClick={() => {
                    setActiveTeam(business)
                    // setCookie("X-Business-Id", business.id)
                    router.push('/dashboard')
                  }}
                  className='gap-2 p-2'
                >
                  <div className='flex size-6 items-center justify-center rounded-sm border'>
                    <Image alt={business.name} className='shrink-0' width={16} height={16} src={business.logoUrl ?? "/assets/placeholder.webp"} />
                  </div>
                  {business.name}
                  <DropdownMenuShortcut>⌘{index + 1}</DropdownMenuShortcut>
                </DropdownMenuItem>
              ))}
              <DropdownMenuSeparator />
              <DropdownMenuItem className='gap-2 p-2'>
                <div className='flex size-6 items-center justify-center rounded-md border bg-background'>
                  <Plus className='size-4' />
                </div>
                <div className='font-medium text-muted-foreground'>Add team</div>
              </DropdownMenuItem>
            </DropdownMenuGroup>
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarMenuItem>
    </SidebarMenu>
  )
}