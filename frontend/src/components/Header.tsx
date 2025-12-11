import { Link } from '@tanstack/react-router'

export default function Header() {
  return (
    <header className="flex items-center bg-slate-950 p-4 text-gray-100 shadow-lg">
      <h1 className="text-xl font-semibold">
        <Link className="text-cyan-400 hover:text-cyan-300" to="/">
          Meno
        </Link>
      </h1>
    </header>
  )
}
