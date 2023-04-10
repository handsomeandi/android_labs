package com.example.androidlabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "cityListFragment"

class CityListFragment : Fragment() {

    private lateinit var cityRecyclerView: RecyclerView
    private var adapter: CityAdapter? = null

    private val cityListViewModel: CityListViewModel by lazy {
        ViewModelProviders.of(this)[CityListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)

        cityRecyclerView =
            view.findViewById(R.id.city_recycler_view) as RecyclerView
        cityRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = CityAdapter(emptyList())
        cityRecyclerView.adapter = adapter
        cityListViewModel.cityListLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }

        return view
    }

    private fun updateUI(cities: List<City>) {
        adapter?.setItems(cities)
    }

    private inner class CityHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var city: City

        private val titleTextView: TextView = itemView.findViewById(R.id.city_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.city_date)
        private val isFavorite: ImageView = itemView.findViewById(R.id.is_favorite)
        private val image: ImageView = itemView.findViewById(R.id.icon)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(city: City, onFavoriteClick: () -> Unit) {
            this.city = city
            titleTextView.text = this.city.title
            dateTextView.text = this.city.date.toString()
            isFavorite.setImageResource(if (city.isFavorite) R.drawable.ic_like else R.drawable.ic_heart_broken)
            isFavorite.setOnClickListener {
                onFavoriteClick()
            }
            image.setImageResource(city.img ?: R.drawable.ic_city)

        }

        override fun onClick(v: View) {
            val fragment = CityFragment.newInstance(city)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private inner class CityAdapter(var citys: List<City>) : RecyclerView.Adapter<CityHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CityHolder {
            val view = layoutInflater.inflate(R.layout.list_item_city, parent, false)
            return CityHolder(view)
        }

        override fun onBindViewHolder(holder: CityHolder, position: Int) {
            val city = citys[position]
            holder.bind(city) {
                city.isFavorite = !city.isFavorite
                notifyDataSetChanged()
            }
        }

        override fun getItemCount() = citys.size

        fun setItems(items: List<City>) {
            citys = items
            notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): CityListFragment {
            return CityListFragment()
        }
    }
}