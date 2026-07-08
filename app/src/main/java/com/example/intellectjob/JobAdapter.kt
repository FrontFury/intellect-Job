package com.example.intellectjob // Adjust to match your package name

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intellectjob.databinding.ItemJobBinding
class JobAdapter(private val jobList: List<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    inner class JobViewHolder(val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val currentJob = jobList[position]

        holder.binding.tvJobTitle.text = currentJob.title
        holder.binding.tvCompany.text = currentJob.company
        holder.binding.tvSalary.text = currentJob.salary
    }

    override fun getItemCount(): Int {
        return jobList.size
    }
}