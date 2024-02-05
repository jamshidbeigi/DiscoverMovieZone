import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discovermovie.ui.main.MainActivity
import com.example.discovermovie.R
import com.example.discovermovie.data.model.MovieModel

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

        fun bind(movie: MovieModel?) {

            itemView.setOnClickListener{}

            itemView.findViewById<TextView>(R.id.titleText).text = movie!!.title
            itemView.findViewById<TextView>(R.id.subtitleText).text = movie.overview
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(itemView.findViewById(R.id.imageView))

            if(movie.release_date.length>=5) {
                val onlyDate = movie.release_date.substring(0, 4)
                itemView.findViewById<TextView>(R.id.dateText).text = onlyDate
            }

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