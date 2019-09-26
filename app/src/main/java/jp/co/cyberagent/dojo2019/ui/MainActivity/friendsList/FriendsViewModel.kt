package jp.co.cyberagent.dojo2019.ui.MainActivity.friendsList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.db.User
import jp.co.cyberagent.dojo2019.data.network.APIClient
import jp.co.cyberagent.dojo2019.data.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FriendsViewModel : ViewModel(){

    val friends= MutableLiveData<List<User>>()
    val repositotyList = MutableLiveData<List<Repository>>()

    fun fetchRepositoryList(gitHubID: String) =
        viewModelScope.launch(Dispatchers.IO) {
            async {
                APIClient
                    .retrofit
                    .fetchRepoList(gitHubID)
            }.await()
        }


    fun refresh(){
        val friend1 = User("kohei","twitter","github")
        val friendlist = arrayListOf<User>(friend1)

        friends.value = friendlist
    }
}
