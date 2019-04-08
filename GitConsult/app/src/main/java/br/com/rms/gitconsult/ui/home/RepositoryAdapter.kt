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

    private val Repos = mutableListOf<Repository>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_repository, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = Repos.size

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        val repo = Repos[position]

        holder.tvRepoName.text = repo.name
        holder.tvRepoDescription.text = repo.description
        holder.tvForkCount.text = repo.forks_count.toString()
        holder.tvStars.text = repo.stargazers_count.toString()
        holder.tvLanguage.text = repo.language
    }

    fun addRepository(newRepositories: List<Repository>) {
        val oldRepoList = mutableListOf<Repository>()
        oldRepoList.addAll(Repos)
        Repos.addAll(newRepositories)
        val RepoDiffUtilCallback = RepoDiffUtilCallback(oldRepoList, Repos)
        DiffUtil.calculateDiff(RepoDiffUtilCallback).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}