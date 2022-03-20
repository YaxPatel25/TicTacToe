package com.example.tictactoe

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.tictactoe.PastGameListFragment.OnListFragmentInteractionListener
import com.example.tictactoe.listdata.PastGameListData.PastGame

import kotlinx.android.synthetic.main.fragment_pastgamelist.view.*
import org.w3c.dom.Text

/**
 * [RecyclerView.Adapter] that can display a [PastGame] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PastGameListRecyclerViewAdapter(
    private val mValues: List<PastGame>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PastGameListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PastGame
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pastgamelist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.who_won
        // to show the timestamp
        holder.mDateTime.text = item.timestamp

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.game_emoji
        val mContentView: TextView = mView.winner
        val mDateTime : TextView = mView.dateTime
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
