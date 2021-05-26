import no.dossier.libraries.errorhandling.InternalError

fun defineApplicationError() {
    class ApplicationError(
        override val message: String = "",
        override val causes: Map<String, InternalError> = emptyMap()
    ) : InternalError()

    val error = ApplicationError()
}