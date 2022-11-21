package es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses

data class PersonajesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)