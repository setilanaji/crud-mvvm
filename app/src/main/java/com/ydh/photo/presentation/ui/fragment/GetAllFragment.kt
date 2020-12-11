package com.ydh.photo.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.ydh.photo.databinding.FragmentGetAllBinding
import com.ydh.photo.domain.PhotoDomain
import com.ydh.photo.presentation.ui.adapter.PhotoAdapter
import com.ydh.photo.presentation.ui.viewmodel.PhotoViewModel
import com.ydh.photo.presentation.ui.state.PhotoState


class GetAllFragment : Fragment() , PhotoAdapter.PhotoItemListener {

    private lateinit var binding: FragmentGetAllBinding
    private val adapter by lazy { PhotoAdapter(requireActivity(), this) }
    private val viewModel by viewModel<PhotoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetAllBinding.inflate(inflater, container, false)


        setView()
        setObserver()


        return binding.root
    }

    override fun onClick(domain: PhotoDomain) {

        val action = GetAllFragmentDirections.actionGetAllFragmentToAddFragment(domain, "update")
        findNavController().navigate(action)
    }

    override fun onClickDelete(domain: PhotoDomain) {
        viewModel.deletePhoto(domain)
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
                is PhotoState.SuccessGetAllPhoto -> {
                    adapter.list = func.list.toMutableList()
                }
                is PhotoState.SuccessDeletePhoto -> {
                    Toast.makeText(requireContext(), func.message, Toast.LENGTH_SHORT).show()
                }
                is PhotoState.Loading -> println(func.message)
                is PhotoState.Error -> println(func.exception.toString())
                else -> println("error")
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        viewModel.onDestroy()
    }



}