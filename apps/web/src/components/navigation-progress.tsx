'use client';
import { useEffect, useRef } from 'react';
import { usePathname } from 'next/navigation';
import LoadingBar, { type LoadingBarRef } from 'react-top-loading-bar';

export function NavigationProgress() {
  const ref = useRef<LoadingBarRef>(null);
  const pathname = usePathname();
  const prev = useRef<string | null>(null);
  const current = pathname;

  useEffect(() => {
    if (prev.current && prev.current !== current) {
      ref.current?.continuousStart();
      // simulate loading end (since Next has no "done" event)
      setTimeout(() => {
        ref.current?.complete();
      }, 300);
    }
    prev.current = current;
  }, [current]);

  return (
    <LoadingBar
      color="var(--muted-foreground)"
      ref={ref}
      shadow
      height={2}
    />
  );
}