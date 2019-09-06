package jp.co.cyberagent.dojo2019.MainActivity.friendsList

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.WebActivity.WebActivity
import jp.co.cyberagent.dojo2019.data.db.User
import jp.co.cyberagent.dojo2019.data.network.Repository
import kotlinx.android.synthetic.main.item_repo.view.*


//友人一覧用のrecyclerviewのadapter

class FriendsViewAdapter(
    val context: Context
): RecyclerView.Adapter<FriendsViewAdapter.RecyclerViewHolder>(){
    private val itemList = mutableListOf<User>()

    public lateinit var friendsViewModel: FriendsViewModel

    //User情報をitemListに入れます
    fun setUsers(userList: List<User>){
        //this.itemList = userList as MutableList<User>
        itemList.clear()
        itemList.addAll(userList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.twitter.text = item.twitter
        holder.github.text = item.github

        val context = this.context


        Picasso.get().load(FriendsViewModel().fetchRepositoryList(item.github)).into(holder.itemView.imageView)

        holder.twitter.setOnClickListener {
            //Toast.makeText(context,item.twitter,Toast.LENGTH_SHORT).show()
            gotwi(item)
        }
        holder.github.setOnClickListener {
            //Toast.makeText(context,item.github,Toast.LENGTH_SHORT).show()
            gogit(item)
        }



    }

    fun gotwi(item: User){
        val str = "https://twitter.com/${item.twitter}"
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra("str", str)
        context.startActivity(intent)
    }

    fun gogit(item: User){
        val str = "https://github.com/${item.github}"
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra("str", str)
        context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        //インフレイトする
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RecyclerViewHolder(
            inflate
        )
    }

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.nameItem)
        val twitter = view.findViewById<TextView>(R.id.twitterItem)
        val github = view.findViewById<TextView>(R.id.githubItem)
    }


}