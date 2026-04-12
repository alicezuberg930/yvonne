import { Settings } from '@/features/settings'

export default function layout({ children }: { children: React.ReactNode }) {
    return (
        <Settings>
            {children}
        </Settings>
    )
}