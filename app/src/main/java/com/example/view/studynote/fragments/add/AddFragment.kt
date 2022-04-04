package com.example.view.studynote.fragments.add

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.view.studynote.R
import com.example.view.studynote.data.models.Priority
import com.example.view.studynote.data.models.ToDoData
import com.example.view.studynote.data.viewmodel.ToDoViewModel
import com.example.view.studynote.databinding.FragmentAddBinding
import com.example.view.studynote.databinding.FragmentUpdateBinding
import com.example.view.studynote.fragments.SharedViewModel
//import kotlinx.android.synthetic.main.fragment_add.*
//import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private var _binding: FragmentAddBinding?=null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.prioritiesSpinner.onItemSelectedListener=mSharedViewModel.listener
        return binding.root
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_add, container, false)
//        // 메뉴 세팅
//        setHasOptionsMenu(true)
//
//        view.priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
//
//        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation){
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}