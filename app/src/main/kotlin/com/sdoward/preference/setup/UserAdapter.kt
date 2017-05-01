package com.sdoward.preference.setup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.sdoward.preference.R
import com.sdoward.preference.signin.User

class UserAdapter(private val layoutInflater: LayoutInflater, private val users: List<User>)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.user_element, parent, false) as TextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = users[position].name
    }

    class ViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        var textView = view
    }
}