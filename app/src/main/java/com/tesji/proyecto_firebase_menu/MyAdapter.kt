import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tesji.proyecto_firebase_menu.R
import com.tesji.proyecto_firebase_menu.model.User

class MyAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.nombre.text = user.nombre
        holder.artista.text = user.artista
        holder.album.text = user.album
        holder.genero.text = user.genero
        holder.ano.text = user.ano.toString()?:"Año no disponible"
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txtTrackQ)
        val artista: TextView = itemView.findViewById(R.id.txtArtistQ)
        val album: TextView = itemView.findViewById(R.id.txtAlbumQ)
        val genero: TextView = itemView.findViewById(R.id.txtGenderQ)
        val ano: TextView = itemView.findViewById(R.id.txtYearQ)
    }
}
