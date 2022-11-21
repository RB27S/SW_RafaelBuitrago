package es.iesluiscarrillo.sw_rafaelbuitrago.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.iesluiscarrillo.sw_rafaelbuitrago.databinding.ItemPersonajeBinding
import es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses.Result

class PersonajeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonajeBinding.bind(view)
    fun bind(personaje: Result) {
        binding.tvNombre.text = personaje.name  //Se actualiza el TextView "tvNombre" con el nombre del personaje obtenido de la API
        binding.tvAltura.text = personaje.height    //Se actualiza el TextView "tvAltura" con la altura del personaje obtenida de la API
        binding.tvNPeliculas.text = personaje.films.count().toString()  //Se actualiza el TextView "tvNPeliculas" con el número del personaje obtenido de la API
    }
}