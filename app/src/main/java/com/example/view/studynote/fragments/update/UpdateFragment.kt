package com.example.view.studynote.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.view.studynote.R
import com.example.view.studynote.data.models.Priority
import com.example.view.studynote.data.models.ToDoData
import com.example.view.studynote.data.viewmodel.ToDoViewModel
import com.example.view.studynote.databinding.FragmentUpdateBinding
import com.example.view.studynote.fragments.SharedViewModel
//import kotlinx.android.synthetic.main.fragment_update.*
//import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private var _binding: FragmentUpdateBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(layoutInflater,container,false)
        binding.currentTitleEt.setText(args.currentitem.title)
        binding.currentDescriptionEt.setText(args.currentitem.description)
        binding.currentPrioritiesSpinner.setSelection((mSharedViewModel.parsePriorityToInt(args.currentitem.priority)))
        binding.currentPrioritiesSpinner.onItemSelectedListener=mSharedViewModel.listener
        return binding.root

//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_update, container, false)
//        val current_ti : EditText =view.findViewById(R.id.current_title_et)
//        val current_de : EditText =view.findViewById(R.id.current_description_et)
//        val current_pr : Spinner =view.findViewById(R.id.current_priorities_spinner)
//
//            //메뉴 세팅
//        setHasOptionsMenu(true)
//
//        current_ti.setText(args.currentitem.title)
//        current_de.setText(args.currentitem.description)
//        current_pr.setSelection(mSharedViewModel.parsePriorityToInt(args.currentitem.priority))
//        current_pr.onItemSelectedListener = mSharedViewModel.listener
//        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {

        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title,description)
        if(validation){
            // 아이템 최신화
            val updatItem = ToDoData(
                args.currentitem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updatItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()

        }
    }

    // 항목 제거 확인을 다이알로그에 표시
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mToDoViewModel.deleteItem(args.currentitem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentitem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete '${args.currentitem.title}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentitem.title}'?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}