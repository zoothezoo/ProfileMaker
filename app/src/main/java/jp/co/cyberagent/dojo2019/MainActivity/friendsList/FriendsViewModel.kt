package jp.co.cyberagent.dojo2019.MainActivity.friendsList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.network.APIClient
import jp.co.cyberagent.dojo2019.data.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FriendsViewModel(
) : ViewModel(){

    val repositotyList = MutableLiveData<List<Repository>>()

    fun fetchRepositoryList(gitHubID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                repositotyList.value = APIClient
                    .retrofit
                    .fetchRepoList(gitHubID)
            }.await()
        }
    }

}