package com.example.intellectjob

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.intellectjob.databinding.ActivitySavedJobPageBinding
import com.example.intellectjob.databinding.ItemSavedJobBinding




class SavedJobAdapter(private val savedJobList: List<SavedJob>): RecyclerView.Adapter<SavedJobAdapter.SavedJobViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedJobViewHolder {
        val binding = ItemSavedJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedJobViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SavedJobViewHolder,
        position: Int
    ) {
        val currentSavedJob = savedJobList[position]

        holder.binding.tvSavedJobTitle.text = currentSavedJob.title
        holder.binding.tvSavedCompany.text = currentSavedJob.company
        holder.binding.savedlocationLayout.text = currentSavedJob.location
        holder.binding.tvSavedSalary.text = currentSavedJob.salary

    }

    override fun getItemCount(): Int {
        return savedJobList.size
    }

    inner class SavedJobViewHolder(val binding: ItemSavedJobBinding) : RecyclerView.ViewHolder(binding.root)
}