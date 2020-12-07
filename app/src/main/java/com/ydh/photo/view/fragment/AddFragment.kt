package com.ydh.photo.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ydh.photo.*
import com.ydh.photo.data.PhotoClient
import com.ydh.photo.data.repository.PhotoRemoteRepositoryImpl
import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.databinding.FragmentAddBinding
import com.ydh.photo.viewmodel.AddViewModel
import com.ydh.photo.viewmodel.AddViewModelFactory
import com.ydh.photo.viewmodel.state.AddState

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding

    private val service by lazy { PhotoClient.photoService }
    private val repository by lazy { PhotoRemoteRepositoryImpl(service) }
    private val viewModelFactory by lazy {
        AddViewModelFactory(repository)
    }
    private val viewModel by viewModels<AddViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        setView()
        setObserver()

        return binding.root
    }

    private fun setView(){
        binding.run {
            var status = ""
            arguments?.let { bundle ->
                val args = AddFragmentArgs.fromBundle(bundle)
                binding.photo = args.photo
                status = args.status
                btAdd.text = status
            }

            btAdd.setOnClickListener {

                if (status == "add"){
                    viewModel.insertPhoto(PhotoRequest(title = etTitle.text.toString()))
                } else if (status == "update"){
                    viewModel.updatePhoto(binding.photo!!)
                }
            }
        }
    }

    private fun setObserver(){
        viewModel.state.observe(viewLifecycleOwner){ func ->
            when(func){
                is AddState.SuccessAddState -> {
                    Toast.makeText(requireContext(), "your new id is ${func.id}", Toast.LENGTH_SHORT).show()
                }
                is AddState.SuccessUpdateState -> {
                    Toast.makeText(requireContext(), "your photo with id: ${func.id} is updated", Toast.LENGTH_SHORT).show()
                }
                is AddState.Loading -> println(func.message)
                is AddState.Error -> println(func.exception.toString())
                else -> println("error")
            }
        }
    }




}

