package br.com.rms.gitconsult.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.data.local.database.entity.Repository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.*


class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var onItemClick: ((Repository) -> Unit)? = null


    private val Repos = mutableListOf<Repository>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_repository, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = Repos.size

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        val repository = Repos[position]

        holder.cardView.setOnClickListener {
            onItemClick?.invoke(repository)
        }
        holder.tvRepoName.text = repository.name
        holder.tvRepoDescription.text = repository.description
        holder.tvForkCount.text = repository.forks_count.toString()
        holder.tvStars.text = repository.stargazers_count.toString()
        holder.tvLanguage.text = repository.language
    }

    fun addRepository(newRepositories: List<Repository>) {
        val oldRepoList = mutableListOf<Repository>()
        oldRepoList.addAll(Repos)
        Repos.addAll(newRepositories)
        val repoDiffUtilCallback = RepoDiffUtilCallback(oldRepoList, Repos)
        DiffUtil.calculateDiff(repoDiffUtilCallback).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}