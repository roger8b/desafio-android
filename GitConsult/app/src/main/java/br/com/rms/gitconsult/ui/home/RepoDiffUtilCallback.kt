package br.com.rms.gitconsult.ui.home

import androidx.recyclerview.widget.DiffUtil
import br.com.rms.gitconsult.data.local.database.entity.Repository

class RepoDiffUtilCallback(
    private val oldStatementList: MutableList<Repository>,
    private val newSatementList: MutableList<Repository>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldStatementList.size

    override fun getNewListSize(): Int = newSatementList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (old, new) = getStatements(oldItemPosition, newItemPosition)
        return old.id == new.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (old, new) = getStatements(oldItemPosition, newItemPosition)
        return old.name.equals(new.name) && old.stargazers_count == new.stargazers_count
    }

    private fun getStatements(oldItemPosition: Int, newItemPosition: Int): Pair<Repository, Repository> {
        return Pair(
            oldStatementList[oldItemPosition],
            newSatementList[newItemPosition]
        )
    }
}
