package com.sdoward.preference.setup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.sdoward.preference.R

class UserAdapter(private val layoutInflater: LayoutInflater, private val users: List<SelectableUser>)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    fun getUsers(): List<SelectableUser> {
        return users
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.user_element, parent, false) as TextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.textView.text = user.name
        holder.textView.setOnClickListener {
            user.selected = !user.selected
            val backgroundColor = if (user.selected) R.color.colorAccent else android.R.color.background_light
            it.setBackgroundResource(backgroundColor)
        }
    }

    class ViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        val textView = view
    }
}