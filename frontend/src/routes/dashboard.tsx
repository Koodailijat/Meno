import { createFileRoute, redirect, useNavigate } from '@tanstack/react-router'

import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'

export const Route = createFileRoute('/dashboard')({
  beforeLoad: () => {
    // Check if JWT token exists in localStorage
    const token = localStorage.getItem('jwt')
    if (!token) {
      // Redirect to home if not authenticated
      throw redirect({
        to: '/',
      })
    }
  },
  component: Dashboard,
})

function Dashboard() {
  const navigate = useNavigate()

  const handleLogout = () => {
    // Remove JWT token from localStorage
    localStorage.removeItem('jwt')
    // Redirect to home page
    navigate({ to: '/' })
  }

  return (
    <div className="min-h-screen bg-background p-6">
      <div className="mx-auto max-w-7xl">
        <div className="mb-6 flex justify-end">
          <Button onClick={handleLogout} variant="outline">
            Logout
          </Button>
        </div>
        <Card>
          <CardHeader>
            <CardTitle className="text-3xl">Dashboard</CardTitle>
            <CardDescription>
              Welcome to your budget dashboard
            </CardDescription>
          </CardHeader>
          <CardContent>
            <p className="text-muted-foreground">Content coming soon...</p>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
