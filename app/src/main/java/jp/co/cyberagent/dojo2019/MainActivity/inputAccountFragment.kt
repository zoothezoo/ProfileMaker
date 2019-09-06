package jp.co.cyberagent.dojo2019.MainActivity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.zxing.BarcodeFormat
import android.widget.ImageView
import androidx.core.graphics.drawable.toIcon
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_input_account.*
import kotlinx.android.synthetic.main.item_repo.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [inputAccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [inputAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class inputAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contextThemeWrapper = ContextThemeWrapper(activity,R.style.DarkThemeApp)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        val view = localInflater.inflate(R.layout.fragment_input_account,container,false)
        //val view = inflater.inflate(R.layout.fragment_input_account,container,false)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val makebut = view?.findViewById<Button>(R.id.makebutton)
        val closebut = view?.findViewById<Button>(R.id.closebutton)

        val imageViewQrCode = view.findViewById<ImageView>(R.id.qrcode)
        var flag = false //BitMap作成したかどうか

        cardview.visibility = GONE

        //shareprefernces用
        val data = this.activity?.getSharedPreferences("savedata",Context.MODE_PRIVATE)
        val editor = data?.edit()

        //editview用
        val name = view?.findViewById<EditText>(R.id.address)?.setText(data?.getString("Name",""))
        val twiac = view?.findViewById<EditText>(R.id.twitterAc)?.setText(data?.getString("Twitter",""))
        val gitac = view?.findViewById<EditText>(R.id.githubAc)?.setText(data?.getString("Github",""))

        makebut?.setOnClickListener {
            if(flag){
                imageViewQrCode.visibility = View.VISIBLE
                cardview.visibility = VISIBLE
            }
            else {
                val name = view.findViewById<EditText>(R.id.address)?.text.toString()
                val twiac = view.findViewById<EditText>(R.id.twitterAc)?.text.toString()
                val gitac = view.findViewById<EditText>(R.id.githubAc)?.text.toString()

                if(name == ""|| twiac == ""|| gitac == ""){
                    AlertDialog.Builder(activity)
                        .setTitle("input text")
                        .setPositiveButton("OK", null)
                        .show()
                    }
                else {
                    val newname = name.replace(" ", "%20", true)
                    val url = "ca-tech://dojo/share?iam=$newname&tw=$twiac&gh=$gitac"
                    editor?.let {
                        it.putString("Name", name)
                        it.putString("Twitter", twiac)
                        it.putString("Github", gitac)
                        it.apply()
                    }
                    //urlをbitmapにencodeする
                    try {
                        val barcodeEncoder = BarcodeEncoder()
                        val bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 800, 800)
                        imageViewQrCode.setImageBitmap(bitmap)
                        imageViewQrCode.bringToFront()
                        flag = true
                    } catch (e: Exception) {
                    }
                }
            }
        }
        closebut.setOnClickListener {
            imageViewQrCode.visibility = View.GONE
            cardview.visibility = GONE
        }
    }

//      fun onButtonPressed() {
//      }
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
//         * @return A new instance of fragment inputAccountFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            inputAccountFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
