package ro.pub.cs.systems.eim.lab10.googlemapplaces.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ro.pub.cs.systems.eim.lab10.R
import ro.pub.cs.systems.eim.lab10.googlemapplaces.model.Place

class PlacesAdapter(context: Context, private val data: List<Place>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private class PlaceViewHolder {
        var placeInformationTextView: TextView? = null
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val place = getItem(position) as Place

        val view: View
        val placeViewHolder: PlaceViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.place, parent, false)
            placeViewHolder = PlaceViewHolder()
            placeViewHolder.placeInformationTextView = view.findViewById(R.id.place_information_text_view)
            view.tag = placeViewHolder
        } else {
            view = convertView
            placeViewHolder = view.tag as PlaceViewHolder
        }

        placeViewHolder.placeInformationTextView?.text = place.toString()

        return view
    }
}
