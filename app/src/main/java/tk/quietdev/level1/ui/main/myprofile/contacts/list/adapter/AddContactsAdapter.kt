package tk.quietdev.level1.ui.main.myprofile.contacts.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import tk.quietdev.level1.databinding.ListItemBinding
import tk.quietdev.level1.ui.main.myprofile.contacts.list.adapter.holders.ContactHolderAdd
import tk.quietdev.level1.ui.main.myprofile.contacts.list.adapter.holders.ContactHolderBase
import tk.quietdev.level1.ui.main.myprofile.contacts.list.adapter.holders.HolderState


class AddContactsAdapter(
    onClickListener: ContactHolderBase.OnItemClickListener,
    holderState: MutableLiveData<MutableMap<Int, HolderState>>
) : BaseContactsAdapter(onClickListener, holderState) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolderBase {
        return ContactHolderAdd(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClickListener,
        )
    }

}