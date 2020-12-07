package com.ydh.photo.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ydh.photo.*
import com.ydh.photo.data.PhotoClient
import com.ydh.photo.data.repository.PhotoRemoteRepositoryImpl
import com.ydh.photo.databinding.FragmentGetAllBinding
import com.ydh.photo.model.PhotoModel
import com.ydh.photo.view.adapter.PhotoAdapter
import com.ydh.photo.viewmodel.GetAllViewModel
import com.ydh.photo.viewmodel.GetAllViewModelFactory
import com.ydh.photo.viewmodel.state.GetAllState


class GetAllFragment : Fragment() , PhotoAdapter.PhotoItemListener {

    private lateinit var binding: FragmentGetAllBinding
    private val adapter by lazy { PhotoAdapter(requireActivity(), this) }
    private val service by lazy { PhotoClient.photoService }
    private val repository by lazy { PhotoRemoteRepositoryImpl(service) }
    private val viewModelFactory by lazy {
        GetAllViewModelFactory(repository)
    }
    private val viewModel by viewModels<GetAllViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetAllBinding.inflate(inflater, container, false)


        setView()
        setObserver()


        return binding.root
    }

    override fun onClick(model: PhotoModel) {

        val action = GetAllFragmentDirections.actionGetAllFragmentToAddFragment(model, "update")
        findNavController().navigate(action)
    }

    override fun onClickDelete(model: PhotoModel) {
        viewModel.deletePhoto(model.id)
    }

    private fun setView(){
        binding.run {
            rvGetAllPhoto.adapter = adapter
            fabAddPhoto.setOnClickListener {
                val action = GetAllFragmentDirections.actionGetAllFragmentToAddFragment(null, "add")
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPhoto()
    }

    private fun setObserver(){
        viewModel.state.observe(viewLifecycleOwner){ func ->
            when(func){
                is GetAllState.SuccessGetAllPhoto -> {
                    adapter.list = func.list.toMutableList()
                }
                is GetAllState.SuccessDeletePhoto -> {
                    Toast.makeText(requireContext(), func.message, Toast.LENGTH_SHORT).show()
                }
                is GetAllState.Loading -> println(func.message)
                is GetAllState.Error -> println(func.exception.toString())
                else -> println("error")
            }
        }
    }




}