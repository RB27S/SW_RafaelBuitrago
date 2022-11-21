package es.iesluiscarrillo.sw_rafaelbuitrago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.iesluiscarrillo.sw_rafaelbuitrago.adapter.PersonajeAdapter
import es.iesluiscarrillo.sw_rafaelbuitrago.databinding.ActivityMainBinding
import es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses.PersonajesResponse
import es.iesluiscarrillo.sw_rafaelbuitrago.dataclasses.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //ATRIBUTOS Y VARIABLES PRIVADAS DE LA CLASE
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonajeAdapter
    private val listaPersonajes = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()  //Inicialización del recyclerView

        binding.bVerListado.setOnClickListener { BuscarPersonajes() }   //Se establece el "click listener" para el botón "bVerListado"

        //Se establece el "click listener" para el botón "bGuardarListado" que muestra el cuadro de dialogo creado en la clase "CuadroDialogo"
        binding.bGuardarListado.setOnClickListener {
            val cuadro = CuadroDialogo()
            cuadro.show(supportFragmentManager, "Guardar")
        }
    }

    //Función de inicialización de recyclerView
    private fun initRecyclerView() {
        adapter = PersonajeAdapter(listaPersonajes)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    //Función para la solicitud de los datos a la API haciendo uso de retrofit2
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/people/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Función que hace la búsqueda de los personajes de Star Wars por medio de la API y el método getRetrofit
    private fun BuscarPersonajes() {
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<PersonajesResponse> = getRetrofit().create(APIService::class.java).getPersonajes("")  //el url en el método "getPersonajes" es una cadena vacía ya que no es necesario añadir nada al url base para realizar la petición a la API
            val characters = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val personajes = characters?.results ?: emptyList() //Variable que va a almacenar una lista con los resultados obtenidos de la API

                    listaPersonajes.clear() //Se limpia la lista de personajes
                    listaPersonajes.addAll(personajes)  //Se añaden los personajes obtenidos de la API

                    adapter.notifyDataSetChanged()  //Se notifica del cambio de datos
                } else {
                    showError() //En caso de que la llamada no tenga éxito se muestra el mensaje de error
                }
            }
        }
    }

    //Función que muestra un Toast con un mensaje de error
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}