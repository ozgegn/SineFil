package com.ozgegn.sinefil.features.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.ProviderModel
import com.ozgegn.sinefil.databinding.ItemAvailableServicesBinding

class StreamServicesAdapter :
    ListAdapter<ProviderModel, StreamServicesAdapter.StreamServicesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamServicesViewHolder {
        return StreamServicesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StreamServicesViewHolder, position: Int) {
        holder.onBindView(getItem(position))
    }

    class StreamServicesViewHolder(val binding: ItemAvailableServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(providerModel: ProviderModel) {
            binding.provider = providerModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): StreamServicesViewHolder {
                val binding = DataBindingUtil.inflate<ItemAvailableServicesBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_available_services,
                    parent,
                    false
                )
                return StreamServicesViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ProviderModel>() {
        override fun areItemsTheSame(oldItem: ProviderModel, newItem: ProviderModel): Boolean {
            return oldItem.providerId == newItem.providerId
        }

        override fun areContentsTheSame(oldItem: ProviderModel, newItem: ProviderModel): Boolean {
            return oldItem == newItem
        }

    }

}