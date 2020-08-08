package com.kartik.test.schooluidemoproject.modules.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kartik.test.schooluidemoproject.databinding.UsersListItemBinding

class UserListAdapter(private val arrayList: ArrayList<UserListDataModel>) :
    RecyclerView.Adapter<UserListAdapter.UsersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        return UsersListViewHolder(
            UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.usersListItemBinding.name.text = arrayList[position].name
        holder.usersListItemBinding.phoneNumber.text = arrayList[position].phoneNumber
        holder.usersListItemBinding.address.text =
            arrayList[position].city + ", " + arrayList[position].state
    }

    class UsersListViewHolder(val usersListItemBinding: UsersListItemBinding) :
        RecyclerView.ViewHolder(usersListItemBinding.root)

}