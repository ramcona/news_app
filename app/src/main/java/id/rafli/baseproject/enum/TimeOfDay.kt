package id.rafli.baseproject.enum

enum class TimeOfDay(val greeting: String, val description: String) {
    MORNING("Selamat pagi", "Good morning!"),
    AFTERNOON("Selamat siang", "Good afternoon!"),
    EVENING("Selamat sore", "Good evening!"),
    NIGHT("Selamat malam", "Good night!")
}