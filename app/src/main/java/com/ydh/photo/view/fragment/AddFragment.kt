package com.ydh.photo.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.databinding.FragmentAddBinding
import com.ydh.photo.viewmodel.PhotoViewModel
import com.ydh.photo.viewmodel.state.PhotoState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding

    private val viewModel by viewModel<PhotoViewModel>()

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
                is PhotoState.SuccessAddState -> {
                    Toast.makeText(requireContext(), "your new id is ${func.id}", Toast.LENGTH_SHORT).show()
                }
                is PhotoState.SuccessUpdateState -> {
                    Toast.makeText(requireContext(), "your photo with id: ${func.id} is updated", Toast.LENGTH_SHORT).show()
                }
                is PhotoState.Loading -> println(func.message)
                is PhotoState.Error -> println(func.exception.toString())
                else -> println("error")
            }
        }
    }




}

