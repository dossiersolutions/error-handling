package no.dossier.libraries.errorhandling

abstract class ApplicationError {
    abstract val message: String
    abstract val causes: Map<String, ApplicationError>
    private val stackTrace: Array<StackTraceElement> = Thread.currentThread().stackTrace

    private fun getErrorTree(levels: List<Boolean> = emptyList()): String = buildString {
        val levelPrefix = levels.joinToString("") { if (it) "│   " else "    " }
        message.lines().forEach {
            if (it == message.lines().first())
                append("[${this@ApplicationError::class.simpleName}] $it\n")
            else {
                val localPrefix = if (causes.isEmpty()) "        " else "│       "
                append("$levelPrefix$localPrefix$it\n")
            }
        }
        causes.entries.forEach { entry ->
            val lastEntry = entry != causes.entries.last()
            val entryPrefix = levelPrefix + if (lastEntry) "├── " else "└── "
            append("$entryPrefix\uD83D\uDD34 ${entry.key} : ${entry.value.getErrorTree(levels + lastEntry)}")
        }
    }

    private fun getFullStackTrace(): String = buildString {
        stackTrace.forEach {
            append("${it}\n")
        }
    }

    private fun getMiniStackTrace(): String = buildString {
        stackTrace.filter {
            it.className.startsWith("no.dossier.")
                    && (it.fileName as String) !in listOf("ApplicationError.kt")
        }.forEachIndexed { index, it ->
            val symbol = if (index == 0) "┌" else "│"
            append("$symbol ${it}\n")
        }
    }

    override fun toString(): String = "\n\uD83D\uDD34 " + getErrorTree() + "mini trace:\n" + getMiniStackTrace()
}