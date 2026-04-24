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
import { Editor } from '@tinymce/tinymce-react';
import { Calendar } from "@/components/ui/calendar";
import { DatePicker } from "@/components/date-picker";
import z from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { FormProvider, RHFRangeDatePicker, RHFSingleDatePicker } from "@/components/hook-form";
import { FieldGroup } from "@/components/ui/field";
import { DayPicker, DateRange, type DayPickerProps } from 'react-day-picker'

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
    const signUpSchema = z.object({
        dateRange: z.object({
            from: z.date().optional(),
            to: z.date().optional()
        }).refine(
            (val) => val.from !== undefined && val.to !== undefined,
            { message: "Select a start and end date" }
        )
    })

    const form = useForm<z.infer<typeof signUpSchema>>({
        resolver: zodResolver(signUpSchema),
        defaultValues: {
            dateRange: {
                from: undefined,
                to: undefined,
            },
        },
    })

    const { handleSubmit } = form

    function onSubmit(data: z.infer<typeof signUpSchema>) {
        // eslint-disable-next-line no-console
        console.log(data)

    }

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
        // <Editor
        //     apiKey='syztc4nmwzj649hdcz869ptfjxpn6l03x2db88c0pn77zv3i'
        //     init={{
        //         plugins: [
        //             // Core editing features
        //             'anchor', 'autolink', 'charmap', 'codesample', 'emoticons', 'link', 'lists', 'media', 'searchreplace', 'table', 'visualblocks', 'wordcount',
        //             // Your account includes a free trial of TinyMCE premium features
        //             // Try the most popular premium features until May 5, 2026:
        //             'checklist', 'mediaembed', 'casechange', 'formatpainter', 'pageembed', 'a11ychecker', 'tinymcespellchecker', 'permanentpen', 'powerpaste', 'advtable', 'advcode', 'advtemplate', 'tinymceai', 'uploadcare', 'mentions', 'tinycomments', 'tableofcontents', 'footnotes', 'mergetags', 'autocorrect', 'typography', 'inlinecss', 'markdown', 'importword', 'exportword', 'exportpdf'
        //         ],
        //         toolbar: 'undo redo | tinymceai-chat tinymceai-quickactions tinymceai-review | blocks fontfamily fontsize | bold italic underline strikethrough | link media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography uploadcare | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
        //         tinycomments_mode: 'embedded',
        //         tinycomments_author: 'Author name',
        //         mergetags_list: [
        //             { value: 'First.Name', title: 'First Name' },
        //             { value: 'Email', title: 'Email' },
        //         ],
        //         tinymceai_token_provider: async () => {
        //             await fetch(`https://demo.api.tiny.cloud/1/syztc4nmwzj649hdcz869ptfjxpn6l03x2db88c0pn77zv3i/auth/random`, { method: "POST", credentials: "include" });
        //             return { token: await fetch(`https://demo.api.tiny.cloud/1/syztc4nmwzj649hdcz869ptfjxpn6l03x2db88c0pn77zv3i/jwt/tinymceai`, { credentials: "include" }).then(r => r.text()) };
        //         },
        //         uploadcare_public_key: 'bb4aa260efed9c599ec1',
        //     }}
        //     initialValue="Welcome to TinyMCE!"
        // />
        <FormProvider methods={form} onSubmit={handleSubmit(onSubmit)}>
            <FieldGroup>
                {/* <RHFSingleDatePicker
                    name="date"
                    fieldLabel="Full name"
                    placeholder="Hatsune Miku"
                /> */}
                <RHFRangeDatePicker
                    name="dateRange"
                    fieldLabel="Full name"
                    placeholder="Hatsune Miku"
                />
            </FieldGroup>
            <button type="submit">eqrfeqr </button>
        </FormProvider>
    );
}
