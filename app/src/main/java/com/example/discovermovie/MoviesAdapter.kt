import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discovermovie.MainActivity
import com.example.discovermovie.R
import com.example.discovermovie.model.MovieModel

class MovieAdapter(val activity: MainActivity) : PagingDataAdapter<MovieModel, MovieAdapter.MovieViewHolder>(MovieModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieModel) {
            itemView.findViewById<TextView>(R.id.titleText).text = movie.title
            itemView.findViewById<TextView>(R.id.subtitleText).text = movie.overview
            Glide.with(itemView)
                .load("https://api.themoviedb.org"+movie.poster_path)
                .into(itemView.findViewById(R.id.imageView))
        }
    }

    class MovieModelDiffCallback : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

}