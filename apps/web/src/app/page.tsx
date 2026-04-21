'use client'
// import { CircleCheck, CpuIcon } from "lucide-react";
// import { cn } from "@/lib/utils";
// import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
// import { useState } from "react";

// const options = [
//     {
//         value: "4-core",
//         label: "4-core CPU",
//         description: "32 GB RAM",
//     },
//     {
//         value: "6-core",
//         label: "6-core CPU",
//         description: "32 GB RAM",
//     },
//     {
//         value: "8-core",
//         label: "8-core CPU",
//         description: "32 GB RAM",
//     },
// ];

// const RadioCardsDemo = () => {
//     const [value, setValue] = useState("")
//     console.log(value)
//     return (
//         <RadioGroup onChange={(e) => console.log(e)} className="grid w-full max-w-md grid-cols-3 gap-4" defaultValue={options[0].value}>
//             {options.map((option) => (
//                 <label key={option.value} className="cursor-pointer relative">
//                     <RadioGroupItem
//                         value={option.value}
//                         className="peer sr-only hidden"
//                         render={
//                             <>
//                                 <CircleCheck
//                                     className={cn(
//                                         "absolute top-0 right-0 h-6 w-6 translate-x-1/2 -translate-y-1/2 fill-blue-500 stroke-white text-primary",
//                                         "opacity-0 transition-opacity z-30",
//                                         "peer-data-checked:opacity-100"
//                                     )}
//                                 />
//                                 <div
//                                     className={cn(
//                                         "relative rounded-lg border p-4 transition-all",
//                                         "border-muted hover:border-blue-400",
//                                         // "peer-data-[checked]:border-blue-500",
//                                         "peer-data-checked:ring-2 peer-data-checked:ring-blue-500"
//                                     )}
//                                 >
//                                     <CpuIcon className="mb-2 text-muted-foreground" />
//                                     <div className="font-semibold">{option.label}</div>
//                                     <p className="text-xs text-muted-foreground">{option.description}</p>
//                                 </div>
//                             </>
//                         }
//                     />

//                 </label>
//             ))}
//         </RadioGroup>
//     );
// };

// export default RadioCardsDemo;
'use client'
import * as React from "react";
import { RadioGroup } from "@base-ui/react";
import { Radio } from "@base-ui/react";
import { cn } from "@/lib/utils"; // Standard Shadcn utility
import { CircleCheck, CpuIcon } from "lucide-react";

const options = [
    {
        value: "4-core",
        label: "4-core CPU",
        description: "32 GB RAM",
    },
    {
        value: "6-core",
        label: "6-core CPU",
        description: "32 GB RAM",
    },
    {
        value: "8-core",
        label: "8-core CPU",
        description: "32 GB RAM",
    },
];

export default function CpuSelector() {
    return (
        // <RadioGroup defaultValue="4-core" className="grid grid-cols-1 gap-4 sm:grid-cols-3">
        //     {options.map((option) => (
        //         <label key={option.value} className="cursor-pointer relative">
        //             <Radio.Root
        //                 value={option.value}
        //                 render={(props, state) => {
        //                     const { checked } = state;

        //                     return (
        //                         <div
        //                             {...props}
        //                             className={cn(
        //                                 "relative cursor-pointer rounded-lg border p-4 transition-all",
        //                                 "hover:border-blue-400",

        //                                 // ✅ state-driven styling
        //                                 checked && "border-blue-500 ring-2 ring-blue-500"
        //                             )}
        //                         >
        //                             {/* ✅ Check icon */}
        //                             <CircleCheck
        //                                 className={cn(
        //                                     "absolute right-2 top-2 h-5 w-5 text-blue-500 transition-opacity",
        //                                     checked ? "opacity-100" : "opacity-0"
        //                                 )}
        //                             />

        //                             <CpuIcon className="mb-2 text-muted-foreground" />
        //                             <div className="font-semibold">{option.label}</div>
        //                             <p className="text-xs text-muted-foreground">
        //                                 {option.description}
        //                             </p>
        //                         </div>
        //                     );
        //                 }}

        //             // className="group peer absolute opacity-0"
        //             />
        //             {/* <CircleCheck
        //                 className={cn(
        //                     "absolute right-2 top-2 h-5 w-5 text-blue-500 transition-opacity",
        //                     "opacity-0",
        //                     "peer-data-checked:opacity-100"
        //                 )}
        //             />
        //             <div
        //                 className={cn(
        //                     "relative rounded-lg border p-4 transition-all",
        //                     "border-muted hover:border-blue-400",

        //                     // ✅ MUST target exact attribute value
        //                     "peer-data-checked:border-blue-500",
        //                     "peer-data-checked:ring-2 peer-data-checked:ring-blue-500"
        //                 )}
        //             >
        //                 <CpuIcon className="mb-2 text-muted-foreground" />
        //                 <div className="font-semibold">{option.label}</div>
        //                 <p className="text-xs text-muted-foreground">
        //                     {option.description}
        //                 </p>
        //             </div> */}
        //         </label>
        //     ))}
        // </RadioGroup>
        <div className="p-12">
        </div>
    );
}
