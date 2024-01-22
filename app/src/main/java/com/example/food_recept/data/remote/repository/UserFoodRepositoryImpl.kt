package com.example.food_recept.data.remote.repository

import android.util.Log
import com.example.food_recept.data.local.mapper.toData
import com.example.food_recept.data.remote.util.FirebaseResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.model.FoodModel
import com.example.food_recept.domain.repository.remote.UserFoodRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserFoodRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseResource: FirebaseResource,
    private val firebaseAuth: FirebaseAuth
) : UserFoodRepository {
    override suspend fun getUserFood(): Flow<Resource<List<FoodModel>>> {
        return firebaseResource.handleResource {
            fireStore.collection("user").document(firebaseAuth.uid!!).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.i("omiko", document.data.toString())
                    document.data
                } else {
                    "Not Include"
                }
            }
        }
    }

    override suspend fun addUserFood(foodModel: FoodModel) {
        fireStore.collection("user").document(firebaseAuth.uid!!).collection("foods")
            .document(foodModel.toData().foodId.toString()).set(foodModel.toData())

    }

    override suspend fun deleteUserFood(foodId: String) {
        fireStore.collection("user").document(firebaseAuth.uid!!).collection("foods").document(foodId).delete()
    }
}