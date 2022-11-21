package es.iesluiscarrillo.sw_rafaelbuitrago.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iesluiscarrillo.sw_rafaelbuitrago.R
import es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses.Result

class PersonajeAdapter(val personaje: List<Result>): RecyclerView.Adapter<PersonajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonajeViewHolder(layoutInflater.inflate(R.layout.item_personaje, parent, false))
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item:Result = personaje[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = personaje.size

}