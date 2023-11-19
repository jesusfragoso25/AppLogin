package com.example.applogin.ui.adaptar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applogin.data.model.UserModel

class UserAdapter(private val userList: List<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>()  {
    private val tag = "UserAdapter"

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserModel) {
            itemView.findViewById<TextView>(R.id.tvUsername).text = user.user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(tag, "getItemCount: ${userList.size}")
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        holder.itemView.setOnClickListener {
            Log.d(tag, "onBindViewHolder: ${user.user}")
        }
    }
}