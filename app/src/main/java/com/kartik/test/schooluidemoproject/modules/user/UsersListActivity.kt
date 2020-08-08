package com.kartik.test.schooluidemoproject.modules.user

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.ActivityUsersListBinding

class UsersListActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, UsersListActivity::class.java))
        }
    }

    lateinit var userListAdapter: UserListAdapter
    lateinit var binding: ActivityUsersListBinding
    var userList = ArrayList<UserListDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        initUsersList()
        userListAdapter = UserListAdapter(userList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = userListAdapter
    }

    fun backPressed(view: View) {
        finish()
    }

    private fun initUsersList() {
        userList.add(
            UserListDataModel(
                "Amit",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Shekhar",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Rahul",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Chaintanya",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Kartik",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Abhishek",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Astha",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Parul",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Ricky",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Honey",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Aastha",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Rami",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Romio",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Deepika",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Deepak",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Anksuh",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Happy",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Hani",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Amit",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Amit",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Amit",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "MUkta",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )
        userList.add(
            UserListDataModel(
                "Sherry",
                "3253453433",
                "+9134757345793",
                "Bangalore",
                "Karnataka"
            )
        )

    }
}