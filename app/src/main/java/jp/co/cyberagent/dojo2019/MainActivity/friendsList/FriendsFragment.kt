package jp.co.cyberagent.dojo2019.MainActivity.friendsList

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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FriendsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FriendsFragment : Fragment() , CoroutineScope by MainScope(){
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var listener: OnFragmentInteractionListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

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
        val mAdapter =
            FriendsViewAdapter(view.context)
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


                //launch(Dispatchers.IO){
                //    async {
                //        AppDatabase.getInstance()?.userDao()?.insert(user)
                //    }.await()
                //}
            }
            if (resultCode == RESULT_CANCELED) {
                //handle cancel
            }
        }
    }

    //fun createDataset() : List<String>{
    //    val dataset : MutableList<String> = mutableListOf()

    //    repeat(10){
    //        dataset.add("yeeey")
    //    }
    //    return dataset
    //}

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     *
//     *
//     * See the Android Training lesson [Communicating with Other Fragments]
//     * (http://developer.android.com/training/basics/fragments/communicating.html)
//     * for more information.
//     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FriendsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FriendsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}