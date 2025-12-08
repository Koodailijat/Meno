import { createFileRoute } from '@tanstack/react-router'
import { Car, LayoutDashboard, PenLine, Users } from 'lucide-react'

import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { config } from '@/config'

export const Route = createFileRoute('/')({ component: Home })

function Home() {
  const features = [
    {
      description:
        'Track monthly budgets, compare with previous months, and visualize spending patterns',
      icon: <LayoutDashboard className="h-8 w-8" />,
      title: 'Dashboard',
    },
    {
      description:
        'Calculate fuel costs and tax deductions based on the Finnish tax system',
      icon: <Car className="h-8 w-8" />,
      title: 'Gas Calculator',
    },
    {
      description:
        'Adjust budget allocations and see how changes affect your financial goals',
      icon: <PenLine className="h-8 w-8" />,
      title: 'Budget Editor',
    },
    {
      description:
        'Create shared budgets with friends for trips and group expenses',
      icon: <Users className="h-8 w-8" />,
      title: 'Shared Costs',
    },
  ]

  return (
    <div className="min-h-screen bg-background">
      <div className="mx-auto max-w-7xl px-6 py-20">
        <div className="mb-16 text-center">
          <h1 className="mb-4 text-6xl font-bold tracking-tight">
            Welcome to <span className="text-primary">Meno</span>
          </h1>
          <p className="mx-auto max-w-2xl text-xl text-muted-foreground">
            Take control of your personal finances with smart budgeting and
            expense tracking
          </p>
        </div>

        <div className="mb-12 mx-auto max-w-3xl grid grid-cols-1 gap-6 md:grid-cols-2">
          {features.map((feature) => (
            <Card
              className="transition-all duration-300 hover:border-primary/50 hover:shadow-lg hover:shadow-primary/10"
              key={feature.title}
            >
              <CardHeader>
                <div className="mb-2 text-primary">{feature.icon}</div>
                <CardTitle>{feature.title}</CardTitle>
              </CardHeader>
              <CardContent>
                <CardDescription>{feature.description}</CardDescription>
              </CardContent>
            </Card>
          ))}
        </div>

        <div className="text-center">
          <Button
            className="shadow-lg shadow-primary/50"
            onClick={() => {
              window.location.href = `${config.apiUrl}/login`
            }}
            size="lg"
            variant="default"
          >
            Get Started
          </Button>
        </div>
      </div>
    </div>
  )
}
