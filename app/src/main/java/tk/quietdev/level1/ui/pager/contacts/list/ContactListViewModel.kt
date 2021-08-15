package tk.quietdev.level1.ui.pager.contacts.list

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tk.quietdev.level1.database.FakeDatabase
import tk.quietdev.level1.models.UserModel
import tk.quietdev.level1.utils.Const

class ContactListViewModel(
    private val db: FakeDatabase
) : ViewModel() {

    var userList = MutableLiveData<MutableList<UserModel>>()
    private var deletedUserPosition: Int? = null
    private var handler: Handler? = null
    private val userIdToRemove = mutableListOf<Int>()

    var isRemoveState = MutableLiveData(false)


    init {
        userList = MutableLiveData(db.getUserList(Const.FAKE_USER_AMOUNT_TO_LIST).toMutableList())
    }

    fun addUserBack(id: Int) {
        val user = db.getUserWithNoValidation(id)
        user?.let {
            deletedUserPosition?.let {
                userList.value?.add(it, user.also { it.isToRemoveChecked = false })
                deletedUserPosition = null
                updateLiveData()
            }
        }
    }

    fun removeUser(userModel: UserModel, position: Int) {
        val removedUser = userList.value?.remove(userModel)
        removedUser?.let {
            deletedUserPosition = position
            updateLiveData()
            userIdToRemove.remove(userModel.id)
            isRemoveState.value = userIdToRemove.isNotEmpty()
        }
    }

    fun addNewUser(userModel: UserModel) {
        userList.value?.add(userModel)
        updateLiveData()
    }

    private fun updateLiveData() {
        userList.value = userList.value
    }

    private fun getHandler(): Handler? {
        if (handler == null) {
            handler = Handler(Looper.getMainLooper())
        }
        return handler
    }

    fun updateUser(updatedUserModel: UserModel) {
       userList.value?.let {
           for (i in it.indices) {
               if (it[i].id == updatedUserModel.id) {
                   it[i] = updatedUserModel
                   break
               }
           }
           updateLiveData()
       }
    }

    fun toggleUserRemove(userId: Int) {
        val isUserRemovedFromList = userIdToRemove.remove(userId)
        if (!isUserRemovedFromList) {
            userIdToRemove.add(userId)
        }
        isRemoveState.value = userIdToRemove.isNotEmpty()
    }

    fun removeUsers() {
        val newList = userList.value?.filter { !userIdToRemove.contains(it.id) }?.toMutableList()
        newList?.let {
            userList.value = it
        }
        userIdToRemove.clear()
        isRemoveState.value = false
    }

}