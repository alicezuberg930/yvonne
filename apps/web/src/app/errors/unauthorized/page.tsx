import { UnauthorisedError } from "@/features/errors/unauthorized-error";

export default function page() {
    return (<UnauthorisedError />)
}