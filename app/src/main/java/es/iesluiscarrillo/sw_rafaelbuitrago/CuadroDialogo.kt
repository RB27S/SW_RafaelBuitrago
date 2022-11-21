package es.iesluiscarrillo.sw_rafaelbuitrago

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

//Clase que crea el cuadro de dialogo a mostrar cuando se pulse el boton para guardar la lista de personajes
class CuadroDialogo: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val title = "Guardar"
            val content = "¿Desea guardar el listado?"
            val builder: AlertDialog.Builder=AlertDialog.Builder(requireActivity())
            builder.setTitle(title).setMessage(content)
                .setPositiveButton(android.R.string.ok)  {dialog, which ->
                    Toast.makeText(activity, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(android.R.string.cancel) {dialog, which ->
                    Toast.makeText(activity, "Ha cancelado la acción", Toast.LENGTH_SHORT).show()
                }
            //Devuelve un AlertDialog
            //tal y como lo hemos definido en el builder
            return builder.create()} ?: throw IllegalStateException("El fragmento no está asociado a ninguna actividad")
    }
}