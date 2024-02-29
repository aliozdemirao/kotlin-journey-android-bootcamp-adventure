package com.aliozdemir.getir.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.getir.R
import com.aliozdemir.getir.data.entity.Campaign
import com.aliozdemir.getir.data.entity.Category
import com.aliozdemir.getir.databinding.FragmentHomeBinding
import com.aliozdemir.getir.ui.adapter.CampaignAdapter
import com.aliozdemir.getir.ui.adapter.CategoryAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


        val address = ArrayList<String>()
        address.add("Getir UK, Limited WeWork, 30 Stamford Street, London, SE1 9LQ")
        address.add("Getir US, 625 W. Adams Street, 19th floor, Chicago, IL 60661")
        address.add("Getir Germany GmbH, Prenzlauer Allee 242-247, 10405 Berlin")
        address.add("Keizersgracht 572, 1017 EM Amsterdam, Nederland")
        address.add("Etiler Mah. Tanburi Ali Efendi Sk. No:13 GetirOfis, Etiler, Beşiktaş/İstanbul")


        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, address)
        binding.spinner.adapter = arrayAdapter




        val campaigns = arrayListOf(
            Campaign(R.drawable.im_campaign_6),
            Campaign(R.drawable.im_campaign_5),
            Campaign(R.drawable.im_campaign_4),
            Campaign(R.drawable.im_campaign_2),
            Campaign(R.drawable.im_campaign_1),
            Campaign(R.drawable.im_campaign_3)
        )

        val campaignAdapter = CampaignAdapter(campaigns)
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerViewCampaign.layoutManager = linearLayoutManager
        binding.recyclerViewCampaign.adapter = campaignAdapter





        val categoryList = arrayListOf(
            Category(R.drawable.im_beverages, "Beverages"),
            Category(R.drawable.im_fruits_veg, "Fruits & Veg"),
            Category(R.drawable.im_milk_dairy, "Milk & Dairy"),
            Category(R.drawable.im_baked_goods, "Baked Goods"),
            Category(R.drawable.im_snacks, "Snacks"),
            Category(R.drawable.im_ice_cream, "Ice Cream"),
            Category(R.drawable.im_food, "Food"),
            Category(R.drawable.im_breakfast, "Breakfast"),
            Category(R.drawable.im_ready_to_eat, "Ready to Eat"),
            Category(R.drawable.im_fit_form, "Fit & Form"),
            Category(R.drawable.im_personal_care, "Personal Care"),
            Category(R.drawable.im_home_care, "Home Care"),
            Category(R.drawable.im_home_living, "Home & Living"),
            Category(R.drawable.im_pet_food, "Pet Food"),
            Category(R.drawable.im_baby_care, "Baby Care"),
            Category(R.drawable.im_sex_health, "Sex Health")
        )

        val categoryAdapter = CategoryAdapter(categoryList)
        val gridLayoutManager = GridLayoutManager(context, 4)

        binding.recyclerViewCategory.layoutManager = gridLayoutManager
        binding.recyclerViewCategory.adapter = categoryAdapter


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}