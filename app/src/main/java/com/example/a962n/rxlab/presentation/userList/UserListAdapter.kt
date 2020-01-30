package com.example.a962n.rxlab.presentation.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a962n.rxlab.data.UserEntity
import com.example.a962n.rxlab.databinding.ItemUserListBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {


    private val list: MutableList<Item> = mutableListOf()

    fun refreshItems(items: List<Item>) {
        list.removeAll { true }
        list.addAll(items)
        notifyDataSetChanged()
    }

    data class Item(val id: String, val first: String, val last: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserListBinding.inflate(inflater, parent, false)
        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.binding.item = list[position]
    }

    inner class UserListViewHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}

fun UserEntity.toUserListAdapterItem(): UserListAdapter.Item {
    val id = "id:${id}"
    val first = firstName?.let { "firstName:$it" } ?: "unknown"
    val last = lastName?.let {
        "firstName:$it"
    } ?: "unknown"
    return UserListAdapter.Item(id, first, last)
}