package com.aliozdemir.getir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.getir.data.entity.Campaign
import com.aliozdemir.getir.databinding.ItemCampaignBinding

class CampaignAdapter(private val campaignList: List<Campaign>) : RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    class CampaignViewHolder(val binding: ItemCampaignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = ItemCampaignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        val campaign = campaignList[position]
        holder.binding.imageViewCampaign.setImageResource(campaign.image)
    }

    override fun getItemCount(): Int {
        return campaignList.size
    }

}