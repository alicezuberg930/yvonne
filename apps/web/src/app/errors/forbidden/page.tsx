import { ForbiddenError } from "@/features/errors/forbidden";

export default function page() {
    return (<ForbiddenError />)
}