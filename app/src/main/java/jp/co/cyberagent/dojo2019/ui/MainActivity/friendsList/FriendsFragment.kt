package jp.co.cyberagent.dojo2019.ui.MainActivity.friendsList

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.db.User
import jp.co.cyberagent.dojo2019.data.repository.UserViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.*


class FriendsFragment : Fragment() , CoroutineScope by MainScope(){

    public lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //dbインスタンス化
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val addbutton = view.findViewById<Button>(R.id.addbutton)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val mAdapter = FriendsViewAdapter(view.context)
        mRecyclerView.apply{
            //addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = mAdapter
        }

        userViewModel.allUser.observe(this, Observer { user ->
            user?.let {
                mAdapter.setUsers(it)
            }
        })

        val a = activity!!.intent.data
        val uri = Uri.parse(a.toString())
        val NAME = uri.getQueryParameter("iam")
        val TWI = uri.getQueryParameter("tw")
        val GIT = uri.getQueryParameter("gh")

        val user = User(
            name = NAME ?: "none",
            twitter = TWI ?: "none",
            github = GIT ?: "none"
        )

        userViewModel.insert(user)

        addbutton.setOnClickListener{
            try {
                val intent = Intent("com.google.zxing.client.android.SCAN")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // "PRODUCT_MODE for bar codes
                startActivityForResult(intent, 0)
            } catch (e: Exception) {
                val marketUri = Uri.parse("market://details?id=com.google.zxing.client.android")
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val contents = data?.getStringExtra("SCAN_RESULT")
                //uriに変換
                val uri = Uri.parse(contents.toString())

                val NAME = uri.getQueryParameter("iam")
                val TWI = uri.getQueryParameter("tw")
                val GIT = uri.getQueryParameter("gh")

                val user = User(
                    name = NAME ?: "none",
                    twitter = TWI ?: "none",
                    github = GIT ?: "none"
                )

                userViewModel.insert(user)
            }
            if (resultCode == RESULT_CANCELED) {
                //handle cancel
            }
        }

//        fun observeViewModel(){
//
//            userViewModel.frien
//        }
    }


}