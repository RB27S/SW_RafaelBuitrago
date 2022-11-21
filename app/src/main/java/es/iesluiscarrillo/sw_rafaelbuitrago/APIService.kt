package es.iesluiscarrillo.sw_rafaelbuitrago

import es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses.PersonajesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPersonajes(@Url url: String): Response<PersonajesResponse>
}