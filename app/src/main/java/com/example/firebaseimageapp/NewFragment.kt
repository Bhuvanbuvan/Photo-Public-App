package com.example.firebaseimageapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.firebaseimageapp.databinding.FragmentNewBinding
import com.example.firebaseimageapp.model.PostModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class NewFragment : Fragment() {
    private var _binding:FragmentNewBinding?=null
    private val binding get() =_binding!!
    private  var firestore=FirebaseFirestore.getInstance().collection("client1")
    private  var firebaseStorage=FirebaseStorage.getInstance().getReference("client1")
     var uri:Uri?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentNewBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ImageView.setOnClickListener{
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,100)
        }

        binding.Savebtn.setOnClickListener {
            var url:String=""
            val title: String = binding.PostTitle.text.toString()
            val disc: String = binding.Discription.text.toString()
            firebaseStorage.child(title).putFile(uri!!).addOnSuccessListener { task ->
                task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    url=it.toString()
                    val post=PostModel(title,disc,url)
                    firestore.document(title).set(post).addOnSuccessListener {
                        Toast.makeText(context,"data hase been sentit",Toast.LENGTH_LONG).show()
                    }.addOnCanceledListener {
                        Toast.makeText(context,"data nor snt it",Toast.LENGTH_LONG).show()
                    }
                }
            }

            it.findNavController().navigate(R.id.action_newFragment_to_homeFragment)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100 && resultCode==RESULT_OK){
            uri =data?.data
            binding.ImageView.setImageURI(uri)
        }
    }


}