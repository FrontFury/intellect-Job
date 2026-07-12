package com.example.intellectjob.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intellectjob.Model.Jobs
import com.example.intellectjob.databinding.ItemJobManageBinding

class JobsAdapter(
    private var jobList: List<Jobs>,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    fun updateData(newList: List<Jobs>) {
        jobList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding = ItemJobManageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val job = jobList[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int = jobList.size

    inner class JobsViewHolder(private val binding: ItemJobManageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: Jobs) {
            binding.tvJobTitle.text = job.title
            binding.tvCompanyName.text = job.company
            binding.tvJobType.text = job.jobType
            binding.tvJobLocation.text = job.location
            binding.tvSalary.text = "৳${job.salary.min} - ৳${job.salary.max}"
            binding.tvVacancies.text = "${job.vacancies} Vacancies"

            binding.btnDelete.setOnClickListener {
                onDeleteClick(job._id)
            }
        }
    }
}