package com.example.androidlabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class CityFragment : Fragment() {

    private lateinit var city: City
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var favoriteCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city = arguments?.getParcelable("city") ?: City()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)

        titleField = view.findViewById(R.id.city_title) as EditText
        dateButton = view.findViewById(R.id.city_date) as Button
        favoriteCheckBox = view.findViewById(R.id.city_favorite) as CheckBox
        titleField.setText(city.title)

        dateButton.apply {
            text = city.date.toString()
            isEnabled = false
        }
        favoriteCheckBox.isChecked = city.isFavorite

        return view
    }

    override fun onStart() {
        super.onStart()
        favoriteCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                city.isFavorite = isChecked
            }
        }
    }

    companion object {
        fun newInstance(city: City): CityFragment {
            return CityFragment().apply {
                arguments = bundleOf("city" to city)
            }
        }
    }
}