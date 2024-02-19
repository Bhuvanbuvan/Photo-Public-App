package com.example.firebaseimageapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.firebaseimageapp.adapter.PostAdapter
import com.example.firebaseimageapp.databinding.FragmentHomeBinding
import com.example.firebaseimageapp.model.PostModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private  var firestore= FirebaseFirestore.getInstance().collection("client1")
    private  var firebaseStorage= FirebaseStorage.getInstance().getReference("client1")
    lateinit var myadapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newFragment)
        }
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        myadapter= PostAdapter(requireContext())
        binding.recyclerView.apply {
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter=myadapter
        }
            firestore.get().addOnSuccessListener {snapshot->
                var post= mutableListOf<PostModel>()
                for (snap in snapshot){
                    post.add(PostModel(snap.data.get("title"),snap.data.get("discription"),snap.data.get("image")))
                }
                myadapter.differ.submitList(post)
            }
        }


}